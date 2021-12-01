package com.edurda77.filmlibrary.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.ResultSearchedPeople

class PeopleHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_people, parent, false)) {

    private var titlePeople: TextView? = null
    private var picturePeople: ImageView
    private var beginURL = "https://image.tmdb.org/t/p/w500/"


    init {
        titlePeople = itemView.findViewById(R.id.name_people_view)
        picturePeople = itemView.findViewById(R.id.picture_people_view)
    }
    @SuppressLint("SetTextI18n")
    fun bind(people: ResultSearchedPeople) {


            titlePeople?.text =   people.namePeopleSearched
        Glide.with(this.itemView.context)
            .load(beginURL+people.profilePathPeopleSearched)
            .placeholder(R.drawable.video)
            .into(picturePeople)


    }
}