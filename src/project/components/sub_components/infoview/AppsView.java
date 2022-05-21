package project.components.sub_components.infoview;

import project.constants.ColorContent;
import project.constants.ProjectManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Arrays;

/**
 * This class represents the waveform animation view.
 * 
 * @author Jack Meng
 */
public class AppsView extends JPanel {
  private JScrollPane pane;
  private final JPanel waveFormDisplay;
  private int[] firstBars;
  public int MAX_DRAW;

  /**
   * Constructor
   * 
   * @param g The designated dimension to be used as the animation panel's
   *          dimensions.
   */
  public AppsView(Dimension g) {
    if (ProjectManager.DEBUG_LAYOUT) {
      setOpaque(true);
      setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));
      setBackground(Color.GREEN);
    }
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, true));
    setOpaque(true);
    MAX_DRAW = 2048 / 4;
    setPreferredSize(new Dimension(MAX_DRAW * 2, g.height));
    setMaximumSize(new Dimension(MAX_DRAW * 2, g.height));
    firstBars = new int[MAX_DRAW];
    Arrays.fill(firstBars, 10);

    waveFormDisplay = new JPanel() {
      @Override
      public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(ColorContent.WAVE_FORM_BAR_X);
        for (int i = 0, x = 0; i < firstBars.length && x < getWidth(); i++, x += 3) {
          if (firstBars[i] <= 10) {
            g2.setColor(ColorContent.WAVE_FORM_LOWER_X);
            g2.fillRect(x, getHeight() - 10, 2, 10);
          } else {
            g2.setColor(ColorContent.WAVE_FORM_BAR_X);
            g2.fillRect(x, getHeight() - firstBars[i], 2, firstBars[i]);
          }
        }
        g2.dispose();
      }
    };
    pane = new JScrollPane(waveFormDisplay);
    pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

    if (ProjectManager.DEBUG_LAYOUT) {
      waveFormDisplay.setOpaque(true);
      waveFormDisplay.setBackground(Color.MAGENTA);
    }
    waveFormDisplay.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
    add(pane);
  }

  /**
   * @param firstBars The revised array of new integers to be drawn as the
   *                  waveform.
   */
  public void pokeAndDraw(int[] firstBars) {
    this.firstBars = firstBars;
    waveFormDisplay.repaint();
  }

  /**
   * Reset everything
   */
  public void pokeAndResetDrawing() {
    Arrays.fill(firstBars, 10);
    waveFormDisplay.repaint();
  }

}
