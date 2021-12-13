package com.edurda77.filmlibrary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.databinding.ActivityMapsBinding
import com.edurda77.filmlibrary.domain.LocationRepo
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
const val TMDB_API_KEY="2513408bca2d22ed908b2b3badf57939"
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var binding: ActivityMapsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        val arguments = intent.extras
        val placeOfBirth: String
        if (arguments != null) {
            val locationRepo: LocationRepo by lazy { app.locationRepo }
            placeOfBirth = arguments.getSerializable(String::class.java.simpleName) as String
            locationRepo.setLocation(placeOfBirth, this,googleMap)


        }
    }

}