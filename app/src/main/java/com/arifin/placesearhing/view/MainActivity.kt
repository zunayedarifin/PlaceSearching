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
import android.text.Editable
import android.text.TextWatcher




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NearByPlacesViewModel by viewModels()
    private lateinit var  location:String
    private lateinit var  radius:String
    private lateinit var  types:String
    private lateinit var  name:String
    private val apiKey:String = BuildConfig.API_KEY
    private lateinit var list: List<Result>
    private var isUpdated:Boolean =false
    private var isValidInput:Boolean =false
    private lateinit var latlon:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Places.initialize(applicationContext,apiKey)
        binding.etAddress.setOnClickListener {
            val fieldList = listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME)
            Autocomplete.IntentBuilder(OVERLAY, fieldList)
                .build(this@MainActivity).apply {
                    this@MainActivity.startActivityForResult(this, 100)
            }

        }

        textWatcher()

        binding.imageViewBtn.setOnClickListener {
            val intent = Intent(this, SearchListActivity::class.java)
            intent.putExtra("location", latlon)
            intent.putExtra("radius", binding.etRadius.text.toString())
            intent.putExtra("types", "food")
            intent.putExtra("name", binding.etSearch.text.toString())
            startActivity(intent)
        }

    }

    private fun textWatcher() {
        binding.etAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(str: Editable) {
                changeButtonColor()
            }
        })
        binding.etRadius.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(str: Editable) {
                changeButtonColor()
            }
        })
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(str: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(str: Editable) {
                changeButtonColor()
            }
        })
    }

    private fun changeButtonColor() {
        if (binding.etAddress.text.toString().trim { it <= ' ' }.isNotEmpty()
            && binding.etRadius.text.toString().trim { it <= ' ' }.isNotEmpty()
            && binding.etSearch.text.toString().trim { it <= ' ' }.isNotEmpty()) {
            binding.imageViewBtn.setImageDrawable(getDrawable(R.drawable.ic_go_green))
            isValidInput=true
        } else {
            binding.imageViewBtn.setImageDrawable(getDrawable(R.drawable.ic_go_orange))
            isValidInput=false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val place = Autocomplete.getPlaceFromIntent(data!!)
            latlon=place.latLng?.latitude.toString()+","+place.latLng?.longitude.toString()
            binding.etAddress.setText(place.name)

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Autocomplete.getStatusFromIntent(data!!)
            Toast.makeText(applicationContext,"Something went wrong ",Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
