package com.example.myweatherapp.api

import com.example.myweatherapp.model.ApiData
import com.example.myweatherapp.model.Forecast
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://weatherapi-com.p.rapidapi.com"
//giao tiếp với server
interface WeatherAPI {
    @Headers(
        "X-RapidAPI-Key: 2a80c97814msh98c8e99b73672adp118373jsn5db1f18d86c2",
        "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    )
    @GET("current.json")
    suspend fun getLocationDetail(
        @Query("q") q: String
    ) : ApiData

    @Headers(
        "X-RapidAPI-Key: 2a80c97814msh98c8e99b73672adp118373jsn5db1f18d86c2",
        "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    )
    @GET("forecast.json?days=14")
    suspend fun getForecast(
        @Query("q") q: String
    ) : ApiData
}
//build
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object RetrofitInstance {
    val retrofitService: WeatherAPI by lazy {
        retrofit.create(WeatherAPI::class.java)
    }
}
