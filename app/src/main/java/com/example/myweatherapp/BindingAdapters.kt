package com.example.myweatherapp

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.myweatherapp.overview.CurrentStatus

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