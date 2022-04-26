package project.components.sub_components.infoview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Polygon;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.StyleConstants.FontConstants;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import java.awt.RenderingHints;
import java.awt.Graphics2D;

import project.audio.Overseer;
import project.audio.content.AudioInfoEditor;
import project.audio.content.AudioUtil;
import project.components.windows.ErrorWindow;
import project.constants.ColorContent;
import project.constants.ProjectManager;
import project.constants.Size;

import java.awt.Graphics;

public class TopView extends JPanel {
  private JPanel mainPanel, sliderPanel, waveForm;
  private JLabel artStyle;
  private JScrollPane infoBoxWrapper;
  private JEditorPane informationBox;
  private JSlider volumeSlider;
  private JSlider[] unusedSliders;
  private transient Overseer seer;
  private transient AudioInfoEditor aie;
  public static final int MAX_BAR = 215;
  private int[] firstBars = new int[MAX_BAR];

  /**
   * Previous Impl:
   * mainPanel = new JPanel();
   * mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
   * setPreferredSize(new Dimension((int) getPreferredSize().getWidth(),
   * Size.HEIGHT - 300));
   * setBorder(BorderFactory.createLineBorder(new Color(173, 173, 173), 1,
   * false));
   * informationBox = new JEditorPane();
   * informationBox.setEditable(false);
   * informationBox.setAutoscrolls(false);
   * informationBox.setContentType("text/html");
   * informationBox.setMaximumSize(new Dimension(200, 300));
   * informationBox.setText(AudioInfoEditor.getBlank());
   * informationBox.setFont(new Font("Arial", Font.PLAIN, 13));
   * infoBoxWrapper = new JScrollPane(informationBox,
   * ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
   * ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   * infoBoxWrapper.setBorder(BorderFactory.createEmptyBorder());
   * infoBoxWrapper.setMaximumSize(new Dimension(300, 170));
   * infoBoxWrapper.setPreferredSize(new Dimension(300, 170));
   * infoBoxWrapper.setMinimumSize(new Dimension(300, 170));
   * artStyle = new JLabel();
   * artStyle.setIcon(new ImageIcon("resource/icons/others/disk.png"));
   * sliderPanel = new JPanel();
   * sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
   * sliderPanel.setPreferredSize(new Dimension(70, 200));
   * sliderPanel.setBorder(BorderFactory.createLineBorder(new Color(173, 173,
   * 173), 1, false));
   * volumeSlider = new JSlider(SwingConstants.VERTICAL, 0, 100, 50);
   * sliderPanel.add(volumeSlider);
   * add(artStyle);
   * add(Box.createHorizontalStrut(100));
   * add(infoBoxWrapper);
   * add(sliderPanel);
   */
  public TopView() {
    Arrays.fill(firstBars, 1);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(),
        Size.HEIGHT - 300));
    setBorder(BorderFactory.createLineBorder(new Color(173, 173, 173), 1, false));
    mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
    mainPanel.setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 200));
    informationBox = new JEditorPane();
    informationBox.setEditable(false);
    informationBox.setAutoscrolls(false);
    informationBox.setContentType("text/html");
    informationBox.setMaximumSize(new Dimension(180, 330));
    if (ProjectManager.DEBUG_LAYOUT)
      informationBox.setBackground(Color.RED);
    informationBox.setText(AudioInfoEditor.getBlank());
    informationBox.setFont(new Font("Arial", Font.PLAIN, 13));
    infoBoxWrapper = new JScrollPane(informationBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    infoBoxWrapper.setBorder(BorderFactory.createEmptyBorder());
    infoBoxWrapper.setMaximumSize(new Dimension(280, 200));
    infoBoxWrapper.setPreferredSize(new Dimension(280, 200));
    infoBoxWrapper.setMinimumSize(new Dimension(280, 200));
    artStyle = new JLabel();
    artStyle.setIcon(new ImageIcon("resource/icons/others/disk.png"));
    Dimension defaultWaveFormFault = new Dimension(210, 200);
    waveForm = new JPanel(true) {
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints rhSpeed = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2.setRenderingHints(rh);
        g2.addRenderingHints(rhSpeed);
        g2.setColor(ColorContent.WAVE_FORM_BAR_X);
        /**
         * Previous Impl:
         * for(int i = 0, x = 30; i < firstBars.length && x < 200; i++, x += 50) {
         * g.fillRect(x, (190 - firstBars[i] < 10 ? 10 : 190 - firstBars[i]), 40, firstBars[i] < 10 ?
         * 10 : firstBars[i]);
         * }
         */
        // draw vertical lines representing the waveform
        for (int i = 0, x = 0; i < firstBars.length && x < MAX_BAR; i++, x += 1) {
          if(i > 0 && firstBars[i] < firstBars[i - 1]) {
            g2.setColor(ColorContent.WAVE_FORM_LOWER_X);
          } else {
            g2.setColor(ColorContent.WAVE_FORM_BAR_X);
          }
          // draw a horizontal line
          g2.drawLine(100, x, 100 - firstBars[i], x);
        }

        g2.dispose();
      }
    };
    waveForm.setOpaque(isOpaque());

    waveForm.setPreferredSize(defaultWaveFormFault);
    if(ProjectManager.DEBUG_LAYOUT) {
      waveForm.setOpaque(true);
      waveForm.setBackground(Color.GREEN);
      mainPanel.setOpaque(true);
      mainPanel.setBackground(Color.PINK);
    }
    mainPanel.add(waveForm);
    mainPanel.add(infoBoxWrapper);
    add(mainPanel, BorderLayout.NORTH);
    // add(sliderPanel, BorderLayout.SOUTH);
  }

  public synchronized void pokeAndDraw(int[] firstBars) {
    this.firstBars = firstBars;
    waveForm.repaint();
  }

  public JPanel getMainP() {
    return mainPanel;
  }

  public void setSeer(Overseer seer) {
    this.seer = seer;
  }

  public void setAie(AudioInfoEditor aie) {
    this.aie = aie;
    informationBox.setText(aie.toString());
    /**
     * BufferedImage ico = null;
     * try {
     * ico = ImageIO.read(aie.getUtilFile());
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * artStyle.setIcon(new ImageIcon(ico));
     */
    informationBox.setPreferredSize(informationBox.getPreferredSize());
    seer.pokeFile(aie.getUtilFile());
    System.out.println(aie.getUtilFile().getAbsolutePath());
  }

  /**
   * @deprecated Use Overseer instead
   * @param f
   * @return
   */
  @Deprecated
  public static boolean check(File f) {
    if (f == null) {
      alert("Nothing was selected");
      return false;
    } else if (!((AudioUtil) f).isMP3()) {
      alert("This is not a mp3 file");
      return false;
    }
    return true;
  }

  /**
   * @deprecated Use Overseer instead
   * @param message
   */
  @Deprecated
  public static synchronized void alert(String message) {
    new ErrorWindow(message);
  }
}
