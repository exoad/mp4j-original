package backend.setup

import app.CLI.print
import app.core.Host.Companion.returnJava
import app.interfaces.setup.JBRNotFound.show
import java.lang.StringBuilder
import kotlin.Throws
import backend.audio.AudioConversionException
import backend.audio.GlobalVars
import app.CLI
import app.global.cli.CliType
import it.sauronsoftware.jave.AudioAttributes
import it.sauronsoftware.jave.EncodingAttributes
import java.lang.IllegalArgumentException
import it.sauronsoftware.jave.InputFormatException
import it.sauronsoftware.jave.EncoderException
import app.core.Host
import app.global.Items
import app.interfaces.setup.JBRNotFound
import java.io.IOException
import kotlin.jvm.JvmStatic
import javax.sound.sampled.Clip
import java.lang.Runnable
import javax.sound.sampled.FloatControl
import kotlin.jvm.Synchronized
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.UnsupportedAudioFileException
import javax.sound.sampled.LineUnavailableException
import backend.audio.Music
import java.io.File

object CheckSetup {
    fun checkNativeDirs() {
        val apiCache = File(Items.items[0])
        if (!apiCache.isDirectory) {
            apiCache.mkdir()
        }
        val mpSaves = File(Items.items[1])
        if (!mpSaves.isDirectory) {
            mpSaves.mkdir()
        }
        val customs = File(Items.items[5])
        if (!customs.isDirectory) {
            customs.mkdir()
        }
        val mpLogs = File(Items.items[2])
        if (!mpLogs.isDirectory) {
            mpLogs.mkdir()
        }
    }

    fun checkJBR() {
        try {
            if (returnJava()!!.contains("JBR")) {
                JBRNotFound().show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
    }
}