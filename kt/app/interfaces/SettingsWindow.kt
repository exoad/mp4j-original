package app.interfaces

import app.CLI
import app.core.Cache.cleanCache
import app.core.JSONParser.parseElement
import app.core.PropertiesReader
import app.core.PropertiesReader.Companion.reset
import app.global.Items
import app.global.VersionInfo
import app.global.cli.CliType
import app.interfaces.dialog.ErrorMessage
import app.interfaces.dialog.OKWindow
import app.interfaces.event.FrameOrganizer
import app.interfaces.theme.LAFCommitter
import app.interfaces.theme.Parser
import app.telemetry.FileIntegrity
import app.telemetry.Logger
import app.telemetry.api.Wrapper
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import java.io.IOException
import java.util.*
import javax.swing.*

class SettingsWindow : Runnable, ActionListener, ItemListener {
    private val panel: JPanel
    private val title: JLabel
    private val theme: JComboBox<String>
    private val information: JLabel
    private var pr: PropertiesReader
    private val verifyFile: JButton
    private val clearCache: JButton
    private val resetProperties: JButton
    private val clearLogs: JButton
    private val wrapper = Wrapper()
    private val fileIntegrity = FileIntegrity()

    constructor(something: WelcomeWindow?) {
        pr = PropertiesReader()
        panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.preferredSize = Dimension(500, 400)
        title = JLabel("Settings")
        title.alignmentX = Component.CENTER_ALIGNMENT
        title.font = title.font.deriveFont(title.font.size * 1.5f)
        verifyFile = JButton("Verify File Integrity")
        verifyFile.alignmentX = Component.CENTER_ALIGNMENT
        val URLFILEINT = javaClass.getResource("/icons/others/fileint_icon.png")!!
        val fileINTCO: Icon = ImageIcon(URLFILEINT)
        verifyFile.icon = fileINTCO
        verifyFile.addActionListener(this)
        theme = JComboBox(Items.themes)
        theme.alignmentX = Component.CENTER_ALIGNMENT
        theme.maximumSize = Dimension(theme.preferredSize.width, theme.preferredSize.height)
        theme.selectedItem = pr.getVal("gui.defaultTheme")
        theme.toolTipText = "Change the theme of the application"
        theme.addItemListener(this)
        val json = wrapper.run()
        val versionInfo = ("<html><p>Your Version: " + VersionInfo.VERSION + "<br>Latest Release: "
                + (if (parseElement("latest_release", json) == null
                || parseElement("latest_release", json) == "") "Unavaliable" else parseElement("latest_release", json))
                + "<br>"
                + "Latest Patch: "
                + (if (parseElement("latest_patch", json) == null
                || parseElement("latest_patch", json) == "") "Unavaliable" else parseElement("latest_patch", json))
                + "<br>" + "Latest Beta: "
                + (if (parseElement("latest_beta", json) == null
                || parseElement("latest_beta", json) == "") "Unavaliable" else parseElement("latest_beta", json))
                + "</p></html>")
        information = JLabel(versionInfo)
        information.alignmentX = Component.CENTER_ALIGNMENT
        information.font = information.font.deriveFont(information.font.style or Font.ITALIC)
        clearCache = JButton("Clear Cache")
        clearCache.alignmentX = Component.CENTER_ALIGNMENT
        val URLCLEARCACHE = javaClass.getResource("/trashcan_icon.png")!!
        val trashcanCO: Icon = ImageIcon(URLCLEARCACHE)
        clearCache.icon = trashcanCO
        clearCache.addActionListener(this)
        clearLogs = JButton("Clear Logs")
        clearLogs.alignmentX = Component.CENTER_ALIGNMENT
        clearLogs.icon = trashcanCO
        clearLogs.addActionListener(this)
        resetProperties = JButton("Reset Properties")
        resetProperties.alignmentX = Component.CENTER_ALIGNMENT
        val URLRESETPROP = javaClass.getResource("/icons/others/reset_icon.png")!!
        val resetPROPCO: Icon = ImageIcon(URLRESETPROP)
        resetProperties.icon = resetPROPCO
        resetProperties.addActionListener(this)
        panel.add(title)
        panel.add(Box.createHorizontalStrut(10))
        panel.add(verifyFile)
        panel.add(clearCache)
        panel.add(resetProperties)
        panel.add(clearLogs)
        panel.add(theme)
        panel.add(Box.createHorizontalStrut(10))
        panel.add(Box.createHorizontalStrut(10))
        panel.add(information)
        val icon = javaClass.getResource("/icons/others/information_icon.png")!!
        val imageIcon = ImageIcon(icon)
        frame = JFrame("Music Player | Settings")
        frame.setSize(500, 600)
        frame.isResizable = false
        frame.iconImage = imageIcon.image
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
        frame.add(panel)
    }

