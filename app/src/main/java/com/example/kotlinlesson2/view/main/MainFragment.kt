package com.example.kotlinlesson2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlesson2.R
import com.example.kotlinlesson2.databinding.FragmentMainBinding
import com.example.kotlinlesson2.domain.Weather
import com.example.kotlinlesson2.extensions.showSnackbarWithoutAction
import com.example.kotlinlesson2.viewmodel.*
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }
    private var isDataSetRus: Boolean = true
    private val adapter = MainFragmentAdapter(::onItemClick)
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter
            mainFragmentFAB.setOnClickListener {
                isDataSetRus = !isDataSetRus
                if (isDataSetRus) {
                    viewModel.getWeatherFromLocalSourceRussia()
                    mainFragmentFAB.setImageResource(R.drawable.ic_russia)
                } else {
                    viewModel.getWeatherFromLocalSourceWorld()
                    mainFragmentFAB.setImageResource(R.drawable.ic_earth)
                }
            }
        }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            renderData(it)
        })
        viewModel.getWeatherFromLocalSourceRussia()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is Fail -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                binding.root.showSnackbarWithoutAction(R.string.fail)
            }
            Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                adapter.setWeather(weather)
                binding.root.showSnackbarWithoutAction(R.string.success)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_WEATHER_KEY, weather)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("").commit()

    }
}