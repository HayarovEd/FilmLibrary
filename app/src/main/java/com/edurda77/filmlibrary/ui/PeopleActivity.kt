package com.edurda77.filmlibrary.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.MapsActivity
import com.edurda77.filmlibrary.data.People
import com.edurda77.filmlibrary.databinding.ActivityPeopleBinding

class PeopleActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private var beginURL = "https://image.tmdb.org/t/p/w500"
    private lateinit var binding: ActivityPeopleBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPeopleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        val nameText: TextView = binding.namePeople
        val idText: TextView = binding.idPeople
        val birthdayPeopleText: TextView = binding.birthdayPeople
        val placeBirthPeopleText: TextView = binding.placeBirthPeople
        val biographyPeopleText: TextView = binding.biographyPeople
        val picture: ImageView = binding.picturePeople
        val arguments = intent.extras
        val people: People
        if (arguments != null) {
            people = arguments.getSerializable(People::class.java.simpleName) as People
            nameText.text = people.namePeople
            idText.text = idText.text.toString() + people.idPeoplePeople.toString()

            birthdayPeopleText.text = birthdayPeopleText.text.toString() + people.birthdayPeople
            placeBirthPeopleText.text = placeBirthPeopleText.text.toString() + people.placeBirthPeople
            biographyPeopleText.text = biographyPeopleText.text.toString() + people.biographyPeople


            Glide.with(this).load(beginURL + people.profilePathPeople)
                //.override(320, 480)
                .placeholder(R.drawable.video).into(picture)
            binding.placeBirthPeople.setOnClickListener{
                checkPermission()
                if (people.placeBirthPeople!=null) {
                    val place = people.placeBirthPeople
                    runOnUiThread {
                        val intent = Intent(this@PeopleActivity, MapsActivity::class.java)
                        intent.putExtra(String::class.java.simpleName, place)

                        startActivity(intent)
                    }
                }
            }
        }

    }
    //binding.requestGeolocation.setOnClickListener
    private fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.start -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.people_search -> {
                val intent = Intent(this, SearchPeopleActivity::class.java)
                startActivity(intent)
            }
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.nots -> {
                val intent = Intent(this, NotesActivity::class.java)
                startActivity(intent)
            }
            R.id.custom -> {
                val intent = Intent(this, CustomActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //TODO
        } else {
            ActivityCompat.requestPermissions(this, Array(2) {
                Manifest.permission.ACCESS_FINE_LOCATION
                Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //TODO


        } else {
            Toast.makeText(
                this,
                "Необходимо для определения места рождения актера на карте",
                Toast.LENGTH_LONG
            ).show()
            //checkPermission()
        }
    }
}