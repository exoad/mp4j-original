package app.telemetry.api

import app.telemetry.API
import kotlin.Throws
import java.io.IOException
import java.io.BufferedWriter
import java.io.FileWriter
import java.lang.StringBuilder
import app.telemetry.FileIntegrity
import app.CLI
import app.global.cli.CliType
import java.net.URL
import java.util.*

class Wrapper : API {
    @Throws(IOException::class)
    override fun run(): String {
        val sc = Scanner(returnLink().openStream())
        val sb = StringBuilder()
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n")
        }
        sc.close()
        return sb.toString()
    }

    @Throws(IOException::class)
    override fun returnLink(): URL {
        return URL("https://exoad.github.io/MusicPlayer4J/api/version")
    }
}