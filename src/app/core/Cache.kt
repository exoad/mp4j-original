package app.core

import app.global.Items
import java.io.File
import kotlin.Throws
import java.io.IOException
import java.util.*

/**
 * @author Jack Meng
 */
object Cache {
    @JvmStatic
    @Throws(IOException::class)
    fun cleanCache(): Boolean {
        val cache = File(Items.items[0])
        if (cache.isDirectory) {
            for (f in Objects.requireNonNull(cache.listFiles())) {
                f.delete()
            }
            val audio = File(Items.items[0].toString() + "\\audio")
            if (audio.isDirectory) {
                for (f in Objects.requireNonNull(audio.listFiles())) {
                    f.delete()
                }
            }
            return true
        }
        return false
    }
}