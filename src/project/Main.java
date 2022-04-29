package project;

import com.formdev.flatlaf.FlatDarkLaf;
import project.audio.Overseer;
import project.components.BigContainer;
import project.components.ParentPanel;
import project.components.ProcessesSchedule;
import project.components.sub_components.FileViewPanel;
import project.components.sub_components.FileViewWrapper;
import project.components.sub_components.InfoView;
import project.components.sub_components.infoview.BottomView;
import project.components.sub_components.infoview.TopView;
import project.constants.ProjectManager;
import project.constants.Size;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
  private static void launch() {
    ParentPanel pb;
    FileViewPanel fileViewPanel = new FileViewPanel();
    Map<JComponent, String> panels = new HashMap<>();
    TopView tv = new TopView();
    Overseer overseer = new Overseer(null, fileViewPanel, tv);
    BottomView bw = new BottomView(overseer);
    JSplitPane otherSide = new InfoView(tv, bw);
    otherSide.setDividerLocation(Size.HEIGHT - 100);
    fileViewPanel.getAl();
    FileViewWrapper fvw = new FileViewWrapper(fileViewPanel, overseer);
    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, otherSide, fvw);
    jsp.setDividerLocation(Size.WIDTH - fvw.getWidth() - 20);
    fileViewPanel.dispatch();
    panels.put(jsp, BorderLayout.CENTER);
    pb = new ParentPanel(panels);
    new BigContainer(pb).run();

    // make a thread to print how much memory this program is using in mb
    if (ProjectManager.DEBUG_LAYOUT) {
      new Thread(() -> {
        while (true) {
          System.out.println(
              "Used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "mb");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("Divider Pos: " + otherSide.getDividerLocation());
          System.out.println("BigContainer Size: " + pb.getSize().toString());
        }
      }).start();
    }
  }

  /**
   * @param args
   */
  public static synchronized void main(String[] args) {
    try {
      System.setProperty("flatlaf.useJetBrainsCustomDecorations", "false");
      Thread.sleep(100);
      FlatDarkLaf.setup();
      ProcessesSchedule.main();
      Thread.sleep(100);
      launch();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
