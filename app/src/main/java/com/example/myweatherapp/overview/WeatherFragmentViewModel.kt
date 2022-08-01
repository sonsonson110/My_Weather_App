package com.example.myweatherapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.api.RetrofitInstance
import com.example.myweatherapp.model.ApiData
import kotlinx.coroutines.launch
//sync status
enum class CurrentStatus { LOADING, DONE, ERROR }

class WeatherFragmentViewModel : ViewModel() {

    //LiveData response
    private val _myResponse = MutableLiveData<ApiData>()
    val myResponse: LiveData<ApiData> = _myResponse

    //sync progress
    private val _status = MutableLiveData<CurrentStatus>()
    val status: LiveData<CurrentStatus> = _status

    //location properties
    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    fun getData(customLocation: String) {
        viewModelScope.launch {
            _status.value = CurrentStatus.LOADING
            try {
                //full response data
                val response = RetrofitInstance.retrofitService.getLocationDetail(customLocation)
                _city.value = response.location!!.name!!
                _status.value = CurrentStatus.DONE
            } catch (e: Exception) {
                _status.value = CurrentStatus.ERROR
            }
        }
    }
}