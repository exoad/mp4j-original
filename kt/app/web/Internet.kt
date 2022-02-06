package app.web

import app.global.Items
import app.telemetry.Logger.Companion.log
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

object Internet {
    fun internet(): Boolean {
        try {
            BufferedReader(FileReader(File(Items.items[6]))).use { br ->
                var line = br.readLine()
                while (line != null) {
                    if (line.contains("1")) {
                        return true
                    }
                    line = br.readLine()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            log(e.localizedMessage)
        }
        return false
    }
}