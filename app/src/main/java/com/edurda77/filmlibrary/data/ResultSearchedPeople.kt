package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultSearchedPeople(
    val idPeopleSearched: Int,
    val namePeopleSearched: String,
    @SerializedName("profile_path")
    val profilePathPeopleSearched: String
) : Serializable