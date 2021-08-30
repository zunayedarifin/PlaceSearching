package com.arifin.placesearhing.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifin.placesearhing.BuildConfig
import com.arifin.placesearhing.adapter.SearchListAdapter
import com.arifin.placesearhing.databinding.ActivitySearchListBinding
import com.arifin.placesearhing.model.nearbyplaces.NearByPlace
import com.arifin.placesearhing.model.nearbyplaces.Result
import com.arifin.placesearhing.viewModel.NearByPlacesViewModel

class SearchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchListBinding
    private val apiKey:String = BuildConfig.API_KEY
    private lateinit var list: ArrayList<Result>
    private var isUpdated:Boolean =false
    private var searchListAdapter: SearchListAdapter? = null
    private val viewModel: NearByPlacesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle: Bundle? = intent.extras
        val location: String = intent.getStringExtra("location")!!
        val radius: String = intent.getStringExtra("radius")!!
        val types: String = intent.getStringExtra("types")!!
        val name: String = intent.getStringExtra("name")!!

        viewModel.getNearByPlaces(location,radius,types,name,apiKey).observe(this) { response ->
            if (response.status == "OK") {
                list= response.results as ArrayList<Result>
                setValues(list)
            }
        }
        viewModel.getIsUpdate().observe(this){
            isUpdated=it
        }

    }

    private fun setValues(list: ArrayList<Result>) {
        binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SearchListAdapter(list)
        binding.searchResultRecyclerView.adapter = adapter

    }


}