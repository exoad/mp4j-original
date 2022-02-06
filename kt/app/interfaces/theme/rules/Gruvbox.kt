package app.interfaces.theme.rules

import app.interfaces.theme.Refresh
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme
import java.awt.Window
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException

/**
 * @author Jack Meng
 */
class Gruvbox : Refresh {
    @Throws(UnsupportedLookAndFeelException::class)
    override fun refresh(frame: Window) {
        UIManager.setLookAndFeel(FlatGruvboxDarkMediumIJTheme())
        try {
            SwingUtilities.updateComponentTreeUI(frame)
        } catch (e: Exception) {
            // do nothing
        }
        frame.pack()
    }

    override fun toString(): String {
        return "gruvbox"
    }
}