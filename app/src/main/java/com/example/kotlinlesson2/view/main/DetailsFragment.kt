package com.example.kotlinlesson2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinlesson2.databinding.FragmentDetailsBinding
import com.example.kotlinlesson2.domain.Weather


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    companion object {
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val BUNDLE_WEATHER_KEY = "key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val weather = (it.getParcelable(BUNDLE_WEATHER_KEY)) ?: Weather()
            setData(weather)
        }
    }

    private fun setData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.name
            cityCoordinates.text = "lat ${weather.city.lat}\n lon ${weather.city.lon}"
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = "${weather.feelsLike}"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}