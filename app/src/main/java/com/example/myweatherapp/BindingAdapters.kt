package com.example.myweatherapp

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myweatherapp.model.ApiData
import com.example.myweatherapp.model.Forecast
import com.example.myweatherapp.model.Forecastday
import com.example.myweatherapp.overview.CurrentStatus
import com.example.myweatherapp.overview.DaysDetailAdapter

@BindingAdapter("apiStatus")
fun bindStatus(statusTextView: TextView, status: CurrentStatus?) {
    if (status == CurrentStatus.LOADING) {
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Fetching data..."
    }
    else if (status == CurrentStatus.DONE) {
        statusTextView.visibility = View.GONE
    }
    else if (status == CurrentStatus.ERROR) {
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Fetching failure!"
    }
}

@BindingAdapter("imageUrl")
fun bindImg(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: ApiData?) {
    val adapter = recyclerView.adapter as DaysDetailAdapter
    val listData = data?.forecast?.forecastday
    adapter.submitList(listData)
    Log.d("DATAFORRECYCLERVIEW", "${listData?.get(0)}")
}
