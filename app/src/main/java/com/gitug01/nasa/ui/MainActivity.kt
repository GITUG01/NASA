package com.gitug01.nasa.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gitug01.nasa.R
import com.gitug01.nasa.domain.ImageRepo
import com.gitug01.nasa.domain.app
import com.gitug01.nasa.domain.entity.ImageEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity(), MainScreenFragment.GettingImageEntity {

    private val imageRepo: ImageRepo by lazy { app.imageRepo }
    private var toolbar: Toolbar? = null
    private var bottomNavigationMenu: BottomNavigationView? = null
    private lateinit var mainScreenFragment: MainScreenFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_Moon)

        toolbar = findViewById(R.id.toolbar)

        setContentView(R.layout.activity_main)

        mainScreenFragment = MainScreenFragment()
        openFragment(mainScreenFragment, false)

        bottomNavigationMenu = findViewById(R.id.bottom_navigation1)

        val listener = NavigationBarView.OnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.mars -> MarsFragment()
                R.id.moon -> MoonFragment()
                R.id.settings -> SettingsFragment()
                else -> MarsFragment()
            }
            openFragment(fragment, true)
            true
        }

        bottomNavigationMenu!!.setOnItemSelectedListener(listener)
    }

    private fun openFragment(
        @NonNull fragment: Fragment,
        addToBackStack: Boolean
    ) {
        when (addToBackStack) {
            false -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragments_container, fragment)
                .commit()
            true -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragments_container, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.today_image -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            R.id.yesterday_image -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override suspend fun getFilmEntity(): ImageEntity {
        return CoroutineScope(Dispatchers.Main).async {
            imageRepo.getImageOfTheDayAsync("PfzeIs0lTnaqJMoDY1KaUgfWGvylfblrObPK5trc")
        }.await()
    }

    override fun onBackPressed() {
        if (mainScreenFragment.isResumed){
            super.onBackPressed()
        } else {
            openFragment(MainScreenFragment(), false)
        }

    }
}