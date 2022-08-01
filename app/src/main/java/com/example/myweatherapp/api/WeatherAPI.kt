package com.example.myweatherapp.api

import com.example.myweatherapp.model.ApiData
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://weatherapi-com.p.rapidapi.com"
//giao tiếp với server
interface WeatherAPI {
    @Headers(
        "X-RapidAPI-Key: 2a80c97814msh98c8e99b73672adp118373jsn5db1f18d86c2",
        "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    )
    @GET("current.json?q=Hanoi")
    suspend fun getLocationDetail() : ApiData
}
//build
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
/*private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()*/
object RetrofitInstance {
    val retrofitService: WeatherAPI by lazy {
        retrofit.create(WeatherAPI::class.java)
    }
}