    constructor() {
        pr = PropertiesReader()
        panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.preferredSize = Dimension(500, 600)
        title = JLabel("Settings")
        title.alignmentX = Component.CENTER_ALIGNMENT
        title.font = title.font.deriveFont(title.font.size * 2.0f)
        verifyFile = JButton("Check File Integrity")
        verifyFile.alignmentX = Component.CENTER_ALIGNMENT
        val URLFILEINT = javaClass.getResource("/icons/others/fileint_icon.png")!!
        val fileINTCO: Icon = ImageIcon(URLFILEINT)
        verifyFile.icon = fileINTCO
        verifyFile.addActionListener(this)
        theme = JComboBox(Items.themes)
        theme.toolTipText = "Change the theme of the application"
        theme.alignmentX = Component.CENTER_ALIGNMENT
        theme.maximumSize = Dimension(theme.preferredSize.width, theme.preferredSize.height)
        theme.selectedItem = pr.getVal("gui.defaultTheme")
        theme.addItemListener(this)
        val json = wrapper.run()
        val versionInfo = ("<html><p>Your Version: " + VersionInfo.VERSION + "<br>Latest Release: "
                + (if (parseElement("latest_release", json) == null
                || parseElement("latest_release", json) == "") "Unavaliable" else parseElement("latest_release", json))
                + "<br>"
                + "Latest Patch: "
                + (if (parseElement("latest_patch", json) == null
                || parseElement("latest_patch", json) == "") "Unavaliable" else parseElement("latest_patch", json))
                + "<br>" + "Latest Beta: "
                + (if (parseElement("latest_beta", json) == null
                || parseElement("latest_beta", json) == "") "Unavaliable" else parseElement("latest_beta", json))
                + "</p></html>")
        information = JLabel(versionInfo)
        information.alignmentX = Component.CENTER_ALIGNMENT
        information.font = information.font.deriveFont(information.font.style or Font.ITALIC)
        clearCache = JButton("Clear Cache")
        clearCache.alignmentX = Component.CENTER_ALIGNMENT
        val URLCLEARCACHE = javaClass.getResource("/icons/others/trashcan_icon.png")!!
        val trashcanCO: Icon = ImageIcon(URLCLEARCACHE)
        clearCache.icon = trashcanCO
        clearCache.addActionListener(this)
        clearLogs = JButton("Clear Logs")
        clearLogs.alignmentX = Component.CENTER_ALIGNMENT
        clearLogs.icon = trashcanCO
        clearLogs.addActionListener(this)
        resetProperties = JButton("Reset Properties")
        resetProperties.alignmentX = Component.CENTER_ALIGNMENT
        val URLRESETPROP = javaClass.getResource("/icons/others/reset_icon.png")!!
        val resetPROPCO: Icon = ImageIcon(URLRESETPROP)
        resetProperties.icon = resetPROPCO
        resetProperties.addActionListener(this)
        panel.add(title)
        panel.add(Box.createHorizontalStrut(10))
        panel.add(verifyFile)
        panel.add(clearCache)
        panel.add(resetProperties)
        panel.add(clearLogs)
        panel.add(theme)
        panel.add(Box.createHorizontalStrut(10))
        panel.add(Box.createHorizontalStrut(30))
        panel.add(information)
        val icon = javaClass.getResource("/icons/others/information_icon.png")!!
        val imageIcon = ImageIcon(icon)
        frame = JFrame("Music Player | Settings")
        frame.setSize(500, 400)
        frame.isResizable = false
        frame.iconImage = imageIcon.image
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
        frame.add(panel)
    }

    override fun run() {
        frame.isVisible = true
        frame.pack()
    }

    /**
     * @param e
     */
    override fun actionPerformed(e: ActionEvent) {
        if (e.source == verifyFile) {
            try {
                val isNice = fileIntegrity.isGood
                Thread.sleep(800)
                if (isNice == "0") OKWindow("Files are all good!") else ErrorMessage("Was unable to process the file: \n$isNice")
            } catch (e1: Exception) {
                e1.printStackTrace()
            }
        } else if (e.source == clearCache) {
            try {
                if (cleanCache()) {
                    OKWindow("Cache Cleared")
                } else {
                    ErrorMessage("Could not clear Cache at this moment")
                }
            } catch (e1: Exception) {
                e1.printStackTrace()
                CLI.print(e1.message, CliType.WARNING)
            }
        } else if (e.source == resetProperties) {
            if (reset()) {
                OKWindow("Properties Reset")
            } else {
                ErrorMessage("Reset failed")
            }
        } else if (e.source == clearLogs) {
            if (Logger.clear()) {
                OKWindow("Logs Cleared")
            } else {
                ErrorMessage("Cannot Clear Logs at this moment")
            }
        }
    }

    override fun itemStateChanged(e: ItemEvent) {
        if (e.source == theme) {
            LAFCommitter(*Frame.getFrames()).setMultTheme(Parser(Objects.requireNonNull(theme.selectedItem).toString()).getTheme())
            try {
                Parser(Objects.requireNonNull(theme.selectedItem).toString()).parseThemeToProperty()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        }
    }

    companion object {
        lateinit var frame: JFrame

        /**
         * @param args
         * @throws IOException
         */
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SettingsWindow().run()
        }

        /**
         * @return Object
         */
        val instance: Any
            get() = frame
    }
}