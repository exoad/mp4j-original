package app.interfaces.setup

import app.core.Host.Companion.openInBrowser
import app.global.Sources
import java.awt.event.ActionListener
import javax.swing.JFrame
import javax.swing.JButton
import java.awt.event.ActionEvent
import kotlin.jvm.JvmStatic
import app.interfaces.setup.JBRNotFound
import java.awt.Component
import java.awt.Dimension
import java.util.*
import javax.swing.ImageIcon
import javax.swing.WindowConstants
import javax.swing.JPanel
import javax.swing.JLabel

class JBRNotFound : ActionListener {
    private val frame: JFrame
    private val openDownload: JButton
    private val downloadInstaller: JButton
    override fun actionPerformed(e: ActionEvent) {
        if (e.source == openDownload) {
            openInBrowser("https://github.com/JetBrains/JetBrainsRuntime/releases")
            System.exit(-1)
        } else if (e.source == downloadInstaller) {
            openInBrowser(Sources.JBRINSTALLER)
            System.exit(-1)
        }
    }

    fun show() {
        frame.pack()
        frame.isVisible = true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val jbr = JBRNotFound()
            jbr.frame.isVisible = true
            jbr.frame.pack()
        }
    }

    init {
        frame = JFrame("A JBR installation is required")
        frame.iconImage = ImageIcon(Objects.requireNonNull(javaClass.getResource("/icons/logos/jetbrains.jpg"))).image
        frame.setSize(500, 300)
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.isResizable = false
        val panel = JPanel()
        panel.preferredSize = Dimension(500, 300)
        panel.layout = null
        val label = JLabel("<html><b>JetBrains Runtime was not found,<br> please install a valid JBR Runtime</b></html>")
        label.horizontalAlignment = Component.CENTER_ALIGNMENT.toInt()
        label.setBounds(0, 0, 500, 100)
        openDownload = JButton("Open Download Page")
        openDownload.toolTipText = "Open the official JetBrains Runtime GitHub releases Page"
        openDownload.setBounds(100, 100, 300, 50)
        openDownload.addActionListener(this)
        downloadInstaller = JButton("Download the Installer")
        downloadInstaller.toolTipText = "Download the JBR installer for this specific MP4J version"
        downloadInstaller.setBounds(100, 175, 300, 50)
        downloadInstaller.addActionListener(this)
        panel.add(label)
        panel.add(openDownload)
        panel.add(downloadInstaller)
        frame.add(panel)
    }
}