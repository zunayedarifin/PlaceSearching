package com.arifin.placesearhing.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arifin.placesearhing.databinding.ActivityResultDetailsBinding
import com.arifin.placesearhing.model.nearbyplaces.Result

class ResultDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: Result = intent.getParcelableExtra("data")!!

        binding.name.text = "Place name: "+ data.name
        binding.type.text = "Place type: "+data.types.toString()
        binding.status.text= "Status: " +data.business_status
        binding.ratings.text ="Ratings: " +data.rating.toString()+"(${data.user_ratings_total})"
       if (data.opening_hours?.open_now == true) binding.open.text = "The place is open now"
       else binding.open.text = "The place is not open now"


        binding.ivBack.setOnClickListener{
            onBackPressed()
        }
    }
}