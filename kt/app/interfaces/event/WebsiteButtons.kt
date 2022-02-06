package app.interfaces.event

import app.interfaces.dialog.ErrorMessage
import java.awt.Desktop
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.IOException
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