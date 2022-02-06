package app.core

import kotlin.Throws
import java.io.IOException
import java.util.HashMap
import app.core.PropertiesReader
import app.global.Items
import java.util.Properties
import app.global.Sources
import java.io.FileInputStream
import app.rules.AllowedProperties
import java.io.FileOutputStream
import app.rules.DefProperties
import java.io.File

/**
 * @author Jack Meng
 */
class PropertiesReader {
    @Throws(IOException::class)
    fun keyyedProp(): Map<String, String> {
        val properties = HashMap<String, String>()
        p = Properties()
        if (!File(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE).exists()) {
            reset()
        }
        FileInputStream(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE).use { isr ->
            p!!.load(isr)
            if (AllowedProperties.valTheme(p!!.getProperty("gui.defaultTheme"))) properties["gui.defaultTheme"] = p!!.getProperty("gui.defaultTheme")
            if (AllowedProperties.valCache(p!!.getProperty("runner.disableCache"))) properties["runner.disableCache"] = p!!.getProperty("runner.disableCache")
            if (AllowedProperties.valBoxSize(p!!.getProperty("gui.defaultBoxSize"))) properties["gui.defaultBoxSize"] = p!!.getProperty("gui.defaultBoxSize")
            if (AllowedProperties.valBox(p!!.getProperty("gui.buttonShape"))) {
                properties["gui.buttonShape"] = p!!.getProperty("gui.buttonShape")
            }
            if (AllowedProperties.valTransparency(p!!.getProperty("gui.window_transparency"))) {
                properties["gui.window_transparency"] = p!!.getProperty("gui.window_transparency")
            }
        }
        return properties
    }

    fun checkPropertiesAll(): Boolean {
        p = Properties()
        if (!File(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE).exists()) {
            return false
        }
        try {
            FileInputStream(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE).use { isr ->
                p!!.load(isr)
                if (!AllowedProperties.valTheme(p!!.getProperty("gui.defaultTheme")) || !AllowedProperties.valCache(p!!.getProperty("runner.disableCache")) || !AllowedProperties.valBoxSize(p!!.getProperty("gui.defaultBoxSize")) || !AllowedProperties.valBox(p!!.getProperty("gui.buttonShape")) || !AllowedProperties.valTransparency(p!!.getProperty("gui.window_transparency"))) {
                    return false
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return true
    }

    @Throws(IOException::class)
    fun setProperty(key: String?, value: String): String {
        p!!.setProperty(key, value)
        FileOutputStream(
                File(Items.items[1].toString() + System.getProperty("file.separator") + Sources.PROPERTIES_FILE)).use { os -> p!!.store(os, Items.PROPERTIES_HEADER_COMMENT) }
        return value
    }

    fun getVal(key: String?): String {
        return p!!.getProperty(key)
    }

    companion object {
        private var p: Properties? = null
        fun reset(): Boolean {
            try {
                val t = File(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE)
                if (!t.exists()) t.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                FileOutputStream(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE).use { os ->
                    p = Properties()
                    p!!.setProperty("gui.defaultTheme", DefProperties.DEFAULT_GUI_LAF)
                    p!!.setProperty("runner.disableCache", DefProperties.DISABLE_CACHE)
                    p!!.setProperty("gui.defaultBoxSize", DefProperties.DEFAULT_BOX_SIZE.toString())
                    p!!.setProperty("gui.buttonShape", DefProperties.DEFAULT_BUTTON_SHAPE)
                    p!!.setProperty("gui.window_transparency", DefProperties.DEFAULT_WINDOW_TRANSPARENCY)
                    p!!.store(os, Items.PROPERTIES_HEADER_COMMENT)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            }
            return true
        }
    }

    init {
        println("""
    hasAll${checkPropertiesAll()}
    File exists? ${File(Items.items[1].toString() + System.getProperty("file.separator") + Sources.PROPERTIES_FILE).exists()}
    """.trimIndent())
        if (!File(Items.items[1].toString() + "/" + Sources.PROPERTIES_FILE).exists()) {
            reset()
        }
    }
}