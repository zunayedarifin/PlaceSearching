package com.arifin.placesearhing.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arifin.placesearhing.R.string
import com.arifin.placesearhing.databinding.ActivityResultDetailsBinding
import com.arifin.placesearhing.model.nearbyplaces.Result

class ResultDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: Result = intent.getParcelableExtra("data")!! //getting clicked item data

        //setting clicked item data to layout
        binding.name.text = getString(string.place_name) + data.name
        binding.type.text = getString(string.place_type) + data.types.toString()
        binding.status.text = getString(string.status) + data.business_status
        binding.ratings.text =
            getString(string.ratings) + data.rating.toString() + "(${data.user_ratings_total})"
        if (data.opening_hours?.open_now == true) binding.open.text =
            getString(string.place_is_open_now)
        else binding.open.text = getString(string.the_place_is_not_open_now)


        binding.ivBack.setOnClickListener {  //for going previous activity
            onBackPressed()
        }
    }
}