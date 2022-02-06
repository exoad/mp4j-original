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
import java.util.*

class Splash(private val seconds: Float) {
    fun run() {
        val splash_screen = javaClass.getResource("/icons/splash/splash_screen.png")
        val window = JWindow()
        assert(splash_screen != null)
        window.contentPane.add(
                JLabel("", ImageIcon(splash_screen),
                        SwingConstants.CENTER))
        window.setSize(800, 600)
        window.setBounds(500, 150, 300, 200)
        window.setLocationRelativeTo(null)
        window.isVisible = true
        try {
            Thread.sleep(Math.floor(seconds.toDouble()) as Int.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
            ErrorMessage(Arrays.toString(e.stackTrace))
        }
        window.isVisible = false
        window.dispose()
    }
}