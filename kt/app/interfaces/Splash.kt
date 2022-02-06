package app.interfaces

import app.interfaces.dialog.ErrorMessage
import java.util.*
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JWindow
import javax.swing.SwingConstants
import kotlin.math.floor

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
            Thread.sleep(floor(seconds.toDouble()).toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
            ErrorMessage(Arrays.toString(e.stackTrace))
        }
        window.isVisible = false
        window.dispose()
    }
}