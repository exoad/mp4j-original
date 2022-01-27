package app.interfaces.event;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.RoundRectangle2D;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class RoundFrame extends JComponent implements ComponentListener {
  private JFrame frame;

  public RoundFrame(JFrame frame) {
    this.frame = frame;
    frame.setUndecorated(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    g2.setRenderingHint(
        java.awt.RenderingHints.KEY_ANTIALIASING,
        java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(
        java.awt.RenderingHints.KEY_RENDERING,
        java.awt.RenderingHints.VALUE_RENDER_QUALITY);

  }

  @Override
  public void componentResized(ComponentEvent e) {

    frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 5, 5));

  }

  @Override
  public void componentMoved(ComponentEvent e) {
    // UNUSED
  }

  @Override
  public void componentShown(ComponentEvent e) {
    // UNUSED
  }

  @Override
  public void componentHidden(ComponentEvent e) {
    // UNUSED
  }

}
