package me.chayan.ytplayer.youTubePlayer

import java.util.regex.Pattern


/**
 * Extract medium quality thumbnail
 */
fun String.getThumbnail(): String {
    return "http://img.youtube.com/vi/${this.getVideoId()}/mqdefault.jpg"
}

/**
 * Regex is to extract Video Id from url
 *
 * http://www.youtube.com/watch?v=0zM3nApSvMg&feature=feedrec_grec_index
 * http://www.youtube.com/user/IngridMichaelsonVEVO#p/a/u/1/QdK8U-VIH_o
 * http://www.youtube.com/v/0zM3nApSvMg?fs=1&amp;hl=en_US&amp;rel=0
 * https://www.youtube.com/watch?v=0zM3nApSvMg#t=0m10s
 * http://www.youtube.com/embed/0zM3nApSvMg?rel=0
 * http://www.youtube.com/watch?v=0zM3nApSvMg
 * https://www.youtube.com/watch?v=aqz-KE-bpKQ
 * http://youtu.be/0zM3nApSvMg
 * https://youtube.com/shorts/0dPkkQeRwTI?feature=share
 * https://youtube.com/shorts/0dPkkQeRwTI
 */
fun String.getVideoId(): String? {
    val reg = "(youtu.*be.*)/(watch\\?v=|embed/|v|shorts|)(.*?((?=[&#?])|\$))"
    val pattern = Pattern.compile(reg)
    val matcher = pattern.matcher(this)
    return if (matcher.find()) matcher.group(3) else null
}
