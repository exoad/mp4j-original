package app.global

import java.util.*

/**
 * @author Jack Meng
 */
object SystemType {
    fun osCXXExec(): String {
        return if (System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")) {
            ".exe"
        } else {
            ".out"
        }
    }

    fun removeFileExtension(f: String): String {
        return f.substring(0, f.lastIndexOf('.'))
    }
}