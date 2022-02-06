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
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.io.File
import java.lang.Exception
import java.util.*

class SelectFileWindow(lastFilePath: String) : JPanel(), Runnable, ActionListener {
    private var filePath: String? = null
    private val frame: JFrame
    private val button: JButton
    private val openYouTube: JButton
    private val openSoundCloud: JButton
    private val openSpotify: JButton
    private val openExplorer: JButton
    private val textField: JTextField
    private val lastDir: String
    private val toolBar: JToolBar
    override fun run() {
        frame.isVisible = true
        frame.pack()
    }

    /**
     * @param field
     */
    fun check(field: String?) {
        CLI.print(field)
        filePath = field
        if (filePath == null || filePath == "" || !File(filePath).exists()) {
            ErrorMessage("Invalid file path")
        }
        file = File(filePath)
        if (file!!.name.endsWith(".wav") || file!!.name.endsWith(".mp3")) {
            frame.isVisible = false
            frame.dispose()
            WindowPanel(lastDir)
            WindowPanel(lastDir).run()
        } else {
            ErrorMessage("Invalid file type")
        }
    }

    /**
     * @param e
     */
    override fun actionPerformed(e: ActionEvent) {
        CLI.print(e.actionCommand)
        if (e.source == button) {
            check(textField.text)
        } else if (e.source == openExplorer) {
            val f: File?
            Host(lastDir)
            f = openFileBrowser(this)
            if (f != null) {
                textField.text = f.absolutePath
                check(f.absolutePath)
                try {
                    val lst = f.parent
                    LifePreserver(lst).saveToPrevDir()
                } catch (ioException: Exception) {
                    ioException.printStackTrace()
                    ErrorMessage(Arrays.toString(ioException.stackTrace))
                }
            }
        }
    }

    companion object {
        /**
         * @return File
         */
        var file: File? = null
            private set
    }

    init {
        CLI.print(lastFilePath)
        lastDir = lastFilePath
        val youtube = javaClass.getResource("/icons/logos/youtube.png")
        val soundcloud = javaClass.getResource("/icons/logos/soundcloud.png")
        val spotify = javaClass.getResource("/icons/logos/spotify.png")
        val youtubeICO = ImageIcon(youtube)
        val soundcloudICO = ImageIcon(soundcloud)
        val spotifyICO = ImageIcon(spotify)
        openYouTube = JButton("YouTube")
        openYouTube.icon = youtubeICO
        openYouTube.addActionListener(this)
        openYouTube.toolTipText = "Open YouTube"
        openYouTube.isBorderPainted = false
        openYouTube.addActionListener(WebsiteButtons("https://www.youtube.com/"))
        openSoundCloud = JButton("SoundCloud")
        openSoundCloud.icon = soundcloudICO
        openSoundCloud.addActionListener(this)
        openSoundCloud.toolTipText = "Open SoundCloud"
        openSoundCloud.isBorderPainted = false
        openSoundCloud.addActionListener(WebsiteButtons("https://soundcloud.com/"))
        openSpotify = JButton("Spotify")
        openSpotify.icon = spotifyICO
        openSpotify.addActionListener(this)
        openSpotify.toolTipText = "Open Spotify"
        openSpotify.isBorderPainted = false
        openSpotify.addActionListener(WebsiteButtons("https://open.spotify.com/"))
        toolBar = JToolBar(SwingConstants.HORIZONTAL)
        toolBar.isFloatable = false
        toolBar.add(openYouTube)
        toolBar.add(openSoundCloud)
        toolBar.add(openSpotify)
        val frameIcon = javaClass.getResource("/icons/others/file_select_folder_icon.png")!!
        val frameImageIcon = ImageIcon(frameIcon)
        button = JButton("Select File")
        button.addActionListener(this)
        textField = JTextField()
        textField.isEditable = true
        textField.toolTipText = "Enter the file path here"
        openExplorer = JButton("Open Explorer")
        openExplorer.addActionListener(this)
        openExplorer.icon = frameImageIcon
        layout = BorderLayout()
        add(toolBar, BorderLayout.NORTH)
        add(button, BorderLayout.CENTER)
        add(textField, BorderLayout.CENTER)
        add(openExplorer, BorderLayout.SOUTH)
        preferredSize = Dimension(300, 80)
        frame = JFrame("Select File")
        frame.iconImage = frameImageIcon.image
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.setSize(300, 100)
        frame.setLocationRelativeTo(null)
        frame.contentPane.background = Color(0, 0, 0, 0)
        frame.isResizable = false
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
        frame.add(this)
    }
}