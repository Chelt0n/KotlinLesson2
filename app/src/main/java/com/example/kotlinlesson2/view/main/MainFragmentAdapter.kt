package com.example.kotlinlesson2.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson2.R
import com.example.kotlinlesson2.domain.Weather


class MainFragmentAdapter (
    private val listener: (Weather) -> Unit
        ): RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var weatherData: MutableList<Weather> = mutableListOf()

    fun setWeather(data: List<Weather>) {
        weatherData.clear()
        weatherData.addAll(data)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        return MainFragmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    override fun getItemCount() = weatherData.size

    inner class MainFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun render(weather: Weather) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                weather.city.name
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "РАБОТАЕТ", Toast.LENGTH_SHORT).show()
                listener.invoke(weather)
            }
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "РАБОТАЕТ", Toast.LENGTH_SHORT).show()
                listener.invoke(weather)
            }
        }
    }
}