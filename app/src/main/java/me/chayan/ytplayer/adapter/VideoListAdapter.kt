package me.chayan.ytplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.chayan.ytplayer.R
import me.chayan.ytplayer.databinding.RowVideoBinding
import me.chayan.ytplayer.models.Video
import me.chayan.ytplayer.youTubePlayer.YouTubePlayerActivity

class VideoListAdapter(
    private val videos: List<Video>,
) : RecyclerView.Adapter<VideoListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowVideoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_video, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class MyViewHolder(private val binding: RowVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.video = video
            binding.executePendingBindings()

            itemView.setOnClickListener {
                YouTubePlayerActivity.playVideo(itemView.context, video.url)
            }
        }
    }
}