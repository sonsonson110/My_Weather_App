package com.example.myweatherapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.WeatherDetailItemBinding
import com.example.myweatherapp.model.Forecastday

class DaysDetailAdapter :
    ListAdapter<Forecastday, DaysDetailAdapter.DaysDetailViewHolder>(DiffCallback) {

    class DaysDetailViewHolder(private var binding: WeatherDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastday: Forecastday) {
            binding.date.text = forecastday.date
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Forecastday>() {
        override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysDetailViewHolder {
        return DaysDetailViewHolder(
            WeatherDetailItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: DaysDetailViewHolder, position: Int) {
        val forecastday = getItem(position)
        holder.bind(forecastday)
    }
}
