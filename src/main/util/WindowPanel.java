package main.util

import java.awt.event.ActionListener
import javax.swing.event.ChangeListener
import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JSlider
import javax.swing.Icon
import main.util.WindowPanel
import javax.sound.sampled.FloatControl
import kotlin.Throws
import javazoom.jl.decoder.JavaLayerException
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javazoom.jl.player.Player
import java.io.FileInputStream
import javax.sound.sampled.UnsupportedAudioFileException
import java.io.IOException
import javax.sound.sampled.LineUnavailableException
import java.awt.event.ActionEvent
import javax.swing.event.ChangeEvent
import javax.swing.JFrame
import javax.sound.sampled.Clip
import kotlin.jvm.JvmStatic
import javax.swing.SwingUtilities
import java.lang.Runnable
import javax.swing.WindowConstants
import javax.swing.ImageIcon
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme
import main.util.SelectFileWindow
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.io.File
import javax.swing.SwingConstants
import javax.swing.BoxLayout

class WindowPanel(resource: String) : ActionListener, ChangeListener {
    protected var bp: JPanel
    protected var mainPanel: JPanel
    protected var play_btn: JButton
    protected var new_file: JButton
    protected var header_notice: JLabel
    protected var status: JLabel
    protected var volume_slider: JSlider
    protected var volume_keeper = 0f
    protected var loop = false
    protected var pause_icon = javaClass.getResource("/pause_button.png")
    protected var pause_button_ico: Icon? = null
    protected var play_icon = javaClass.getResource("/play_button.png")
    protected var play_button_ico: Icon? = null
    var currentFrame: Long = 0
    fun volumeControl() {
        if (clip != null) {
            val gainControl = clip!!.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
            val range = gainControl.maximum - gainControl.minimum
            val gain = volume_slider.value / 100.0f * range + gainControl.minimum
            gainControl.value = gain
            volume_slider.toolTipText = "Current Volume: " + volume_slider.value + "%"
        }
    }

    @Throws(JavaLayerException::class)
    fun playMusic() {
        try {
            if (!musicFile.name.endsWith(".mp3")) {
                val audioInputStream = AudioSystem
                        .getAudioInputStream(musicFile)
                clip = AudioSystem.getClip()
                clip.open(audioInputStream)
                clip.setMicrosecondPosition(currentFrame)
                clip.start()
                playAsMp3 = false
                volumeControl()
            } else {
                playAsMp3 = true
                val mp3Player = Player(FileInputStream(musicFile))
                mp3Player.play()
            }
        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: LineUnavailableException) {
            e.printStackTrace()
        }
    }

    fun pauseMusic() {
        if (clip != null) {
            currentFrame = clip!!.microsecondPosition
            clip!!.stop()
        }
    }

    fun setPauseState() {
        play_btn.icon = play_button_ico
        play_btn.toolTipText = "Play the current media"
        status.text = "<html><b>Currently Playing Nothing</b></html>"
    }

    fun setPlayState() {
        play_btn.icon = pause_button_ico
        play_btn.toolTipText = "Pause the current media"
        status.text = "<html><b>Currently Playing: </b><br>" + musicFile.name + "</html>"
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === play_btn) {
            if (play_btn.icon === play_button_ico) {
                try {
                    playMusic()
                } catch (e1: JavaLayerException) {
                    e1.printStackTrace()
                }
                setPlayState()
            } else if (play_btn.icon === pause_button_ico) {
                pauseMusic()
                setPauseState()
            }
        } else if (e.source === volume_slider) {
            if (playAsMp3 || musicFile.name.endsWith(".mp3")) {
                volume_slider.isEnabled = false
                volume_slider.toolTipText = "MP3 is only semi supported for now"
            } else {
                volume_slider.isEnabled = true
                volume_slider.toolTipText = "Current Volume: " + volume_slider.value + "%"
            }
            volumeControl()
        }
    }

    override fun stateChanged(e: ChangeEvent) {
        if (e.source === volume_slider) {
            volumeControl()
        }
    }

    companion object {
        protected var frame: JFrame
        protected var musicFile: File
        protected var clip: Clip? = null
        protected var alreadyPlaying = false
        protected var toPause = false
        protected var playAsMp3 = false
        protected var music_path: String
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater { run() }
            val f = File("musicML")
            if (!f.exists()) f.mkdir()
            run()
        }

        @JvmStatic
        fun run() {
            WindowPanel(music_path)
            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.pack()
            frame.isVisible = true
        }
    }

    init {
        assert(pause_icon != null)
        pause_button_ico = ImageIcon(pause_icon)
    }

    init {
        assert(play_icon != null)
        play_button_ico = ImageIcon(play_icon)
    }

    init {
        music_path = resource
        FlatMaterialDarkerIJTheme.setup()
        musicFile = SelectFileWindow.getFile()
        status = JLabel("<html><b>Currently Playing: </b></html>" + musicFile.name)
        status.horizontalAlignment = Component.CENTER_ALIGNMENT.toInt()
        val frame_icon = javaClass.getResource("/frame-icon.png")
        assert(pause_icon != null)
        assert(play_icon != null)
        assert(frame_icon != null)
        val frame_ico = ImageIcon(frame_icon)
        frame = JFrame("Music Player - Jack Meng")
        frame.iconImage = frame_ico.image
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.isResizable = false
        play_btn = JButton(play_button_ico)
        play_btn.addActionListener(this)
        play_btn.toolTipText = "Play the current media"
        play_btn.alignmentX = Component.CENTER_ALIGNMENT
        val new_file_icon = javaClass.getResource("/file_select_folder_icon.png")!!
        val new_file_ico: Icon = ImageIcon(new_file_icon)
        new_file = JButton(new_file_ico)
        new_file.addActionListener(this)
        new_file.toolTipText = "Select a new media file"
        new_file.alignmentX = Component.CENTER_ALIGNMENT
        header_notice = JLabel(
                "<html><body><strong><u>Supported File Types : .mp3 & .wav</u></strong><br><center>Place files in folder: <code>/musicML/</code></center></body></html><br>")
        header_notice.font = Font("Courier", Font.PLAIN, 13)
        header_notice.alignmentX = Component.CENTER_ALIGNMENT
        volume_slider = JSlider(SwingConstants.HORIZONTAL, 0, 100, 50)
        volume_slider.majorTickSpacing = 10
        volume_slider.minorTickSpacing = 1
        volume_slider.minimum = 0
        volume_slider.maximum = 100
        volume_slider.paintTicks = true
        volume_slider.paintLabels = true
        volume_slider.alignmentX = Component.CENTER_ALIGNMENT
        volume_slider.addChangeListener(this)
        volume_slider.toolTipText = "Change the volume. Current: " + volume_slider.value + "%"
        bp = JPanel()
        bp.layout = BoxLayout(bp, BoxLayout.X_AXIS)
        bp.add(status)
        bp.add(play_btn)
        bp.add(volume_slider)
        bp.preferredSize = Dimension(450, 200)
        mainPanel = JPanel()
        mainPanel.add(bp)
        frame.add(mainPanel)
    }
}