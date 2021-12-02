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


    override fun setLocation(place: String, context: Context, map: GoogleMap) {
        val addresses :List<Address> = Geocoder(context)
            .getFromLocationName(place, 1)
        if (addresses.isNotEmpty()) {
            val latitude = addresses[0].latitude
            val longitude = addresses[0].longitude
            val placeImpl = LatLng(latitude, longitude)
            map.addMarker(MarkerOptions().position(placeImpl).title(place))
            map.moveCamera(CameraUpdateFactory.newLatLng(placeImpl))
            map.animateCamera(CameraUpdateFactory.zoomTo(7F))
        }
    }

}