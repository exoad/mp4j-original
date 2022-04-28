package project.components.sub_components.infoview;

import project.constants.ColorContent;
import project.constants.ProjectManager;
import project.constants.Size;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Arrays;

public class AppsView extends JPanel {
  private JPanel waveFormDisplay;
  private int[] firstBars;
  public int MAX_DRAW = 0;

  public AppsView(Dimension g) {
    if (ProjectManager.DEBUG_LAYOUT) {
      setOpaque(true);
      setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));
    }
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, true));
    setOpaque(true);
    MAX_DRAW = Size.WIDTH - 530 - 10;
    setPreferredSize(new Dimension(Size.WIDTH - 530 - 10, Size.HEIGHT - 600));
    firstBars = new int[Size.WIDTH - 530 - 10];
    Arrays.fill(firstBars, 1);
    waveFormDisplay = new JPanel() {
      @Override
      public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED));
        g2.setColor(ColorContent.WAVE_FORM_BAR_X);
        for (int i = 0, x = 0; i < firstBars.length && x < getWidth(); i++, x++) {
          if (i > 0 && firstBars[i] < firstBars[i - 1]) {
            g2.setColor(ColorContent.WAVE_FORM_LOWER_X);
          } else {
            g2.setColor(ColorContent.WAVE_FORM_BAR_X);
          }
          g2.drawLine(x, 50, x, 50 - firstBars[i]);
        }
        g2.dispose();
      }
    };
    waveFormDisplay.setPreferredSize(getPreferredSize());
    add(waveFormDisplay);
  }

  public void pokeAndDraw(int[] firstBars) {
    this.firstBars = firstBars;
    waveFormDisplay.repaint();
  }

  public void pokeAndResetDrawing() {
    Arrays.fill(firstBars, 1);
    waveFormDisplay.repaint();
  }

  
}
