package app.core

import app.core.ico.IconRules
import kotlin.Throws
import java.io.IOException
import app.core.LifePreserver
import javax.swing.JFileChooser
import app.core.Host
import app.interfaces.dialog.ErrorMessage
import java.awt.Component
import java.awt.Desktop
import java.awt.Dimension
import java.lang.Runtime
import java.lang.Process
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URI
import java.util.*

/**
 * @author Jack Meng
 */
class Host(lastDir: String?) {
    companion object {
        private var lastDir: String? = ""
        private val ir = IconRules()

        /**
         * @param i
         * @throws IOException
         */
        @Throws(IOException::class)
        fun extendedFileSaver(i: File) {
            val lp = LifePreserver(i.absolutePath)
            lp.saveToPrevDir()
        }

        /**
         * @param parent
         * @return File
         */
        fun openFileBrowser(parent: Component?): File? {
            var fileChooser: JFileChooser? = null
            try {
                fileChooser = JFileChooser()
                fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
                fileChooser.fileView = ir
                fileChooser.dialogTitle = "Select File using File Explorer"
                fileChooser.approveButtonText = "Select"
                fileChooser.approveButtonToolTipText = "Select the file"
                if (lastDir != null || lastDir!!.length == 0) {
                    fileChooser.currentDirectory = File(lastDir)
                } else {
                    fileChooser.currentDirectory = File(".")
                }
                fileChooser.preferredSize = Dimension(700, 700)
                fileChooser.showOpenDialog(parent)
                return fileChooser.selectedFile
            } catch (e: Exception) {
                e.printStackTrace()
                ErrorMessage(Arrays.toString(e.stackTrace))
            }
            return null
        }

        /**
         * @param runtime
         * @param ch
         * @return String
         * @throws IOException
         */
        @Throws(IOException::class)
        fun runProcess(runtime: Runtime, ch: CharSequence): String? {
            val commands = arrayOf(ch.toString(), "-get t")
            val proc = runtime.exec(commands)
            val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
            var s: String? = null
            while (stdInput.readLine().also { s = it } != null) {
                return s
            }
            return null
        }

        @Throws(IOException::class)
        fun returnJava(): String? {
            val rt = Runtime.getRuntime()
            val commands = arrayOf("java", "--version")
            val proc = rt.exec(commands)
            val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
            var s: String? = null
            while (stdInput.readLine().also { s = it } != null) {
                return s
            }
            return null
        }

        fun openInBrowser(url: String?) {
            try {
                Desktop.getDesktop().browse(URI.create(url))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    init {
        Companion.lastDir = lastDir
    }
}