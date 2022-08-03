package com.example.myweatherapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.myweatherapp.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {
    //viewModel
    private val sharedViewModel: WeatherFragmentViewModel by viewModels()

    private lateinit var binding: FragmentWeatherBinding

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        //binding layout element
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = sharedViewModel
        binding.weatherFragment = this@WeatherFragment

        return binding.root //view
    }

    fun onButtonClick() {
        //apply api request w/ your location
        val customLocation = binding.editText.text.toString()
        sharedViewModel.getData(customLocation)
    }

}