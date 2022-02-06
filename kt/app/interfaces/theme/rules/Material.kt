package app.interfaces.theme.rules

import app.interfaces.theme.Refresh
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme
import java.awt.Window
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException

/**
 * @author Jack Meng
 */
class Material : Refresh {
    @Throws(UnsupportedLookAndFeelException::class)
    override fun refresh(frame: Window) {
        UIManager.setLookAndFeel(FlatMaterialDarkerIJTheme())
        try {
            SwingUtilities.updateComponentTreeUI(frame)
        } catch (e: NullPointerException) {
            // do nothing
            e.addSuppressed(e)
        }
        frame.pack()
    }

    override fun toString(): String {
        return "material"
    }
}