package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName

data class People(
    val birthdayPeople: String,
    val idPeoplePeople: Int,
    val namePeople: String,
    val biographyPeople: String,
    @SerializedName("place_of_birth")
    val placeBirthPeople: String,
    @SerializedName("profile_path")
    val profilePathPeople: String
)