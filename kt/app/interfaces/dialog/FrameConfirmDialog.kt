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
import java.awt.Component
import java.awt.Dimension
import javax.swing.WindowConstants
import java.io.IOException
import javax.swing.JScrollPane
import java.lang.Runnable

/**
 * @author Jack Meng
 */
class FrameConfirmDialog(message: String, private val frameToListen: JFrame, private val after: Runnable) : ActionListener {
    private val frame: JFrame
    private val okButton: JButton
    private val cancelButton: JButton
    override fun actionPerformed(e: ActionEvent) {
        if (e.source == okButton) {
            frame.dispose()
            frameToListen.dispose()
            after.run()
        } else if (e.source == cancelButton) {
            frame.dispose()
        }
    }

    init {
        val label = JLabel("<html><center><p>$message</p></center></html>")
        label.font = label.font.deriveFont(label.font.size * 1.5f)
        label.alignmentX = Component.CENTER_ALIGNMENT
        okButton = JButton("Yes")
        okButton.alignmentX = Component.CENTER_ALIGNMENT
        okButton.addActionListener(this)
        cancelButton = JButton("No")
        cancelButton.alignmentX = Component.CENTER_ALIGNMENT
        cancelButton.addActionListener(this)
        val pane = JPanel()
        pane.preferredSize = Dimension(300, 150)
        pane.layout = BoxLayout(pane, BoxLayout.Y_AXIS)
        pane.add(label)
        pane.add(okButton)
        pane.add(cancelButton)
        frame = JFrame("Confirm Action")
        frame.iconImage = frameToListen.iconImage
        frame.add(pane)
        frame.isUndecorated = true
        frame.setSize(300, 150)
        frame.addComponentListener(FrameOrganizer(frame))
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.isResizable = false
        frame.isVisible = true
    }
}