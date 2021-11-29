package com.edurda77.filmlibrary.data

import java.io.Serializable

data class NoteMovie (
    val idNote: Int,
    val titleNote: String,
    val contentNote: String

) : Serializable