package app.interfaces.dialog

import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JFrame
import java.awt.event.ActionEvent
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import app.interfaces.event.FrameOrganizer
import app.telemetry.Logger
import java.awt.Component
import java.awt.Desktop
import java.io.File
import javax.swing.WindowConstants
import java.io.IOException
import javax.swing.JScrollPane
import java.lang.Runnable

/**
 * @author Jack Meng
 */
class ErrorMessage(message: String?) : ActionListener {
    private val okButton: JButton
    private val pathBtn: JButton
    private val frame: JFrame
    private val path: String

    /**
     * @param e
     */
    override fun actionPerformed(e: ActionEvent) {
        if (e.source == okButton) {
            frame.dispose()
        } else if (e.source == pathBtn) {
            try {
                Desktop.getDesktop().open(File(path))
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
        }
    }

    init {
        path = Logger.log(message)
        val panel = JPanel()
        val label = JLabel("<html><center><p>An error occured click below to view the error<br></p></center></html>")
        okButton = JButton("OK")
        okButton.addActionListener(this)
        okButton.alignmentX = Component.CENTER_ALIGNMENT
        label.alignmentX = Component.CENTER_ALIGNMENT
        label.font = label.font.deriveFont(label.font.size * 1f)
        pathBtn = JButton("View Error")
        pathBtn.addActionListener(this)
        pathBtn.alignmentX = Component.CENTER_ALIGNMENT
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(label)
        panel.add(okButton)
        panel.add(pathBtn)
        val jsp = JScrollPane(panel)
        val url = javaClass.getResource("/icons/others/error_frame_icon.png")!!
        val icon = ImageIcon(url)
        frame = JFrame("Error!")
        frame.iconImage = icon.image
        frame.add(jsp)
        frame.setSize(400, 150)
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.isVisible = true
    }
}