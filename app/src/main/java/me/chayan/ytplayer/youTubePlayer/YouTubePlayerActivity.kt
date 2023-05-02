package me.chayan.ytplayer.youTubePlayer

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import me.chayan.ytplayer.databinding.ActivityYoutubePlayerBinding


class YouTubePlayerActivity : AppCompatActivity() {

    companion object {
        private const val KEY_VIDEO_ID = "VIDEO_ID"
        fun playVideo(context: Context, videoUrl: String) {
            val intent = Intent(context, YouTubePlayerActivity::class.java)
            intent.putExtra(KEY_VIDEO_ID, videoUrl.getVideoId())
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityYoutubePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enter landscape with full screen mode
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        hideSystemBars()

        binding.ivClose.setOnClickListener { finish() }

        initYouTubePlayerView()
    }


    private fun initYouTubePlayerView() {
        // The player will automatically release itself when the fragment is destroyed.
        binding.youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                intent.getStringExtra(KEY_VIDEO_ID)?.let {
                    youTubePlayer.loadVideo(it, 0F)
                }
            }
        })

        // If you don't add YouTubePlayerView as a lifecycle observer, you will have to release it manually.
        lifecycle.addObserver(binding.youtubeView)
    }

    /*
     * Hide system bars for immersive mode
     */
    private fun hideSystemBars() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // Configure the behavior of the hidden system bars.
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide the system bars.
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

}