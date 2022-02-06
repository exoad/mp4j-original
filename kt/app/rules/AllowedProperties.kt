package app.rules

import java.util.HashSet
import app.rules.AllowedProperties
import app.CLI
import app.global.cli.CliType

class AllowedProperties private constructor() {
    companion object {
        protected val allowedDarkLaf: MutableSet<String> = HashSet()
        protected val allowedLiteLaf: MutableSet<String> = HashSet()
        protected val allowedDefCache: MutableSet<String> = HashSet()
        protected val allowedDefDirs: Set<String> = HashSet()
        protected val allowedButtonShape: MutableSet<String> = HashSet()
        fun valTheme(s: String): Boolean {
            return allowedDarkLaf.contains(s) || allowedLiteLaf.contains(s) && (!s.isBlank() || s.isBlank())
        }

        fun valCache(s: String): Boolean {
            CLI.print(allowedDefCache.contains(s), CliType.INFO)
            return allowedDefCache.contains(s) && (!s.isBlank() || s.isBlank())
        }

        fun valBox(s: String): Boolean {
            CLI.print(allowedButtonShape.contains(s), CliType.INFO)
            return allowedButtonShape.contains(s) && (!s.isBlank() || s.isBlank())
        }

        fun valBoxSize(a: String): Boolean {
            val b = a.toInt()
            CLI.print(b >= 1 && b <= 32, CliType.INFO)
            return b >= 1 && b <= 32
        }

        fun valTransparency(a: String): Boolean {
            val d = a.toDouble()
            CLI.print(d >= 0.0 && d <= 1.0, CliType.INFO)
            return d > 0 && d <= 1
        }
    }

    init {
        allowedDarkLaf.add("regulardark")
        allowedDarkLaf.add("material")
        allowedDarkLaf.add("onedark")
        allowedDarkLaf.add("arcdark")
        allowedDarkLaf.add("dracula")
        allowedDarkLaf.add("nord")
        allowedDarkLaf.add("gruvbox")
        allowedDarkLaf.add("vuesion")
        allowedLiteLaf.add("regularlight")
        allowedLiteLaf.add("solarized")
        allowedLiteLaf.add("gradientogreen")
        allowedLiteLaf.add("materiallighter")
        allowedDefCache.add("false")
        allowedDefCache.add("true")
        allowedButtonShape.add("round")
        allowedButtonShape.add("square")
        allowedButtonShape.add("default")
    }
}