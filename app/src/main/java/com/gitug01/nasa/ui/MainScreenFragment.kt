package com.gitug01.nasa.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gitug01.nasa.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class MainScreenFragment : Fragment() {

    private var image: ImageView? = null
    private var gettingImage: GettingImage? = null
    private var imageUrl: String? = null

    override fun onAttach(context: Context) {
        this.gettingImage = context as GettingImage
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
    }

    private fun assignmentValues(){
        CoroutineScope(Dispatchers.IO).launch {
            val url = gettingImage!!.getImageUrl()
            withContext(Dispatchers.Main){
                Picasso.get().load(url).into(image)
            }
        }
    }

    private fun setImage(){

    }

    interface GettingImage {
        suspend fun getImageUrl(): String
    }
}