package com.gitug01.nasa.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gitug01.nasa.R
import com.gitug01.nasa.domain.entity.MarsWeatherMainEntity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarsFragment : Fragment() {

    private lateinit var marsImage: ImageView
    private var getMarsWeather: GetMarsWeather? = null
    private lateinit var averageTemp: TextView
    private lateinit var maxTemp: TextView
    private lateinit var minTemp: TextView
    private lateinit var averageAtm: TextView
    private lateinit var maxAtm: TextView
    private lateinit var minAtm: TextView

    override fun onAttach(context: Context) {

        this.getMarsWeather = context as GetMarsWeather

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        assignmentValues()
    }

    fun init(view: View) {
        marsImage = view.findViewById(R.id.mars_image)

        averageTemp = view.findViewById(R.id.average_temp)
        maxTemp = view.findViewById(R.id.max_temp)
        minTemp = view.findViewById(R.id.min_temp)

        averageAtm = view.findViewById(R.id.average_atmospheric_pressure)
        maxAtm = view.findViewById(R.id.max_atmospheric_pressure)
        minAtm = view.findViewById(R.id.min_atmospheric_pressure)
    }

    fun assignmentValues() {
        Picasso.get()
            .load("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG")
            .into(marsImage)

        CoroutineScope(Dispatchers.IO).launch {
            val marsWeatherEntity = getMarsWeather!!.getWeather()
            withContext(Dispatchers.Main) {
                Log.d("text", marsWeatherEntity.First_UTC)
                averageTemp.text = "average: " + marsWeatherEntity.AT.av
                maxTemp.text = "max: " + marsWeatherEntity.AT.mx
                minTemp.text = "min: " + marsWeatherEntity.AT.mn

                averageAtm.text = "average: " + marsWeatherEntity.PRE.av
                maxAtm.text = "max: " + marsWeatherEntity.PRE.mx
                minAtm.text = "min: " + marsWeatherEntity.PRE.mn
            }
        }
    }

    interface GetMarsWeather {
        suspend fun getWeather(): MarsWeatherMainEntity
    }
}