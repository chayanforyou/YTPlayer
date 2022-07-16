package me.chayan.ytplayer.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.chayan.ytplayer.Utils.getVideoId

data class Video(
    val title: String,
    val url: String
) {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["app:imageUrl"])
        fun loadImage(view: ImageView, url: String?) {
            if (!url.isNullOrBlank()) {
                val thumbnailMq = "http://img.youtube.com/vi/" + url.getVideoId() + "/mqdefault.jpg" // medium quality thumbnail
                Glide.with(view.context).load(thumbnailMq).into(view)
            }
        }
    }
}
