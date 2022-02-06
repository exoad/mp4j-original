package backend.setup

import app.core.Host.Companion.returnJava
import app.global.Items
import app.interfaces.setup.JBRNotFound
import java.io.File
import java.io.IOException

object CheckSetup {
    fun checkNativeDirs() {
        val apiCache = File(Items.items[0])
        if (!apiCache.isDirectory) {
            apiCache.mkdir()
        }
        val mpSaves = File(Items.items[1])
        if (!mpSaves.isDirectory) {
            mpSaves.mkdir()
        }
        val customs = File(Items.items[5])
        if (!customs.isDirectory) {
            customs.mkdir()
        }
        val mpLogs = File(Items.items[2])
        if (!mpLogs.isDirectory) {
            mpLogs.mkdir()
        }
    }

    fun checkJBR() {
        try {
            if (returnJava()!!.contains("JBR")) {
                JBRNotFound().show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
    }
}