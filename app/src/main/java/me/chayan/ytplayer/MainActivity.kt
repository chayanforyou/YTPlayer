package me.chayan.ytplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.chayan.ytplayer.adapter.VideoListAdapter
import me.chayan.ytplayer.databinding.ActivityMainBinding
import me.chayan.ytplayer.models.Video

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = VideoListAdapter(getVideoList())
        binding.recyclerView.adapter = adapter
    }

    private fun getVideoList(): List<Video> {
        val videos: ArrayList<Video> = arrayListOf()
        videos.add(Video("Moana Official Trailer", "https://www.youtube.com/watch?v=LKFuXETZUsI"))
        videos.add(Video("Big Buck Bunny 60fps 4K - Official Blender Foundation Short Film", "https://www.youtube.com/watch?v=aqz-KE-bpKQ"))
        videos.add(Video("The Sea Beast | Official Trailer | Netflix", "https://www.youtube.com/watch?v=P-E-IGQCsPo"))
        videos.add(Video("Lightyear | Official Trailer", "https://www.youtube.com/watch?v=BwZs3H_UN3k"))
        videos.add(Video("Ice Age: Scrat Tales | Official Trailer | Disney+", "https://www.youtube.com/watch?v=QFwJJywzbDs"))
        return videos
    }
}