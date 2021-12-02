package com.edurda77.filmlibrary.data

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.edurda77.filmlibrary.domain.LocationRepo
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class LocationRepoImpl: LocationRepo {


    override fun setLocation(place: String, context: Context, mMap: GoogleMap) {
        val addresses :List<Address> = Geocoder(context)
            .getFromLocationName(place, 1)
        if (addresses.isNotEmpty()) {
            val latitude = addresses[0].latitude
            val longitude = addresses[0].longitude
            val placeImpl = LatLng(latitude, longitude)
            mMap.addMarker(MarkerOptions().position(placeImpl).title(place))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(placeImpl))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(7F))
        }
    }

}