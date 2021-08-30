package com.arifin.placesearhing.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.arifin.placesearhing.BuildConfig
import com.arifin.placesearhing.R
import com.arifin.placesearhing.databinding.ActivityMainBinding
import com.arifin.placesearhing.model.nearbyplaces.Result
import com.arifin.placesearhing.viewModel.NearByPlacesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NearByPlacesViewModel by viewModels()
    private var location:String = "-33.8670522,151.1957362"
    private var radius:String = "500"
    private var types:String = "food"
    private var name:String = "harbour"
    private val apiKey:String = BuildConfig.API_KEY
    private lateinit var list: List<Result>
    private var isUpdated:Boolean =false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        viewModel.getNearByPlaces(location,radius,types,name,apiKey).observe(this) { response ->
            if (response.status == "OK") {
                list= response.results
                setValues(list)
            }
        }
        viewModel.getNearByPlaces(location,radius,types,name,apiKey).observe(this) { response ->
            if (response.status == "OK") {
                list= response.results
                setValues(list)
            }
        }
        viewModel.getIsUpdate().observe(this){
            isUpdated=it
        }

    }

    private fun setValues(list: List<Result>) {
        R.style.noActionBar
        //binding.helloWorld.text= list[0].business_status +" \n"+ list[0].name+" \n"+ list[0].opening_hours
    }
}
