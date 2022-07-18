package me.chayan.ytplayer.youTubePlayer

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import me.chayan.ytplayer.databinding.ActivityYoutubePlayerBinding


class YouTubePlayerActivity : AppCompatActivity() {

    companion object {
        private const val KEY_VIDEO_ID = "VIDEO_ID"
        fun playVideo(context: Context, videoId: String?) {
            val intent = Intent(context, YouTubePlayerActivity::class.java)
            intent.putExtra(KEY_VIDEO_ID, videoId)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityYoutubePlayerBinding

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var videoId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enter landscape with full screen mode
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        binding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullscreen()

        videoId = intent.getStringExtra(KEY_VIDEO_ID)!!

        youTubePlayerView = binding.youtubeView
        binding.ivClose.setOnClickListener { finish() }

        initYouTubePlayerView()
    }


    private fun initYouTubePlayerView() {
        // The player will automatically release itself when the fragment is destroyed.
        // The player will automatically pause when the fragment is stopped
        // If you don't add YouTubePlayerView as a lifecycle observer, you will have to release it manually.
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0F)
            }
        })
        // Play in full screen
        youTubePlayerView.enterFullScreen()
    }

    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }
}