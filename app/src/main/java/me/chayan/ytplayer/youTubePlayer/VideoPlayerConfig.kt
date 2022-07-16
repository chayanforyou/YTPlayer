package me.chayan.ytplayer.youTubePlayer

class VideoPlayerConfig {

    companion object {
        //Minimum Video you want to buffer while Playing
        const val MIN_BUFFER_DURATION: Int = 3000
        //Max Video you want to buffer during PlayBack
        const val MAX_BUFFER_DURATION: Int = 5000
        //Min Video you want to buffer before start Playing it
        const val MIN_PLAYBACK_START_BUFFER: Int = 1500
        //Min video You want to buffer when user resumes video
        const val MIN_PLAYBACK_RESUME_BUFFER: Int = 5000
        const val DEFAULT_VIDEO_URL: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    }
}