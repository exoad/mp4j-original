package app.interfaces.theme

import app.core.PropertiesReader
import app.interfaces.theme.rules.*
import java.io.IOException

class Parser(theme: String?) {
    private val theme: String?
    private val themePartsRegularDark = arrayOf(
            "regulardark",
            "regular dark",
            "REGULARDARK",
            "Regular Dark")
    private val themePartsVuesion = arrayOf(
            "vuesion",
            "VUESION",
            "Vuesion"
    )
    private val themePartsOneDark = arrayOf(
            "onedark",
            "one dark",
            "ONEDARK",
            "One Dark")
    private val themePartsArcDark = arrayOf(
            "arcdark",
            "arc dark",
            "ARCDARK",
            "Arc Dark")
    private val themePartsMaterialDark = arrayOf(
            "materialdark",
            "material dark",
            "MATERIALDARK",
            "Material Dark")
    private val themePartsDracula = arrayOf(
            "dracula",
            "DRACULA",
            "Dracula"
    )
    private val themePartsNord = arrayOf(
            "nord",
            "NORD",
            "Nord"
    )
    private val themePartsGruvbox = arrayOf(
            "gruvbox",
            "GRUVBOX",
            "Gruvbox"
    )
    private val themePartsSolarized = arrayOf(
            "solarized",
            "SOLARIZED",
            "Solarized"
    )
    private val themePartsRegularLight = arrayOf(
            "regularlight",
            "regular light",
            "REGULARLIGHT",
            "Regular Light")
    private val themePartsMaterialLighter = arrayOf(
            "materiallighter",
            "material lighter",
            "MATERIALLIGHTER",
            "Material Lighter")
    private val themePartsMonokai = arrayOf(
            "Monokai",
            "monokai",
            "MONOKAI")

    private fun contains(array: Array<String>, value: String?): Boolean {
        for (s in array) {
            if (s == value) {
                return true
            }
        }
        return false
    }

    fun getTheme(): Refresh {
        return if (contains(themePartsRegularDark, theme)) {
            RegularDark()
        } else if (contains(themePartsVuesion, theme)) {
            Vuesion()
        } else if (contains(themePartsOneDark, theme)) {
            OneDark()
        } else if (contains(themePartsArcDark, theme)) {
            ArcDark()
        } else if (contains(themePartsMaterialDark, theme)) {
            Material()
        } else if (contains(themePartsDracula, theme)) {
            Dracula()
        } else if (contains(themePartsNord, theme)) {
            Nord()
        } else if (contains(themePartsGruvbox, theme)) {
            Gruvbox()
        } else if (contains(themePartsSolarized, theme)) {
            SolarizedLight()
        } else if (contains(themePartsRegularLight, theme)) {
            RegularLight()
        } else if (contains(themePartsMonokai, theme)) {
            Monokai()
        } else if (contains(themePartsMaterialLighter, theme)) {
            MaterialLighter()
        } else {
            RegularDark()
        }
    }

    @Throws(IOException::class)
    fun parseThemeToProperty() {
        PropertiesReader().setProperty("gui.defaultTheme", getTheme().toString())
    }

    init {
        assert(theme != null)
        this.theme = theme
    }
}