package app.interfaces.event

import app.core.PropertiesReader.getVal
import javax.swing.JFrame
import javax.swing.JComponent
import java.awt.event.ComponentListener
import app.core.PropertiesReader
import java.awt.event.ComponentEvent
import java.awt.geom.RoundRectangle2D
import app.CLI
import java.awt.RenderingHints
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import java.awt.Desktop
import java.io.IOException
import app.interfaces.dialog.ErrorMessage
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * @author Jack Meng
 */
class WebsiteButtons(private val url: String) : ActionListener {
    override fun actionPerformed(e: ActionEvent) {
        try {
            Desktop.getDesktop().browse(URI(url))
        } catch (e1: IOException) {
            e1.printStackTrace()
            ErrorMessage(Arrays.toString(e1.stackTrace))
        } catch (e1: URISyntaxException) {
            e1.printStackTrace()
            ErrorMessage(Arrays.toString(e1.stackTrace))
        }
    }
}