package com.mobilion.androidbase.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("url")
fun ImageView.loadImage(url: String?) {
    if(!url.isNullOrEmpty()){
       // Glide implementation
    }
}