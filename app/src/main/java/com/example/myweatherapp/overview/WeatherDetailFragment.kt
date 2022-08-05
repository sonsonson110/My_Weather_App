package com.example.myweatherapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweatherapp.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment() {

    //shared view model
    private val sharedViewModel: WeatherFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentWeatherDetailBinding.inflate(inflater)

        sharedViewModel.getDaysData()

        binding.lifecycleOwner = this
        binding.viewModel = sharedViewModel
        binding.recyclerView.apply {
            adapter = DaysDetailAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root
    }
}
// TODO: Fix the recyclerView layout!! 