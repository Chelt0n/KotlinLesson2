package com.example.kotlinlesson2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlesson2.R
import com.example.kotlinlesson2.databinding.FragmentMainBinding
import com.example.kotlinlesson2.domain.Weather
import com.example.kotlinlesson2.view.OnItemViewClickListener
import com.example.kotlinlesson2.viewmodel.AppState
import com.example.kotlinlesson2.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), OnItemViewClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }
    private var isDataSetRus: Boolean = true
    private val adapter = MainFragmentAdapter()
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        adapter.setOnItemViewClickListener(this)
        binding.mainFragmentFAB.setOnClickListener {
            isDataSetRus = !isDataSetRus
            if (isDataSetRus) {
                viewModel.getWeatherFromLocalSourceRussia()
                binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
            } else {
                viewModel.getWeatherFromLocalSourceWorld()
                binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
            }
        }
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            renderData(it)
        })
        viewModel.getWeatherFromLocalSourceRussia()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar.make(binding.root, "Ошибка", Snackbar.LENGTH_LONG).show()
            }
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                adapter.setWeather(weather)
                Snackbar.make(binding.root, "Готово", Snackbar.LENGTH_LONG).show()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_WEATHER_KEY, weather)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("").commit()

    }
}