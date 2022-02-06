package app.telemetry.api

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
import java.net.URL

class Docs : API {
    private val docs = "> Properties Documentation <\n\nProperty Name | Description\ngui.defaultTheme | Changes the LAF of the MusicPlayer\nAvaliable Confs: regulardark, materia, onedark, arcdark, dracula, nord, gruvbox, vuesion, regularlight, solarized\n\nexplorer.defaultDir | Default Spawn Directory for the File Explorer:\n\".\", \"~\", \"/\"\n\nrunner.disableCache | Disable Caching\ntrue, false\n\ngui.defaultBoxSize | Change Button Spacing\n1 to 64"
    @Throws(IOException::class)
    override fun run(): String {
        if (!File(Items.items[4]).exists()) File(Items.items[4]).createNewFile()
        val bw = BufferedWriter(FileWriter(returnLink().file))
        bw.write(docs)
        bw.close()
        return docs
    }

    @Throws(IOException::class)
    override fun returnLink(): URL {
        return URL(Items.items[4])
    }
}