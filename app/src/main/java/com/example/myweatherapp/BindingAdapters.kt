package com.example.myweatherapp

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.myweatherapp.overview.CurrentStatus

@BindingAdapter("apiStatus")
fun bindStatus(statusTextView: TextView, status: CurrentStatus?) {
    if (status == CurrentStatus.LOADING) {
        statusTextView.visibility = View.VISIBLE
        statusTextView.text = "Fetching data..."
    }
    else if (status == CurrentStatus.DONE) {
        statusTextView.visibility = View.INVISIBLE
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