package app.interfaces

import app.interfaces.event.FrameOrganizer
import java.awt.Component
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import javax.swing.*
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants

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