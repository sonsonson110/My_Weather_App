package com.example.myweatherapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myweatherapp.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {
    //viewModel
    private val viewModel: WeatherFragmentViewModel by viewModels()

    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        //binding layout element
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.weatherFragment = this@WeatherFragment

        return binding.root //view
    }

    fun onButtonClick() {
        //apply api request w/ your location
        val customLocation = binding.editText.text.toString()
        viewModel.getData(customLocation)

        /*//add text to detail

        val city = viewModel.myResponse.value?.location?.name.toString()
        val country = viewModel.myResponse.value?.location?.country.toString()
        val timeZone = viewModel.myResponse.value?.location?.tzId.toString()
        val localTime = viewModel.myResponse.value?.location?.localtime.toString()
        val locationDetails = "Your location info:\n" +
                "Location: $city, $country\n" +
                "Time Zone: $timeZone\n" +
                "Sync At: $localTime"
        binding.locationDetails.text = locationDetails

        //add weather info

        val lastUpdated = viewModel.myResponse.value?.current?.lastUpdated.toString()
        val tempC = viewModel.myResponse.value?.current?.tempC.toString()
        val feelC = viewModel.myResponse.value?.current?.feelslikeC.toString()
        val isDay = when (viewModel.myResponse.value?.current?.tempF.toString()) {
            "0" -> "Night Time"
            else -> "Day Time"
        }
        val wind = viewModel.myResponse.value?.current?.windKph.toString()
        val humidity = viewModel.myResponse.value?.current?.humidity.toString()
        val cloud = viewModel.myResponse.value?.current?.cloud.toString()
        val uv = viewModel.myResponse.value?.current?.uv.toString()
        val uvInfo = if (uv.toInt() < 6) "Friendly" else "Harmful"
        val weatherDetail = "Current weather info" +
                "Last Updated: $lastUpdated\n" +
                "Temperature: $tempC | Feels like: $feelC\n" +
                "Cloud: $cloud%\n" +
                "Humidity: $humidity%\n" +
                "UV level: $uv ($uvInfo)\n" +
                "Wind: $wind Kpm"
        binding.weatherInfo.text = weatherDetail*/
    }

}