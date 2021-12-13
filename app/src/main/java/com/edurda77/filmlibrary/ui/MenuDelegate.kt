package com.edurda77.filmlibrary.ui

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.core.content.ContextCompat.startActivity
import com.edurda77.filmlibrary.R

class MenuDelegate (
    val item: MenuItem
    ){

    fun setMenu(context: Context, item: MenuItem): MenuItem {
        when (item.itemId) {
            R.id.start -> {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
            R.id.action_search -> {
                val intent = Intent(context, SearchActivity::class.java)
                context.startActivity(intent)
            }
            R.id.people_search -> {
                val intent = Intent(context, SearchPeopleActivity::class.java)
                context.startActivity(intent)
            }
            R.id.nots -> {
                val intent = Intent(context, NotesActivity::class.java)
                context.startActivity(intent)
            }
            R.id.custom -> {
                val intent = Intent(context, CustomActivity::class.java)
                context.startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(context, AboutActivity::class.java)
                context.startActivity(intent)
            }
        }
        return item
    }
}