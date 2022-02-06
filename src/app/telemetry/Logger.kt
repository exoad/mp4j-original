package app.telemetry

import app.telemetry.API
import kotlin.Throws
import java.io.IOException
import java.io.BufferedWriter
import java.io.FileWriter
import java.lang.StringBuilder
import app.telemetry.FileIntegrity
import app.CLI
import app.global.Items
import app.global.cli.CliType
import java.io.File

class Logger(s: Any) {
    companion object {
        @JvmStatic
        fun log(t: Any): String {
            val time = System.currentTimeMillis()
            val curr = File(Items.items[2] + "/" + "Log_" + time + ".log")
            try {
                BufferedWriter(FileWriter(curr)).use { bw ->
                    bw.write("$time $t")
                    bw.write("Stack Trace Dump\n")
                    bw.write(t.toString())
                    bw.flush()
                    bw.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return curr.absolutePath
        }

        fun clear(): Boolean {
            val curr = File(Items.items[2])
            val files = curr.listFiles()!!
            for (f in files) {
                if (f.name.endsWith(".log")) {
                    if (!f.delete()) {
                        return false
                    }
                }
            }
            return true
        }
    }

    init {
        log(s)
    }
}