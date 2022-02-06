package app.interfaces

import app.core.PropertiesReader.getVal
import app.interfaces.DocumentationWindow.run
import app.core.JSONParser.parseElement
import app.core.Cache.cleanCache
import app.core.PropertiesReader.Companion.reset
import app.interfaces.theme.LAFCommitter.setMultTheme
import app.interfaces.theme.Parser.getTheme
import app.interfaces.theme.Parser.parseThemeToProperty
import app.core.Host.Companion.openFileBrowser
import app.core.LifePreserver.saveToPrevDir
import javax.swing.JWindow
import javax.swing.JLabel
import javax.swing.ImageIcon
import javax.swing.SwingConstants
import java.lang.InterruptedException
import app.interfaces.dialog.ErrorMessage
import java.awt.event.ActionListener
import javax.swing.event.ChangeListener
import java.lang.Runnable
import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.JSlider
import javax.swing.Icon
import backend.audioutil.Player
import app.interfaces.WindowPanel
import java.lang.Math
import kotlin.jvm.Synchronized
import java.awt.event.ActionEvent
import app.interfaces.dialog.FrameConfirmDialog
import app.interfaces.SelectFileWindow
import javax.swing.event.ChangeEvent
import javax.swing.JFrame
import app.interfaces.event.FrameOrganizer
import javax.swing.BoxLayout
import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import app.interfaces.LicenseWindow
import javax.swing.WindowConstants
import javax.swing.JTextPane
import java.io.BufferedReader
import java.lang.StringBuilder
import javax.swing.text.StyledDocument
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import javax.swing.JScrollPane
import app.CLI
import app.interfaces.WelcomeWindow
import app.interfaces.SettingsWindow
import app.interfaces.DocumentationWindow
import app.interfaces.event.WebsiteButtons
import app.core.PropertiesReader
import java.awt.event.ItemListener
import javax.swing.JComboBox
import app.telemetry.FileIntegrity
import app.interfaces.dialog.OKWindow
import java.awt.event.ItemEvent
import app.interfaces.theme.LAFCommitter
import javax.swing.JTextField
import javax.swing.JToolBar
import app.core.Host
import app.core.LifePreserver
import java.awt.*
import java.io.File
import java.net.URL

class WindowPanel(resource: String) : ActionListener, ChangeListener, Runnable {
    protected var bp: JPanel
    protected var mainPanel: JPanel
    protected var waves = arrayOfNulls<URL>(4)
    protected var play_btn: JButton
    protected var new_file: JButton
    protected var loop_btn: JButton
    protected var header_notice: JLabel
    protected var status: JLabel
    protected var wave_synth: JLabel
    protected var volume_slider: JSlider
    protected var loop = false
    protected var pause_icon = javaClass.getResource("/icons/others/pause_button.png")
    protected var looped = javaClass.getResource("/icons/others/loop_icon.png")
    protected var looped_icon: Icon = ImageIcon(looped)
    protected var pause_button_ico: Icon? = null
    protected var pl: Player
    protected var master = Thread()
    protected var loopWatcher = Thread()
    protected var play_icon = javaClass.getResource("/icons/others/play_button.png")
    protected var play_button_ico: Icon? = null
    var currentFrame = 0
    fun setPauseState() {
        play_btn.icon = play_button_ico
        play_btn.toolTipText = "Play the current media"
        status.text = "<html><b>Currently Playing Nothing</b></html>"
        wave_synth.icon = ImageIcon(waves[3])
    }

    fun setPlayState() {
        play_btn.icon = pause_button_ico
        play_btn.toolTipText = "Pause the current media"
        status.text = "<html><u><b>Currently Playing: </b></u><br>" + musicFile!!.name + "</html>"
        wave_synth.icon = ImageIcon(waves[Math.random().toInt() * 3])
    }

    private var volumeWorker = Thread()
    fun updateVolume() {
        volumeWorker = Thread {
            pl.vols = volume_slider.value.toFloat()
            pl.setVolume()
            volume_slider.toolTipText = "Volume: " + volume_slider.value + "%"
        }
        volumeWorker.start()
    }

    @Synchronized
    override fun actionPerformed(e: ActionEvent) {
        if (e.source === play_btn) {
            updateVolume()
            if (alreadyPlaying) {
                pl.pause()
                setPauseState()
                alreadyPlaying = false
            } else {
                pl.play()
                setPlayState()
                alreadyPlaying = true
            }
        } else if (e.source == new_file) {
            FrameConfirmDialog("Are you sure you want to exit?", frame, SelectFileWindow(music_path))
            pl.pause()
            setPauseState()
            alreadyPlaying = false
        } else if (e.source == loop_btn) {
        } else if (e.source == volume_slider) {
            val t = Thread {
                pl.vols = volume_slider.value.toFloat()
                pl.setVolume()
                volume_slider.toolTipText = "Volume: " + volume_slider.value + "%"
            }
            t.start()
        }
    }

    override fun run() {
        frame.pack()
        frame.isVisible = true
    }

    /**
     * @param e
     */
    @Synchronized
    override fun stateChanged(e: ChangeEvent) {
        // make this multi-threaded
        if (e.source == volume_slider) {
            val t = Thread {
                pl.vols = volume_slider.value.toFloat()
                pl.setVolume()
                volume_slider.toolTipText = "Volume: " + volume_slider.value + "%"
            }
            t.start()
        }
    }

    companion object {
        protected var frame: JFrame
        protected var musicFile: File?
        protected var alreadyPlaying = false
        protected var toPause = false
        protected var music_path: String
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
        musicFile = SelectFileWindow.Companion.getFile()
        pl = Player(musicFile, 50f)
        status = JLabel("<html><b>Currently Playing: </b></html>" + musicFile!!.name)
        status.horizontalAlignment = Component.CENTER_ALIGNMENT.toInt()
        waves[0] = javaClass.getResource("/icons/animated/waves/wave_1.gif")
        waves[1] = javaClass.getResource("/icons/animated/waves/wave_2.gif")
        waves[2] = javaClass.getResource("/icons/animated/waves/wave_3.gif")
        waves[3] = javaClass.getResource("/icons/animated/waves/paused/waves0.png")
        wave_synth = JLabel(ImageIcon(waves[3]))
        wave_synth.horizontalAlignment = Component.CENTER_ALIGNMENT.toInt()
        val loop_button_ico: Icon = ImageIcon(looped)
        loop_btn = JButton(loop_button_ico)
        loop_btn.toolTipText = "Loop"
        loop_btn.horizontalAlignment = Component.CENTER_ALIGNMENT.toInt()
        loop_btn.addActionListener(this)
        val frame_icon = javaClass.getResource("/icons/others/frame-icon.png")
        assert(pause_icon != null)
        assert(play_icon != null)
        assert(frame_icon != null)
        val frame_ico = ImageIcon(frame_icon)
        frame = JFrame("Music Player - Jack Meng")
        frame.iconImage = frame_ico.image
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.isResizable = false
        play_btn = JButton(play_button_ico)
        play_btn.addActionListener(this)
        play_btn.toolTipText = "Play the current media"
        play_btn.alignmentX = Component.CENTER_ALIGNMENT
        val new_file_icon = javaClass.getResource("/icons/others/file_select_folder_icon.png")!!
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
        bp.add(wave_synth)
        bp.add(status)
        bp.add(play_btn)
        bp.add(new_file)
        bp.add(loop_btn)
        bp.add(volume_slider)
        bp.preferredSize = Dimension(500, 200)
        mainPanel = JPanel()
        mainPanel.add(bp)
        frame.add(mainPanel)
        currentFrame = 0
    }
}