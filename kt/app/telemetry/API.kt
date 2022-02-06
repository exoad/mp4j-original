package app.telemetry

import java.io.IOException
import java.net.URL

interface API {
    @Throws(IOException::class)
    fun run(): String

    @Throws(IOException::class)
    fun returnLink(): URL
}