package project.components.windows;

import javax.swing.*;

import project.Utils;

import java.awt.*;

public class SimpleDebugWindow implements Runnable {
  private JFrame frame;
  private JPanel panel;
  private JLabel memory;
  private Thread worker;

  public SimpleDebugWindow() {
    frame = new JFrame("MP4J - Debug Window");
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setAlwaysOnTop(false);
    frame.setResizable(false);
    frame.setPreferredSize(new Dimension(300, 300));

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    memory = new JLabel("JVM Memory: u/m");

    panel.add(memory);
    frame.getContentPane().add(panel);
  }

  @Override
  public void run() {
    frame.pack();
    frame.setVisible(true);
    worker = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        memory.setText("JVM Memory: "
            + Utils.bytesToMegabytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + "mb/"
            + Utils.bytesToMegabytes(Runtime.getRuntime().totalMemory()) + "mb");
      }
    });
    worker.start();
  }
}
