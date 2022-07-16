package me.chayan.ytplayer

import java.util.regex.Pattern

object Utils {

    private const val REG = "(youtu.*be.*)/(watch\\?v=|embed/|v|shorts|)(.*?((?=[&#?])|\$))"
    private val PATTERN: Pattern = Pattern.compile(REG)

    fun CharSequence?.getVideoId(): String? {
        if (isNullOrEmpty()) return null
        val matcher = PATTERN.matcher(this)
        if (matcher.find()) return matcher.group(3)
        return null
    }
}