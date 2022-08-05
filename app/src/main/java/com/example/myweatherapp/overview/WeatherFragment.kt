package com.example.myweatherapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {
    //viewModel
    private val sharedViewModel: WeatherFragmentViewModel by activityViewModels()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather,container, false)
        return binding.root //view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding layout element
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = sharedViewModel
        binding.weatherFragment = this@WeatherFragment
        binding.daysDetail.setOnClickListener { onDetailButtonClick() }
    }

    fun onButtonClick() {
        //apply api request w/ your location
        val customLocation = binding.editText.text.toString()
        sharedViewModel.getMainData(customLocation)
    }

    private fun onDetailButtonClick() {
        findNavController().navigate(R.id.action_weatherFragment_to_weatherDetailFragment)
    }

}