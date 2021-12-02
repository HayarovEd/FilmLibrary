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
        mMap = googleMap
        val arguments = intent.extras
        val placeOfBirth: String
        if (arguments != null) {
            placeOfBirth = arguments.getSerializable(String::class.java.simpleName) as String

            val addresses :List<Address> = Geocoder(this)
                .getFromLocationName(placeOfBirth, 1)
            if (addresses.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                val place = LatLng(latitude, longitude)
                mMap.addMarker(MarkerOptions().position(place).title(placeOfBirth))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
                val zoom  = CameraUpdateFactory.zoomTo(7F)
                mMap.animateCamera(zoom)
            }

        }
    }
}