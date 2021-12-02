package com.edurda77.filmlibrary.data

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edurda77.filmlibrary.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.edurda77.filmlibrary.databinding.ActivityMapsBinding
import com.edurda77.filmlibrary.domain.LocationRepo
import com.edurda77.filmlibrary.ui.app

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
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