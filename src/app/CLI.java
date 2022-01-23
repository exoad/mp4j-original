package app;

import static java.lang.System.*;

import app.global.cli.CliType;
import app.global.cli.CliColors;
import app.global.cli.CliException;

public abstract class CLI {
  private static final String consoleLikeDir = " > MPlayer4J CLI $ ";

  private CLI() {
  }

  private static void out(Object s, CliType type) throws CliException {
    if (type == CliType.ERROR)
      out.println(CliColors.UNDERLINE.getColor() + CliColors.RED_BG.getColor() + CliColors.BOLD.getColor() + CliColors.BLACK_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor() + " " + s);
    else if (type == CliType.WARNING)
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.YELLOW_BG.getColor() + CliColors.BOLD.getColor() + CliColors.BLACK_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor()
          + " " + s);
    else if (type == CliType.INFO)
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.BLUE_BG.getColor() + CliColors.BOLD.getColor() + CliColors.BLACK_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor()
          + " " + s);
    else if (type == CliType.SUCCESS)
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.GREEN_BG.getColor() + CliColors.BOLD.getColor() + CliColors.BLACK_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor()
          + " " + s);
    else
      throw new CliException("Unusable CLI_TYPE: " + type);
  }

  public static void print(Object j) {
    try {
      out(j, CliType.INFO);
    } catch (CliException e) {
      CLI.print(e, CliType.ERROR);
    }
  }

  public static void print(Object j, CliType type) {
    try {
      out(j, type);
    } catch (CliException e) {
      System.err.print(e);
      System.exit(2);
    }
  }

}
