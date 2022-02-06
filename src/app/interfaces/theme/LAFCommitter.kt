package app.interfaces.theme

import app.core.PropertiesReader.setProperty
import app.interfaces.theme.Refresh
import kotlin.Throws
import javax.swing.UnsupportedLookAndFeelException
import javax.swing.UIManager
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme
import javax.swing.SwingUtilities
import java.lang.NullPointerException
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMonokaiProContrastIJTheme
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme
import com.formdev.flatlaf.FlatDarkLaf
import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme
import app.interfaces.theme.rules.RegularDark
import app.interfaces.theme.rules.Vuesion
import app.interfaces.theme.rules.OneDark
import app.interfaces.theme.rules.ArcDark
import app.interfaces.theme.rules.Material
import app.interfaces.theme.rules.Dracula
import app.interfaces.theme.rules.Nord
import app.interfaces.theme.rules.Gruvbox
import app.interfaces.theme.rules.SolarizedLight
import app.interfaces.theme.rules.RegularLight
import app.interfaces.theme.rules.Monokai
import app.interfaces.theme.rules.MaterialLighter
import java.io.IOException
import app.core.PropertiesReader
import app.interfaces.theme.LAFCommitter
import app.interfaces.dialog.ErrorMessage
import java.awt.Frame
import java.lang.Exception
import java.util.ArrayList
import java.util.Arrays

class LAFCommitter(vararg jfxs: Frame?) {
    fun setMultTheme(theme: Refresh?) {
        try {
            for (jfts in jfxs) {
                if (theme == null) {
                    ErrorMessage("""
    Unable to process the specified Refresh theme.
    Trace Dump: ${Arrays.toString(jfxs.toTypedArray())}
    Argument Type:${jfts.javaClass.name}
    """.trimIndent())
                    return
                }
                theme.refresh(jfts)
            }
        } catch (ex: Exception) {
            // do nothing
        }
    }

    companion object {
        private val jfxs = ArrayList<Frame>()
    }

    init {
        Companion.jfxs.addAll(Arrays.asList(*jfxs))
    }
}