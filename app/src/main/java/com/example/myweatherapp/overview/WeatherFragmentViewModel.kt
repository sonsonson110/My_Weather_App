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

    //weather info text
    private val _weather = MutableLiveData("...")
    val weather: LiveData<String> = _weather

    fun getData(customLocation: String) {
        viewModelScope.launch {
            _status.value = CurrentStatus.LOADING
            try {
                //full response data
                val response = RetrofitInstance.retrofitService.getLocationDetail(customLocation)

                //add text to location detail
                val city = response.location?.name.toString()
                val country = response.location?.country.toString()
                val timeZone = response.location?.tzId.toString()
                val localTime = response.location?.localtime.toString()
                val locationDetails = "Your location info:\n" +
                        "Location: $city, $country\n" +
                        "Time Zone: $timeZone\n" +
                        "Sync At: $localTime"
                _location.value = locationDetails
                //add text to weather info
                // TODO: remodel this 
                val lastUpdated = response.current?.lastUpdated.toString()
                val tempC = response.current?.tempC.toString()
                val feelC = response.current?.feelslikeC.toString()
                val wind = response.current?.windKph.toString()
                val humidity = response.current?.humidity.toString()
                val cloud = response.current?.cloud.toString()
                val uv = response.current?.uv.toString()
                val uvInfo = if (uv.toInt() < 6) "Friendly" else "Harmful"
                val weatherDetail = "Current weather info\n" +
                        "Last Updated: $lastUpdated\n" +
                        "Temperature: $tempC \n Feels like: $feelC\n" +
                        "Cloud: $cloud%\n" +
                        "Humidity: $humidity%\n" +
                        "UV level: $uv ($uvInfo)\n" +
                        "Wind: $wind Kpm"
                _weather.value = weatherDetail

                _status.value = CurrentStatus.DONE
            } catch (e: Exception) {
                _status.value = CurrentStatus.ERROR
            }
        }
    }
}