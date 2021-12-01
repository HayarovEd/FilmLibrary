package com.edurda77.filmlibrary.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.filmlibrary.R
import com.edurda77.filmlibrary.data.People
import com.edurda77.filmlibrary.data.ResultSearchedPeople
import com.edurda77.filmlibrary.databinding.ActivitySearchPeopleBinding
import com.edurda77.filmlibrary.domain.TheMDBRepoUseCaseSync


class SearchPeopleActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivitySearchPeopleBinding
    private val goSearchedPeople: TheMDBRepoUseCaseSync by lazy { app.theMDBRepoUseCaseSync }
    private val resultSearch = emptyList<ResultSearchedPeople>().toMutableList()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchPeopleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar()
        binding.goSearchPeople.setOnClickListener {
            resultSearch.clear()
            val searchString = binding.searchPeople.text.toString()
            Thread {
                goSearchedPeople.getReposForSearchPeopleSync(searchString)?.forEach {
                    resultSearch.add(it)
                }
                runOnUiThread {
                    setOotRecycledView()

                }
            }.start()
        }
    }

    private fun setOotRecycledView() {

        val recyclerView: RecyclerView = binding.itemSearchPeople
        val goIDPeople: TheMDBRepoUseCaseSync by lazy { app.theMDBRepoUseCaseSync }
        recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager
                .VERTICAL, false
        )

        val stateClickListener: PeopleAdapter.OnStateClickListener =
            object : PeopleAdapter.OnStateClickListener {
                override fun onStateClick(people: ResultSearchedPeople, position: Int) {

                    Thread {
                        val iDPeople = goIDPeople.getReposForIdPeopleSync(people)
                        runOnUiThread {
                            val intent = Intent(this@SearchPeopleActivity, PeopleActivity::class.java)
                            intent.putExtra(People::class.java.simpleName, iDPeople)

                            startActivity(intent)
                        }


                    }.start()


                }
            }


        recyclerView.adapter = PeopleAdapter(resultSearch, stateClickListener)


    }


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
}