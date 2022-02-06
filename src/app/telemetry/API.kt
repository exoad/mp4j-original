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
import java.net.URL

interface API {
    @Throws(IOException::class)
    fun run(): String

    @Throws(IOException::class)
    fun returnLink(): URL
}