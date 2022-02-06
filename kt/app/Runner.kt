package app

import app.CLI.print
import app.core.PropertiesReader
import app.global.Items
import app.global.Sources
import app.global.cli.CliType
import app.interfaces.Splash
import app.interfaces.WelcomeWindow
import backend.setup.CheckSetup
import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.intellijthemes.*
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMonokaiProContrastIJTheme
import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import javax.swing.UIManager

/**
 * <h1>Runner</h1>
 *
 *
 * This class makes sure everything
 * is running the same events and
 * reads the data from the save locations
 * and loads them into the following
 * windows and classes
 *
 *
 *
 * @author Jack Meng
 *
 * @since 1.1
 * @see app.global.Sources
 *
 * @see app.Runner
 *
 * @see app.global.Items
 *
 * @see app.interfaces.Splash
 *
 * @see app.interfaces.WelcomeWindow
 */
class Runner {
    private val pr: PropertiesReader

    /**
     * @return String
     * @throws IOException IO is used here
     */
    @Throws(IOException::class)
    fun readInfo(): String {
        if (File(Items.items[1] + System.getProperty("file.separator") + Sources.LIFEPRESERVER_PREVDIR)
                        .exists()
                || File(Items.items[1] + System.getProperty("file.separator") + Sources.LIFEPRESERVER_PREVDIR)
                        .isDirectory) {
            val br = BufferedReader(
                    FileReader(Items.items[1] + System.getProperty("file.separator")
                            + Sources.LIFEPRESERVER_PREVDIR))
            val sb = StringBuilder()
            var line = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }
            br.close()
            return sb.toString()
        }
        return "."
    }

    @Throws(IOException::class)
    fun run(): Boolean {

        //CLI.print(Runner.readInfo());
        //CLI.print(Runner.class);
        try {
            initLAF()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        try {
            val socket = Socket()
            socket.connect(InetSocketAddress("google.com", 80), 3000)
            socket.close()
        } catch (e: IOException) {
            val bw = BufferedWriter(FileWriter(Items.items[6]))
            bw.write("0")
            bw.close()
            return false
        }
        val bw = BufferedWriter(FileWriter(Items.items[6]))
        bw.write("1")
        bw.close()
        return true
    }

    @Throws(IOException::class)
    private fun initLAF() {
        //CLI.print(new PropertiesReader().toString(), app.global.cli.CliType.WARNING);
        if (pr.getVal("gui.buttonShape") == "round") {
            UIManager.put("Button.arc", 999)
            UIManager.put("CheckBox.arc", 999)
            UIManager.put("ComboBox.arc", 999)
            UIManager.put("TextComponent.arc", 999)
        } else if (pr.getVal("gui.buttonShape") == "square" || pr.getVal("gui.buttonShape") == "default") {
            UIManager.put("Button.arc", 0)
            UIManager.put("CheckBox.arc", 0)
            UIManager.put("ComboBox.arc", 0)
            UIManager.put("TextComponent.arc", 0)
        }
        val laf = pr.getVal("gui.defaultTheme")
        when (laf) {
            "material" -> {
                FlatMaterialDarkerIJTheme.setup()
                print("Material Theme", CliType.INFO)
            }
            "onedark" -> {
                FlatOneDarkIJTheme.setup()
                print("One Dark Theme", CliType.INFO)
            }
            "arcdark" -> {
                FlatArcDarkIJTheme.setup()
                print("Arc Dark Theme", CliType.INFO)
            }
            "nord" -> {
                FlatNordIJTheme.setup()
                print("Nord Theme", CliType.INFO)
            }
            "dracula" -> {
                FlatDraculaIJTheme.setup()
                print("Dracula Theme", CliType.INFO)
            }
            "gruvbox" -> {
                FlatGruvboxDarkMediumIJTheme.setup()
                print("Gruvbox Theme", CliType.INFO)
            }
            "vuesion" -> {
                FlatVuesionIJTheme.setup()
                print("Vuesion Theme", CliType.INFO)
            }
            "regularlight" -> {
                FlatLightLaf.setup()
                print("Regular Light Theme", CliType.INFO)
            }
            "solarized" -> {
                FlatSolarizedLightIJTheme.setup()
                print("Solarized Light Theme", CliType.INFO)
            }
            "gradientogreen" -> {
                FlatMonokaiProContrastIJTheme.setup()
                print("Gradiento Green Theme", CliType.INFO)
            }
            "materiallighter" -> {
                FlatMaterialLighterIJTheme.setup()
                print("Material Light Theme", CliType.INFO)
            }
        }
    }

    companion object {
        /**
         * @param args
         * @throws InterruptedException
         * @throws IOException
         * @throws CliException
         */
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            /**
             * Disposed:
             *
             * Thread cli = new Thread(CLI::runAsInterface);
             */

            //System.setProperty("flatlaf.useWindowDecorations", "true");
            CheckSetup.checkJBR()
            val mpSaves = File(Items.items[1])
            if (!mpSaves.isDirectory) {
                mpSaves.mkdir()
            }
            val apiCache = File(Items.items[0])
            if (!apiCache.isDirectory) {
                apiCache.mkdir()
            }
            val customs = File(Items.items[5])
            if (!customs.isDirectory) {
                customs.mkdir()
            }
            val mpLogs = File(Items.items[2])
            if (!mpLogs.isDirectory) {
                mpLogs.mkdir()
            }
            val mpInternetCache = File(Items.items[6])
            if (!mpInternetCache.isFile) {
                mpInternetCache.createNewFile()
            }
            val b = Runner()
            b.run()
            Splash(Items.SPLASH_SECONDS).run()
            WelcomeWindow(b.readInfo()).run()


            //cli.start(); - Until fully implemented for a standard CLI-based debug session
        }
    }

    init {
        pr = PropertiesReader()
    }
}