package project.components;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import project.connection.discord.DiscordRPCHandler;
import project.constants.ColorContent;
import project.constants.ProjectManager;

public class ProcessesSchedule {
  public static void main(String... args) {
    UIManager.put("FileChooser.readOnly", true);
    PrintStream s = new PrintStream(OutputStream.nullOutputStream());
    if (ProjectManager.PRODUCTION_STYLE) {
      FlatLightLaf.setup();
    } else {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
          | UnsupportedLookAndFeelException e) {
        e.printStackTrace();
      }
    }
    System.setErr(null);
    DiscordRPCHandler.main(args);
  }
}
