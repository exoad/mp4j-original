package app.interfaces.theme

import java.awt.Window
import javax.swing.UnsupportedLookAndFeelException

interface Refresh {
    @Throws(UnsupportedLookAndFeelException::class)
    fun refresh(jfts: Window)
}