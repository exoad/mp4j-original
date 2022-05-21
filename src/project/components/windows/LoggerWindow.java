package project.components.windows;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoggerWindow extends JFrame {
  private static class LoggerOutStream extends java.io.OutputStream {
    private JEditorPane textArea;

    public LoggerOutStream(JEditorPane textArea) {
      this.textArea = textArea;
    }
    
    @Override
    public synchronized void write(byte[] buffer, int offset, int length) throws IOException {
      final String text = new String(buffer, offset, length);
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          String str = textArea.getText();
          textArea.setText(str + text);
        }
      });
    }

    @Override
    public synchronized void write(int b) throws IOException {
      write(new byte[] { (byte) b }, 0, 1);
    }
  }

  private JEditorPane textArea;
  private JPanel p;

  public LoggerWindow() {
    p = new JPanel();
    p.setPreferredSize(new Dimension(400, 400));
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    try {
      setIconImage(new ImageIcon(getClass().getResource("/icons/others/frame-icon.png")).getImage());
    } catch (NullPointerException e) {
      // DO NOTHING
    }
    setPreferredSize(new Dimension(500, 400));
    textArea = new JEditorPane();
    textArea.setPreferredSize(new Dimension(400, 400));
    textArea.setEditable(false);
    LoggerOutStream los = new LoggerOutStream(textArea);
    System.setOut(new java.io.PrintStream(los));
    System.setErr(new java.io.PrintStream(los));
    p.add(textArea);
    add(p);
    setLocationRelativeTo(null);
  }

  public synchronized void run() {
    pack();
    setVisible(true);
  }
}
