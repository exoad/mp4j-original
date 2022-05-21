package project;

import project.audio.Overseer;
import project.components.BigContainer;
import project.components.ParentPanel;
import project.components.ProcessesSchedule;
import project.components.sub_components.FileViewPanel;
import project.components.sub_components.FileViewWrapper;
import project.components.sub_components.InfoView;
import project.components.sub_components.infoview.BottomView;
import project.components.sub_components.infoview.TopView;
import project.connection.discord.DiscordRPCHandler;
import project.constants.ProjectManager;
import project.constants.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Main implements ActionListener {
  static {
    System.setProperty("file.encoding", "UTF-8");
    System.setProperty("sun.jnu.encoding", "UTF-8");
  }
  private BigContainer e;
  public void launch() {
    System.setProperty("flatlaf.useJetBrainsCustomDecorations", "true");
    DiscordRPCHandler disch = new DiscordRPCHandler();
    disch.start();
    ParentPanel pb;
    FileViewPanel fileViewPanel = new FileViewPanel();
    Map<JComponent, String> panels = new HashMap<>();
    TopView tv = new TopView();
    Overseer overseer = new Overseer(null, fileViewPanel, tv, disch);
    BottomView bw = new BottomView(overseer);
    JSplitPane otherSide = new InfoView(tv, bw);
    otherSide.setDividerLocation(Size.HEIGHT - 100);
    fileViewPanel.getAl();
    FileViewWrapper fvw = new FileViewWrapper(this, fileViewPanel, overseer);
    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, otherSide, fvw);
    jsp.setDividerLocation(Size.WIDTH - fvw.getWidth() - 20);
    fileViewPanel.dispatch();
    panels.put(jsp, BorderLayout.CENTER);
    pb = new ParentPanel(panels);
    e = new BigContainer(pb);
    e.run();
    
    // make a thread to print how much memory this program is using in mb
    if (ProjectManager.DEBUG_LAYOUT) {
      new Thread(() -> {
        while (true) {
          System.out.println(
              "Used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "mb");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          System.out.println("Divider Pos: " + otherSide.getDividerLocation());
          System.out.println("BigContainer Size: " + pb.getSize().toString());
        }
      }).start();
    }
  }

  public static final PrintStream STDOUT = System.out;

  /**
   * @param args
   */
  public static synchronized void main(String[] args) {
    
    try {
      ProcessesSchedule.main();
      new Main().launch();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  /** 
   * @param arg0
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    e.getBigFrame().setSize(new Dimension(Size.WIDTH, Size.HEIGHT));
  }
}
