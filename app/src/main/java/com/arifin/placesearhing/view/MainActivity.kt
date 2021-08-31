package com.arifin.placesearhing.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arifin.placesearhing.BuildConfig
import com.arifin.placesearhing.R
import com.arifin.placesearhing.R.string
import com.arifin.placesearhing.databinding.ActivityMainBinding
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode.*
import java.util.*

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val apiKey: String = BuildConfig.API_KEY
  private var isValidInput: Boolean = false
  private lateinit var latlon: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)  // viewBinded
    setContentView(binding.root)

    //Initialize google place api for on Search select
    Places.initialize(applicationContext, apiKey)
    binding.etAddress.setOnClickListener {
      val fieldList = listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME)
      Autocomplete.IntentBuilder(OVERLAY, fieldList)
        .build(this@MainActivity).apply {
          this@MainActivity.startActivityForResult(this, 100)
        }

    }

    textWatcher() // for text input check

    binding.imageViewBtn.setOnClickListener {  //GOTO SearchListActivity
      if (isValidInput) {
        val intent = Intent(this, SearchListActivity::class.java)
        intent.putExtra("location", latlon)
        intent.putExtra("radius", binding.etRadius.text.toString())
        // intent.putExtra("types", "food")
        intent.putExtra("name", binding.etSearch.text.toString())
        startActivity(intent)
      } else {
        Toast.makeText(
          applicationContext,
          getString(string.input_all_the_fields),
          Toast.LENGTH_SHORT
        ).show()
      }

    }
  }

  private fun textWatcher() {
    binding.etAddress.addTextChangedListener(textWatcher)
    binding.etRadius.addTextChangedListener(textWatcher)
    binding.etSearch.addTextChangedListener(textWatcher)
  }

  private val textWatcher = object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      changeButtonColor()
    }
  }

  @SuppressLint("UseCompatLoadingForDrawables")
  private fun changeButtonColor() {
    isValidInput = if (binding.etAddress.text.toString().trim { it <= ' ' }.isNotEmpty()
      && binding.etRadius.text.toString().trim { it <= ' ' }.isNotEmpty()
      && binding.etSearch.text.toString().trim { it <= ' ' }.isNotEmpty()
    ) {
      binding.imageViewBtn.setImageDrawable(getDrawable(R.drawable.ic_go_green))  // changing button color if all inputs are valid
      true
    } else {
      binding.imageViewBtn.setImageDrawable(getDrawable(R.drawable.ic_go_orange))
      false
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == 100 && resultCode == RESULT_OK) {
      val place = Autocomplete.getPlaceFromIntent(data!!)
      latlon =
        place.latLng?.latitude.toString() + "," + place.latLng?.longitude.toString() // getting latlon from placeAPI
      binding.etAddress.setText(place.name)
    } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
      Autocomplete.getStatusFromIntent(data!!)
      Toast.makeText(applicationContext, getString(string.something_went_wrong), Toast.LENGTH_SHORT)
        .show()
    }
    super.onActivityResult(requestCode, resultCode, data)
  }
}
