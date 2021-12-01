package com.edurda77.filmlibrary.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class People(
    @SerializedName("birthday")
    val birthdayPeople: String,
    @SerializedName("id")
    val idPeoplePeople: Int,
    @SerializedName("name")
    val namePeople: String,
    @SerializedName("biography")
    val biographyPeople: String,
    @SerializedName("place_of_birth")
    val placeBirthPeople: String,
    @SerializedName("profile_path")
    val profilePathPeople: String
): Serializable