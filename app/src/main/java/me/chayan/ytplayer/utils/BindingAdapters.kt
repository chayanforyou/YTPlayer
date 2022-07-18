package me.chayan.ytplayer.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(view.context).load(url).into(view)
    }
}