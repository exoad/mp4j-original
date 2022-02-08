package app.global

/**
 * @author Jack Meng
 */
object SystemType {
    fun osCXXExec(): String {
        return if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            ".exe"
        } else {
            ".out"
        }
    }

    fun removeFileExtension(f: String): String {
        return f.substring(0, f.lastIndexOf('.'))
    }
}