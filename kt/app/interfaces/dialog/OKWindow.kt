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
import javax.swing.WindowConstants
import java.io.IOException
import javax.swing.JScrollPane
import java.lang.Runnable

/**
 * @author Jack Meng
 */
class OKWindow(message: String) : ActionListener {
    private val okButton: JButton
    private val frame: JFrame

    /**
     * @param e
     */
    override fun actionPerformed(e: ActionEvent) {
        if (e.source == okButton) {
            frame.dispose()
            Logger.log("""
    ${e.source}
    ${frame.javaClass}
    """.trimIndent())
        }
    }

    init {
        val panel = JPanel()
        val label = JLabel("<html><center><p>$message</p></center></html>")
        okButton = JButton("Yay!")
        okButton.addActionListener(this)
        okButton.alignmentX = Component.CENTER_ALIGNMENT
        label.alignmentX = Component.CENTER_ALIGNMENT
        label.font = label.font.deriveFont(label.font.size * 1.5f)
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(label)
        panel.add(okButton)
        val url = javaClass.getResource("/icons/others/ok_icon.png")!!
        val icon = ImageIcon(url)
        frame = JFrame(message)
        frame.iconImage = icon.image
        frame.add(panel)
        frame.setSize(300, 100)
        frame.isResizable = false
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.isVisible = true
    }
}