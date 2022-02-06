package app.interfaces

import app.functions.Parser.foreach
import java.lang.Runnable
import kotlin.Throws
import java.io.IOException
import kotlin.jvm.JvmStatic
import app.interfaces.DocumentationWindow
import java.io.BufferedReader
import java.lang.StringBuilder
import javax.swing.text.StyledDocument
import javax.swing.text.SimpleAttributeSet
import javax.swing.text.StyleConstants
import app.interfaces.event.FrameOrganizer
import java.awt.Component
import java.awt.Dimension
import java.awt.Frame
import java.io.InputStreamReader
import java.lang.Exception
import java.util.*
import javax.swing.*

class DocumentationWindow : Runnable {
    private val frame: JFrame
    override fun run() {
        frame.pack()
        frame.isVisible = true
        foreach(Frame.getFrames()) { frame: Frame -> println(frame.title) }
    }

    companion object {
        /**
         * @param args
         * @throws IOException
         */
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            DocumentationWindow().run()
        }
    }

    init {
        val url = javaClass.getResource("/icons/others/documentation_icon.png")!!
        val icon = ImageIcon(url)
        frame = JFrame("Music Player | Documentation")
        frame.iconImage = icon.image
        frame.setSize(500, 600)
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        val label = JLabel("Music Player - Documentation")
        label.icon = icon
        label.alignmentX = Component.CENTER_ALIGNMENT
        val textArea = JTextPane()
        textArea.isEditable = false
        val br = BufferedReader(
                InputStreamReader(Objects.requireNonNull(javaClass.getResource("/readme_1.txt")).openStream()))
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
        frame.add(label)
        frame.add(Box.createVerticalStrut(7))
        frame.add(scrollPane)
        frame.isUndecorated = true
        frame.addComponentListener(FrameOrganizer(frame))
    }
}