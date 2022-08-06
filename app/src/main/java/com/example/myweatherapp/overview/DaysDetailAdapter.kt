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
            binding.dayDetail = forecastday
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Forecastday>() {
        override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem.dateEpoch == newItem.dateEpoch
        }

        override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysDetailViewHolder {
        return DaysDetailViewHolder(
            WeatherDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DaysDetailViewHolder, position: Int) {
        val forecastday = getItem(position)
        holder.bind(forecastday)
    }
}
