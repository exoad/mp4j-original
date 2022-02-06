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
import java.lang.InterruptedException
import app.interfaces.dialog.ErrorMessage
import java.awt.event.ActionListener
import javax.swing.event.ChangeListener
import java.lang.Runnable
import backend.audioutil.Player
import app.interfaces.WindowPanel
import java.lang.Math
import kotlin.jvm.Synchronized
import java.awt.event.ActionEvent
import app.interfaces.dialog.FrameConfirmDialog
import app.interfaces.SelectFileWindow
import javax.swing.event.ChangeEvent
import app.interfaces.event.FrameOrganizer
import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import app.interfaces.LicenseWindow
import java.io.BufferedReader
import java.lang.StringBuilder
import javax.swing.text.StyledDocument
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import app.CLI
import app.interfaces.WelcomeWindow
import app.interfaces.SettingsWindow
import app.interfaces.DocumentationWindow
import app.interfaces.event.WebsiteButtons
import app.core.PropertiesReader
import java.awt.event.ItemListener
import app.telemetry.FileIntegrity
import app.interfaces.dialog.OKWindow
import java.awt.event.ItemEvent
import app.interfaces.theme.LAFCommitter
import app.core.Host
import app.core.LifePreserver
import app.telemetry.Logger
import java.awt.*
import java.util.*
import javax.swing.*

class WelcomeWindow(lastDir: String) : Runnable, ActionListener {
    private val frame: JFrame
    private val openSelectFile: JButton
    private val github: JButton
    private val license: JButton
    private val settings: JButton
    private val documentation: JButton
    override fun run() {
        frame.isVisible = true
        frame.pack()
    }

    /**
     * @param e
     */
    override fun actionPerformed(e: ActionEvent) {
        Logger.log("""
    ${e.source}
    ${e.getWhen()}
    """.trimIndent())
        CLI.print(e.source)
        if (e.source == openSelectFile) {
            SelectFileWindow(lastDir).run()
        } else if (e.source == license) {
            try {
                LicenseWindow(0).run()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        } else if (e.source == settings) {
            try {
                SettingsWindow().run()
            } catch (ioe: IOException) {
                ErrorMessage(Arrays.toString(ioe.stackTrace))
                ioe.printStackTrace()
            }
        } else if (e.source == documentation) {
            try {
                DocumentationWindow().run()
            } catch (ioe: IOException) {
                ErrorMessage(Arrays.toString(ioe.stackTrace))
                ioe.printStackTrace()
            }
        }
    }

    companion object {
        var lastDir = ""

        /**
         * @param args
         * @throws IOException
         */
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            WelcomeWindow(lastDir).run()
        }
    }

    init {
        Companion.lastDir = lastDir
        val frame_icon = javaClass.getResource("/icons/others/welcome_icon.png")!!
        val frame_ico = ImageIcon(frame_icon)
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        val title = JLabel("Music Player")
        title.icon = frame_ico
        val font = title.font
        title.font = font.deriveFont(font.style or Font.BOLD)
        title.font = title.font.deriveFont(title.font.size * 2.5f)
        title.alignmentX = Component.CENTER_ALIGNMENT
        val description = JLabel(
                "<html><div style='text-align: center;'><p>This is a very simple Music Player for you. You can check out the GitHub page and look at ongoing updates, and report bugs and issues. If you wanna play something go click on that button called \"Select File\". Currently only wav files are supported!</p></div></html>")
        description.font = description.font.deriveFont(description.font.size * 1.1f)
        description.alignmentX = Component.CENTER_ALIGNMENT
        val version = JLabel("<html><u>Version 1.0 | Made by Jack Meng</u><br></html>")
        version.font = version.font.deriveFont(version.font.style or Font.ITALIC)
        version.alignmentX = Component.CENTER_ALIGNMENT
        val file_icon = javaClass.getResource("/icons/others/file_select_folder_icon.png")!!
        val ico: Icon = ImageIcon(file_icon)
        openSelectFile = JButton("Select File")
        openSelectFile.icon = ico
        openSelectFile.preferredSize = Dimension(300, 90)
        openSelectFile.addActionListener(this)
        openSelectFile.alignmentX = Component.CENTER_ALIGNMENT
        val github_icon = javaClass.getResource("/icons/others/github.png")!!
        val git_ico: Icon = ImageIcon(github_icon)
        github = JButton("GitHub Repository")
        github.icon = git_ico
        github.addActionListener(WebsiteButtons("https://github.com/exoad/MusicPlayer4J"))
        github.alignmentX = Component.CENTER_ALIGNMENT
        val license_icon = javaClass.getResource("/icons/others/license_icon.png")!!
        val lic_ico: Icon = ImageIcon(license_icon)
        license = JButton("Read License")
        license.icon = lic_ico
        license.addActionListener(this)
        license.alignmentX = Component.CENTER_ALIGNMENT
        val settings_icon = javaClass.getResource("/icons/others/information_icon.png")!!
        val set_ico: Icon = ImageIcon(settings_icon)
        settings = JButton("Settings")
        settings.icon = set_ico
        settings.addActionListener(this)
        settings.alignmentX = Component.CENTER_ALIGNMENT
        val docs_icon = javaClass.getResource("/icons/others/documentation_icon.png")!!
        val doc_ico: Icon = ImageIcon(docs_icon)
        documentation = JButton("Documentation")
        documentation.icon = doc_ico
        documentation.addActionListener(this)
        documentation.alignmentX = Component.CENTER_ALIGNMENT
        val pr = PropertiesReader()
        panel.add(title)
        panel.add(version)
        panel.add(Box.createVerticalStrut(20))
        panel.add(description)
        panel.add(Box.createVerticalStrut(20))
        panel.add(openSelectFile)
        panel.add(Box.createVerticalStrut(4))
        panel.add(github)
        panel.add(Box.createVerticalStrut(pr.getVal("gui.defaultBoxSize").toInt()))
        panel.add(license)
        panel.add(Box.createVerticalStrut(pr.getVal("gui.defaultBoxSize").toInt()))
        panel.add(documentation)
        panel.add(Box.createVerticalStrut(pr.getVal("gui.defaultBoxSize").toInt()))
        panel.add(settings)
        panel.preferredSize = Dimension(500, 370)
        frame = JFrame("Music-Player v1.0 | Jack Meng | Welcome!")
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.addComponentListener(FrameOrganizer(frame))
        frame.iconImage = frame_ico.image
        frame.isResizable = false
        frame.add(panel)
    }
}