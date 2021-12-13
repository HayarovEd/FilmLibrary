package com.edurda77.filmlibrary.domain

import android.content.Context
import com.google.android.gms.maps.GoogleMap

interface LocationRepo {
    fun setLocation (place:String, context:Context, map: GoogleMap)
}