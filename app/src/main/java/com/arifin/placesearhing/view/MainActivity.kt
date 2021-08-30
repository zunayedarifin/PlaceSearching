package com.arifin.placesearhing.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arifin.placesearhing.BuildConfig
import com.arifin.placesearhing.R
import com.arifin.placesearhing.databinding.ActivityMainBinding
import com.arifin.placesearhing.model.nearbyplaces.Result
import com.arifin.placesearhing.viewModel.NearByPlacesViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode.*
import java.util.*

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

        Places.initialize(applicationContext,apiKey)
        binding.etAddress.setOnClickListener {
            val fieldList = listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME)
            Autocomplete.IntentBuilder(OVERLAY, fieldList)
                .build(this@MainActivity).apply {
                    this@MainActivity.startActivityForResult(this, 100)
            }

        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val place = Autocomplete.getPlaceFromIntent(data!!)
            binding.etAddress.setText(place.latLng.toString())

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Autocomplete.getStatusFromIntent(data!!)
            Toast.makeText(applicationContext,"Something went wrong ",Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setValues(list: List<Result>) {
        R.style.noActionBar
        //binding.helloWorld.text= list[0].business_status +" \n"+ list[0].name+" \n"+ list[0].opening_hours
    }
}
