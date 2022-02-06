package app.interfaces.theme

import app.interfaces.dialog.ErrorMessage
import java.awt.Frame
import java.util.*

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
    
}


