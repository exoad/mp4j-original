package app.core

import kotlin.Throws
import java.io.IOException
import app.core.Host
import kotlin.jvm.JvmStatic
import app.core.CXX

/**
 * @author Jack Meng
 */
class CXX @Deprecated("") constructor() {
    /**
     * @return String
     * @throws IOException
     */
    @Deprecated("")
    @Throws(IOException::class)
    fun callAPI(): String? {
        if (System.getProperty("os.name").contains("Windows")) {
            val windowsAPI = javaClass.getResource("/apiwrapper.exe")!!
            return Host.runProcess(Runtime.getRuntime(), windowsAPI.path)
        }
        return null
    }

    /**
     * @return String
     * @throws IOException
     */
    @Deprecated("")
    @Throws(IOException::class)
    fun veriyFile(): String? {
        if (System.getProperty("os.name").contains("Windows")) {
            val windowsAPI = javaClass.getResource("/fileint.exe")!!
            return Host.runProcess(Runtime.getRuntime(), windowsAPI.path)
        }
        return null
    }

    companion object {
        /**
         * @param args
         * @throws IOException
         */
        @Deprecated("")
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            CXX().callAPI()
        }
    }
}