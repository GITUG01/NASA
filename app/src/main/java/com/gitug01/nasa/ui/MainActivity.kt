package com.gitug01.nasa.ui

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gitug01.nasa.R
import com.gitug01.nasa.domain.ImageRepo
import com.gitug01.nasa.domain.app
import com.gitug01.nasa.domain.entity.ImageEntity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), MainScreenFragment.GettingImageEntity {

    private val imageRepo: ImageRepo by lazy { app.imageRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.fragments_container, MainScreenFragment(), false)

        var b: String? = null

        CoroutineScope(Dispatchers.IO).launch {
            val a = imageRepo.getImageOfTheDayAsync("PfzeIs0lTnaqJMoDY1KaUgfWGvylfblrObPK5trc").url
            Log.d("@@@", b.toString())
            withContext(Dispatchers.Main){
                b = a
                Log.d("test", b.toString())
            }

        }

        Log.d("@@@", b.toString())

    }

    private fun replaceFragment(
        @IdRes containerViewId: Int,
        @NonNull fragment: Fragment,
        addToBackStack: Boolean
    ) {
        when (addToBackStack) {
            false -> supportFragmentManager.beginTransaction().replace(containerViewId, fragment)
                .commit()
            true -> supportFragmentManager.beginTransaction().replace(containerViewId, fragment)
                .addToBackStack(null).commit()
        }
    }

    override suspend fun getFilmEntity(): ImageEntity {
        return CoroutineScope(Dispatchers.Main).async {
            imageRepo.getImageOfTheDayAsync("PfzeIs0lTnaqJMoDY1KaUgfWGvylfblrObPK5trc")
        }.await()
    }
}