package com.arifin.placesearhing.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifin.placesearhing.BuildConfig
import com.arifin.placesearhing.`interface`.CellClickListener
import com.arifin.placesearhing.adapter.SearchListAdapter
import com.arifin.placesearhing.databinding.ActivitySearchListBinding
import com.arifin.placesearhing.model.nearbyplaces.NearByPlace
import com.arifin.placesearhing.model.nearbyplaces.Result
import com.arifin.placesearhing.viewModel.NearByPlacesViewModel
import kotlin.math.log

class SearchListActivity : AppCompatActivity(), CellClickListener {
    private lateinit var binding: ActivitySearchListBinding
    private val apiKey:String = BuildConfig.API_KEY
    private lateinit var list: ArrayList<Result>
    // private var isUpdated:Boolean =false
    private var searchListAdapter: SearchListAdapter? = null
    private val viewModel: NearByPlacesViewModel by viewModels()
    lateinit var location: String
    lateinit var radius: String
    lateinit var types: String
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        location = intent.getStringExtra("location")!!
        radius = intent.getStringExtra("radius")!!
        types = intent.getStringExtra("types")!!
        name = intent.getStringExtra("name")!!

        viewModel.getNearByPlaces(location,radius,types,name,apiKey).observe(this) { response ->
            if (response.status == "OK") {
                list= response.results as ArrayList<Result>
                setValues(list)
            }
        }
        viewModel.getIsUpdate().observe(this){
            // isUpdated=it
            if(it) binding.progressBar.visibility= View.GONE
            else binding.progressBar.visibility= View.VISIBLE
        }
        binding.ivBack.setOnClickListener{
            onBackPressed()
        }

    }

    private fun setValues(list: ArrayList<Result>) {
        binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(this)
        searchListAdapter = SearchListAdapter(list,this)
        binding.searchResultRecyclerView.adapter = searchListAdapter

    }

    override fun onCellClickListener(data: Result) {
        val intent = Intent(this, ResultDetailsActivity::class.java)
        intent.putExtra("data",data)
        startActivity(intent)
    }


}
