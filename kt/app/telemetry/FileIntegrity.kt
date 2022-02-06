package app.telemetry

import app.telemetry.API
import kotlin.Throws
import java.io.IOException
import java.io.BufferedWriter
import java.io.FileWriter
import java.lang.StringBuilder
import app.telemetry.FileIntegrity
import app.CLI
import app.global.cli.CliType
import java.io.File
import java.net.URL
import java.util.*

class FileIntegrity {
    @get:Throws(IOException::class)
    val isGood: String
        get() {
            val sc = Scanner(fileList.openStream())
            while (sc.hasNextLine()) {
                val str = sc.nextLine()
                if (!File("./resource/" + sc.nextLine()).exists()) {
                    CLI.print("NOT FOUND: " + File("./resource/$str").name, CliType.ERROR)
                    sc.close()
                    return str
                }
                CLI.print("FOUND: " + File("./resource/$str").name, CliType.SUCCESS)
            }
            sc.close()
            return "0"
        }

    companion object {
        private lateinit var fileList: URL

        @get:Throws(IOException::class)
        val allFiles: String
            get() {
                val sc = Scanner(fileList.openStream())
                val sb = StringBuilder()
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine()).append("\n")
                }
                sc.close()
                return sb.toString()
            }
    }

    init {
        fileList = javaClass.getResource("/files.txt")
    }
}