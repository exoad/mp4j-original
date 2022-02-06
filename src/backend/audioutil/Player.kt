package backend.audioutil

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

class Player(f: File, volume: Float) {
    private val f: File? = null
    var c: Clip? = null

    @get:Synchronized
    @set:Synchronized
    var frame = 0L
    var vols: Float
    private var alreadyOpen = false
    private val worker = Thread()
    private var volumeWorker = Thread()
    fun setVolume() {
        if (c != null) {
            volumeWorker = Thread {
                val gainControl = c!!.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
                val range = gainControl.maximum - gainControl.minimum
                val gain = vols / 100.0f * range + gainControl.minimum
                gainControl.value = gain
            }
            volumeWorker.start()
        }
    }

    @Synchronized
    fun play() {
        if (!alreadyOpen) {
            try {
                c!!.open(AudioSystem.getAudioInputStream(f))
                alreadyOpen = true
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: UnsupportedAudioFileException) {
                e.printStackTrace()
            } catch (e: LineUnavailableException) {
                e.printStackTrace()
            }
        }
        c!!.microsecondPosition = frame
        c!!.start()
        setVolume()
    }

    @Synchronized
    fun pause() {
        worker.interrupt()
        if (c!!.isRunning) {
            c!!.stop()
            frame = c!!.microsecondPosition
        }
    }

    @Synchronized
    fun unload() {
        c!!.close()
    }

    @get:Synchronized
    val trackName: String
        get() = f!!.name

    @Synchronized
    fun loop(): Boolean {
        if (c!!.isRunning) {
            c!!.loop(Clip.LOOP_CONTINUOUSLY)
            return true
        }
        return false
    }

    init {
        if (f.absolutePath.endsWith(".mp3")) {
            try {
                this.f = Music.convert(f)
            } catch (e: AudioConversionException) {
                e.printStackTrace()
            }
        } else {
            this.f = f
        }
        vols = volume
        try {
            c = AudioSystem.getClip()
        } catch (e1: LineUnavailableException) {
            e1.printStackTrace()
        }
    }
}