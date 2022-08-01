package com.example.myweatherapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.api.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherFragmentViewModel : ViewModel() {

    //LiveData response
    private val _myResponse = MutableLiveData<String>("Hi")
    val myResponse: LiveData<String> = _myResponse

    fun getData(customLocation: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.retrofitService.getLocationDetail(customLocation)
                _myResponse.value = response.location?.name!!
            } catch (e: Exception) {
                _myResponse.value = "Fetching failure"
            }
        }
    }
}