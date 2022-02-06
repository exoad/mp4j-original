package app.core

import app.core.PropertiesBuffer
import java.io.File
import java.util.Properties
import java.io.FileInputStream
import java.io.IOException

object PropertiesBuffer {
    private fun checkSums(f: File): Boolean {
        return f.exists() && f.isFile && f.canRead() && f.canWrite() && f.length() > 0
    }

    fun readProperties(f: File): Map<Any, Any> {
        if (checkSums(f)) {
            try {
                val p = Properties()
                p.load(FileInputStream(f))
                return p
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return emptyMap()
    }
}