package com.gitug01.nasa.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gitug01.nasa.R
import com.gitug01.nasa.domain.entity.ImageEntity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class MainScreenFragment : Fragment() {

    private var image: ImageView? = null
    private var gettingImageEntity: GettingImageEntity? = null
    private var title: TextView? = null
    private var description: TextView? = null

    override fun onAttach(context: Context) {
        this.gettingImageEntity = context as GettingImageEntity
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        assignmentValues()

    }

    private fun init(view: View){
        image = view.findViewById(R.id.fragment_image)
        title = view.findViewById(R.id.title_tv)
        description = view.findViewById(R.id.description_tv)
    }

    private fun assignmentValues(){
        CoroutineScope(Dispatchers.IO).launch {
            val filmEntity = gettingImageEntity!!.getFilmEntity()
            withContext(Dispatchers.Main){
                title?.text = filmEntity.title
                Picasso.get().load(filmEntity.url).into(image)
                description?.text = filmEntity.explanation
            }
        }

    }

    interface GettingImageEntity {
        suspend fun getFilmEntity(): ImageEntity
    }
}