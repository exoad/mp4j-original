package app.core

import app.global.Items
import app.global.Sources
import kotlin.Throws
import java.io.IOException
import java.io.BufferedWriter
import java.io.FileWriter

/**
 * @author Jack Meng
 */
class LifePreserver(private val data: String) {
    /**
     * @throws IOException
     */
    @Throws(IOException::class)
    fun saveToPrevDir() {
        val bw = BufferedWriter(FileWriter(Items.items[1].toString() + "/" + Sources.LIFEPRESERVER_PREVDIR))
        bw.write(data)
        bw.flush()
        bw.close()
    }
}