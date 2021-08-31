package com.arifin.placesearhing.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifin.placesearhing.BuildConfig
import com.arifin.placesearhing.R
import com.arifin.placesearhing.R.string
import com.arifin.placesearhing.`interface`.CellClickListener
import com.arifin.placesearhing.adapter.SearchListAdapter
import com.arifin.placesearhing.databinding.ActivitySearchListBinding
import com.arifin.placesearhing.model.nearbyplaces.Result
import com.arifin.placesearhing.viewModel.NearByPlacesViewModel

class SearchListActivity : AppCompatActivity(), CellClickListener {
    private lateinit var binding: ActivitySearchListBinding
    private val apiKey: String = BuildConfig.API_KEY
    private lateinit var list: ArrayList<Result>
    private var searchListAdapter: SearchListAdapter? = null
    private val viewModel: NearByPlacesViewModel by viewModels()
    lateinit var location: String
    lateinit var radius: String
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting data from intent extras
        location = intent.getStringExtra("location")!!
        radius = intent.getStringExtra("radius")!!
        name = intent.getStringExtra("name")!!

        viewModel.getNearByPlaces(location, radius, name, apiKey).observe(this) { response ->
            if (response.status == getString(string.ok)) {
                list = response.results as ArrayList<Result>
                setValues(list) // setting values in list
                binding.ivNoData.visibility = View.GONE
            } else if (response.status == getString(string.zero_results)) {
                binding.ivNoData.visibility = View.VISIBLE
            }
        }
        viewModel.getIsUpdate().observe(this) { // for progressbar loading
            if (it) binding.progressBar.visibility = View.GONE
            else binding.progressBar.visibility = View.VISIBLE
        }
        binding.ivBack.setOnClickListener { // for going to previous activity
            onBackPressed()
        }

    }

    private fun setValues(list: ArrayList<Result>) {
        binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(this)
        searchListAdapter = SearchListAdapter(applicationContext, list, this)
        binding.searchResultRecyclerView.adapter = searchListAdapter

    }

    override fun onCellClickListener(data: Result) {
        val intent = Intent(this, ResultDetailsActivity::class.java)
        intent.putExtra("data", data)  // sending clicked data through intent
        startActivity(intent)
    }


}
