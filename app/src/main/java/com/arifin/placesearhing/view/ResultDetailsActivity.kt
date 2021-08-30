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

        val name: String = intent.getStringExtra("name")!!
        val type: String = intent.getStringExtra("type")!!
        val status: String = intent.getStringExtra("status")!!
        val ratings: String = intent.getStringExtra("name")!!

        binding.name.text = "Place name: "+ name
        binding.type.text = "Place type: "+type
        binding.status.text= "Status: " +status
        binding.ratings.text ="Status: " +ratings
//        if (data.opening_hours.open_now) binding.open.text = "The place is open now"
//        else binding.open.text = "The place is not open now"


        binding.ivBack.setOnClickListener{
            onBackPressed()
        }
    }
}