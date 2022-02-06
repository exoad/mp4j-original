package app

import app.global.cli.CliColors
import app.global.cli.CliException
import app.global.cli.CliType
import app.java.HardLock

object CLI {
    private const val consoleLikeDir = " > MPlayer4J CLI $ "
    private const val cliLikeDir = " > MPlayer4J USR @ "

    @Throws(CliException::class)
    private fun out(s: Any?, type: CliType) {
        if (!HardLock.CLI) {
            return
        }
        nl()
        if (type === CliType.ERROR) {
            println(CliColors.UNDERLINE.color + CliColors.RED_BG.color + CliColors.BOLD.color
                    + CliColors.WHITE_TXT.color
                    + consoleLikeDir + CliColors.RESET.color + " " + s)
        } else if (type === CliType.WARNING) {
            println(CliColors.UNDERLINE.color
                    + CliColors.YELLOW_BG.color + CliColors.BOLD.color + CliColors.WHITE_TXT.color
                    + consoleLikeDir + CliColors.RESET.color
                    + " " + s)
        } else if (type === CliType.INFO) {
            println(CliColors.UNDERLINE.color
                    + CliColors.BLUE_BG.color + CliColors.BOLD.color + CliColors.WHITE_TXT.color
                    + consoleLikeDir + CliColors.RESET.color
                    + " " + s)
        } else if (type === CliType.SUCCESS) {
            println(CliColors.UNDERLINE.color
                    + CliColors.GREEN_BG.color + CliColors.BOLD.color + CliColors.WHITE_TXT.color
                    + consoleLikeDir + CliColors.RESET.color
                    + " " + s)
        } else if (type === CliType.CHARM) print(CliColors.UNDERLINE.color
                + CliColors.MAGENTA_BG.color + CliColors.BOLD.color + CliColors.WHITE_TXT.color
                + cliLikeDir + CliColors.RESET.color
                + " " + s) else throw CliException("Unusable CLI_TYPE: $type")
    }

    fun print(j: Any?) {
        try {
            out(j, CliType.INFO)
        } catch (e: CliException) {
            print(e, CliType.ERROR)
        }
    }

    fun nl() {
        println()
    }

    @JvmStatic
    fun print(j: Any?, type: CliType) {
        try {
            out(j, type)
        } catch (e: CliException) {
            System.err.print(e)
            System.exit(2)
        }
    }

}