package com.example.myweatherapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private var isWeatherGenerated = false

    override fun onResume() {
        super.onResume()
        //noinspection RestrictedApi
        (activity as AppCompatActivity).supportActionBar?.setShowHideAnimationEnabled(false) //buggy part
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
        isWeatherGenerated = true
        sharedViewModel.getMainData(customLocation)
        sharedViewModel.getDaysData(customLocation)
    }

    private fun onDetailButtonClick() {
        if (isWeatherGenerated)
            findNavController().navigate(R.id.action_weatherFragment_to_weatherDetailFragment)
        else
            Toast.makeText(requireContext(),"Please enter location first", Toast.LENGTH_SHORT).show()
    }

}