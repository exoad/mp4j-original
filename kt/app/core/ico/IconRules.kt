package app.core.ico

import java.io.File
import java.util.*
import javax.swing.filechooser.FileView
import javax.swing.ImageIcon
import javax.swing.Icon

class IconRules : FileView() {
    private val icons = Hashtable<String, ImageIcon>()
    fun fetchByKey(token: String): ImageIcon? {
        return icons[token]
    }

    override fun getIcon(file: File): Icon {
        val fileName = file.name
        if (file.isDirectory) {
            return fetchByKey("folder")!!
        }
        return if (fileName.endsWith(".mp3")) {
            fetchByKey(".mp3")!!
        } else if (fileName.endsWith(".wav")) {
            fetchByKey(".wav")!!
        } else if (fileName.endsWith(".aiff")) {
            fetchByKey(".aiff")!!
        } else if (fileName.endsWith(".aif")) {
            fetchByKey(".aif")!!
        } else if (fileName.endsWith(".au")) {
            fetchByKey(".au")!!
        } else {
            fetchByKey("null")!!
        }
    }

    init {
        icons[".mp3"] = ImageIcon(javaClass.getResource("/icons/audio-type/mp3-type.png"))
        icons[".wav"] = ImageIcon(javaClass.getResource("/icons/audio-type/wav-type.png"))
        icons[".aiff"] = ImageIcon(javaClass.getResource("/icons/audio-type/aiff-type.png"))
        icons[".aif"] = ImageIcon(javaClass.getResource("/icons/audio-type/aiff-type.png"))
        icons[".au"] = ImageIcon(javaClass.getResource("/icons/audio-type/au-type.png"))
        icons["null"] = ImageIcon(javaClass.getResource("/icons/audio-type/unknown-type.png"))
        icons["folder"] = ImageIcon(javaClass.getResource("/icons/others/file_select_folder_icon.png"))
    }
}