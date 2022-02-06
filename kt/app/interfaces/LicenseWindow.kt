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
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.io.InputStreamReader
import java.lang.Exception
import java.util.*
import javax.swing.*

class LicenseWindow(firstTime: Int) : Runnable, ActionListener {
    private val frame: JFrame
    private val agree: JButton
    private val disagree: JButton
    var proceed = false
    override fun run() {
        frame.pack()
        frame.isVisible = true
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === agree) {
            frame.dispose()
            proceed = true
        } else if (e.source === disagree) {
            frame.dispose()
            proceed = false
        }
    }

    companion object {
        /**
         * @param args
         * @throws IOException
         */
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            LicenseWindow(1).run()
        }
    }

    init {
        val url = javaClass.getResource("/icons/others/license_icon.png")!!
        val icon = ImageIcon(url)
        frame = JFrame("Music Player | License")
        frame.iconImage = icon.image
        frame.setSize(500, 600)
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        val label = JLabel("Music Player - License")
        label.icon = icon
        label.alignmentX = Component.CENTER_ALIGNMENT
        val textArea = JTextPane()
        textArea.isEditable = false
        val br = BufferedReader(
                InputStreamReader(Objects.requireNonNull(javaClass.getResource("/license.txt")).openStream()))
        val license = StringBuilder()
        try {
            var line = br.readLine()
            while (line != null) {
                license.append(line).append("\n")
                line = br.readLine()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        textArea.text = license.toString()
        val doc = textArea.styledDocument
        val center = SimpleAttributeSet()
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER)
        doc.setParagraphAttributes(0, doc.length, center, false)
        val bold = SimpleAttributeSet()
        StyleConstants.setBold(bold, true)
        doc.setCharacterAttributes(0, doc.length, bold, false)
        textArea.size = Dimension(400, 400)
        textArea.font = textArea.font.deriveFont(textArea.font.size * 1f)
        textArea.alignmentX = Component.CENTER_ALIGNMENT
        val scrollPane = JScrollPane(textArea)
        scrollPane.setBounds(0, 0, 150, 500)
        scrollPane.alignmentX = Component.CENTER_ALIGNMENT
        scrollPane.preferredSize = Dimension(450, 500)
        agree = JButton("Agree")
        disagree = JButton("Disagree")
        if (firstTime == -1) {
            val panel = JPanel()
            panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
            agree.addActionListener(this)
            agree.alignmentX = Component.CENTER_ALIGNMENT
            disagree.addActionListener(this)
            disagree.alignmentX = Component.CENTER_ALIGNMENT
            panel.add(agree)
            panel.add(disagree)
            frame.add(label)
            frame.add(Box.createHorizontalStrut(2))
            frame.add(Box.createVerticalStrut(7))
        }
        frame.add(scrollPane)
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
    }
}