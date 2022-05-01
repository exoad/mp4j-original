package project.components.sub_components;

import project.audio.content.AudioType;
import project.constants.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FileViewPanel extends JFileChooser {
  private transient Thread worker;

  public FileViewPanel() {
    super();
    setFileSelectionMode(JFileChooser.FILES_ONLY);
    setApproveButtonText("Select");
    setPreferredSize(new Dimension(Size.WIDTH - 650, Size.HEIGHT));
    setMaximumSize(getPreferredSize());
    setAcceptAllFileFilterUsed(false);
    setBackground(Color.GRAY);
    setApproveButtonToolTipText("Select the file");
    setControlButtonsAreShown(false);
    setMultiSelectionEnabled(false);
    setAcceptAllFileFilterUsed(false);
    addChoosableFileFilter(new AudioType());
  }

  @Override
  public JDialog createDialog(Component parent) {
    JDialog dialog = super.createDialog(parent);
    dialog.setPreferredSize(new Dimension(Size.WIDTH - 670, Size.HEIGHT - 400));
    dialog.setMaximumSize(new Dimension(Size.WIDTH - 670, Size.HEIGHT - 400));
    return dialog;
  }

  public synchronized void getAl() {
    ActionListener[] f = getActionListeners();
    for (ActionListener a : f) {
      System.out.println(a.toString());
    }
  }

  public void dispatch() {
    worker = new Thread(() -> {
      rescanCurrentDirectory();
      while (true) {
        try {
          Thread.sleep(800);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void close() {
    worker.interrupt();
  }
}
