package com.example.myweatherapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.api.RetrofitInstance
import kotlinx.coroutines.launch

//sync status
enum class CurrentStatus { LOADING, DONE, ERROR }

class WeatherFragmentViewModel : ViewModel() {

   /* //LiveData response
    private val _myResponse = MutableLiveData<ApiData>()
    val myResponse: LiveData<ApiData> = _myResponse*/

    //sync progress
    private val _status = MutableLiveData<CurrentStatus>()
    val status: LiveData<CurrentStatus> = _status

    //location text
    private val _location = MutableLiveData("Waiting for data input!")
    val location: LiveData<String> = _location

    //weather info main text
    private val _weather = MutableLiveData<String>()
    val weather: LiveData<String> = _weather

    //weather properties
    private val _lastUpdated = MutableLiveData<String>()
    val lastUpdated: LiveData<String> = _lastUpdated
    private val _wind = MutableLiveData<String>()
    val wind: LiveData<String> = _wind
    private val _windDeg = MutableLiveData<String>()
    val windDeg: LiveData<String> = _windDeg
    private val _windDir = MutableLiveData<String>()
    val windDir: LiveData<String> = _windDir
    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String> = _humidity
    private val _cloud = MutableLiveData<String>()
    val cloud: LiveData<String> = _cloud
    private val _uv = MutableLiveData<String>()
    val uv: LiveData<String> = _uv
    private val _uvInfo = MutableLiveData<String>()
    val uvInfo: LiveData<String> = _uvInfo
    private val _vision = MutableLiveData<String>()
    val vision: LiveData<String> = _vision


    fun getData(customLocation: String) {
        viewModelScope.launch {
            _status.value = CurrentStatus.LOADING
            try {
                //full response data
                val response = RetrofitInstance.retrofitService.getLocationDetail(customLocation)

                //add text to location detail
                val city = response.location?.name!!
                val country = response.location?.country!!
                val timeZone = response.location?.tzId!!
                val localTime = response.location?.localtime!!
                val locationDetails = "Your location info:\n" +
                        "Location: $city, $country\n" +
                        "Time Zone: $timeZone\n" +
                        "Sync At: $localTime"
                _location.value = locationDetails
                //update text of weather info properties
                _lastUpdated.value = response.current?.lastUpdated!!
                _wind.value = response.current?.windKph.toString()
                _windDeg.value = response.current?.windDegree.toString()
                _windDir.value = response.current?.windDir.toString()
                _humidity.value = response.current?.humidity.toString()
                _cloud.value = response.current?.cloud.toString()
                _uv.value = response.current?.uv.toString()
                _vision.value = response.current?.visKm.toString()
                _uvInfo.value = if (uv.value!!.toInt() < 6) "Friendly" else "Harmful"
                //main weather text
                val condition = response.current?.condition?.text
                val tempC = response.current?.tempC
                val tempF = response.current?.tempF
                val feelC = response.current?.feelslikeC
                val feelF = response.current?.feelslikeF
                val isDay = if (response.current?.isDay == 1) "Day time" else "Night time"
                val weatherDetail = "${tempC}C / ${tempF}F\n" +
                        "Feels like: ${feelC}C / ${feelF}F\n" +
                        "$condition\n" +
                        isDay
                _weather.value = weatherDetail

                _status.value = CurrentStatus.DONE
            } catch (e: Exception) {
                _status.value = CurrentStatus.ERROR
            }
        }
    }
}