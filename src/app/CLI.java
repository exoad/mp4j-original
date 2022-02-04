package app;

import static java.lang.System.*;

import java.util.Scanner;

import app.global.cli.CliType;
import app.global.cli.CliColors;
import app.global.cli.CliException;
public abstract class CLI {
  private static final String consoleLikeDir = " > MPlayer4J CLI $ ";
  private static final String cliLikeDir = " > MPlayer4J USR @ ";
  private static final String cliLikeJS = " > Script Out $ ";

  private CLI() {
  }

  private static void out(Object s, CliType type) throws CliException {
    if (type == CliType.ERROR) {
      out.println(CliColors.UNDERLINE.getColor() + CliColors.RED_BG.getColor() + CliColors.BOLD.getColor()
          + CliColors.WHITE_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor() + " " + s);
    } else if (type == CliType.WARNING) {
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.YELLOW_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor()
          + " " + s);
    } else if (type == CliType.INFO) {
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.BLUE_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor()
          + " " + s);
    } else if (type == CliType.SUCCESS) {
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.GREEN_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + consoleLikeDir + CliColors.RESET.getColor()
          + " " + s);
    } else if (type == CliType.CHARM)
      out.print(CliColors.UNDERLINE.getColor()
          + CliColors.MAGENTA_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + cliLikeDir + CliColors.RESET.getColor()
          + " " + s);

    else
      throw new CliException("Unusable CLI_TYPE: " + type);
  }

  public static void scriptOut(Object s, CliType type) {
    if (type == CliType.ERROR) {
      out.println(CliColors.UNDERLINE.getColor() + CliColors.RED_BG.getColor() + CliColors.BOLD.getColor()
          + CliColors.WHITE_TXT.getColor()
          + cliLikeJS + CliColors.RESET.getColor() + " " + s);
    } else if (type == CliType.WARNING) {
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.YELLOW_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + cliLikeJS + CliColors.RESET.getColor()
          + " " + s);
    } else if (type == CliType.INFO) {
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.BLUE_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + cliLikeJS + CliColors.RESET.getColor()
          + " " + s);
    } else if (type == CliType.SUCCESS) {
      out.println(CliColors.UNDERLINE.getColor()
          + CliColors.GREEN_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + cliLikeJS + CliColors.RESET.getColor()
          + " " + s);
    } else if (type == CliType.CHARM)
      out.print(CliColors.UNDERLINE.getColor()
          + CliColors.MAGENTA_BG.getColor() + CliColors.BOLD.getColor() + CliColors.WHITE_TXT.getColor()
          + cliLikeJS + CliColors.RESET.getColor()
          + " " + s);
  }

  public static void print(Object j) {
    try {
      out(j, CliType.INFO);
    } catch (CliException e) {
      CLI.print(e, CliType.ERROR);
    }
  }

  public static void nl() {
    out.println();
  }

  public static void print(Object j, CliType type) {
    try {
      nl();
      out(j, type);
    } catch (CliException e) {
      System.err.print(e);
      System.exit(2);
    }
  }

  public static void runAsInterface() {
    try (Scanner sc = new Scanner(System.in)) {

      while (true) {

        nl();
        print("", CliType.CHARM);
        String input = sc.nextLine();
        if (input.equals("exit"))
          break;
        else if (input.equals("version")) {
          out.print(app.global.VersionInfo.VERSION);
        }

        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
          CLI.print(e.getMessage(), app.global.cli.CliType.ERROR);
        }
      }
    }

  }

}
