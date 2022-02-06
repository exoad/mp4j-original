package build.fileintchecker.global

import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import java.lang.StringBuilder
import build.fileintchecker.global.Exclude
import java.io.BufferedWriter
import java.io.FileWriter

object Exclude {
    const val IGNORE_DEPRECATED = "/deprecated"
    val ndPaths = arrayOf(
            "/icons/audio-type/",
            "/icons/logos/",
            "/icons/others/",
            "/icons/animated/waves/",
            "/icons/animated/waves/paused/",
            "/icons/splash/"
    )
}