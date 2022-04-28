package project.components.sub_components;

import javax.swing.JFileChooser;
import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;

import project.audio.content.AudioType;
import project.constants.Size;

public class FileViewPanel extends JFileChooser {
  private transient Thread worker;

  public FileViewPanel() {
    super();
    setFileSelectionMode(JFileChooser.FILES_ONLY);
    setApproveButtonText("Select");
    setPreferredSize(new Dimension(Size.WIDTH - 670, Size.PREV_HEIGHT - 300));
    setAcceptAllFileFilterUsed(false);
    setBackground(Color.GRAY);
    setApproveButtonToolTipText("Select the file");
    setControlButtonsAreShown(false);
    setBounds(0, 0, Size.WIDTH - 670, Size.PREV_HEIGHT - 300);
    setMultiSelectionEnabled(false);
    setAcceptAllFileFilterUsed(false);
    addChoosableFileFilter(new AudioType());
  }

  @Override
  public JDialog createDialog(Component parent) {
    JDialog dialog = super.createDialog(parent);
    dialog.setPreferredSize(new Dimension(Size.WIDTH - 670, Size.PREV_HEIGHT - 300));
    dialog.setMaximumSize(new Dimension(Size.WIDTH - 670, Size.PREV_HEIGHT - 300));
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
