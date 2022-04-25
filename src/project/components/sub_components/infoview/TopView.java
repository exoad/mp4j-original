package project.components.sub_components.infoview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.text.StyleConstants.FontConstants;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import project.audio.Overseer;
import project.audio.content.AudioInfoEditor;
import project.audio.content.AudioUtil;
import project.components.windows.ErrorWindow;
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
  private int[] bars = new int[3];

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
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(),
        Size.HEIGHT - 300));
    setBorder(BorderFactory.createLineBorder(new Color(173, 173, 173), 1, false));
    mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
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
    waveForm = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
      }
    };
    waveForm.setPreferredSize(new Dimension(210, 200));
    if(ProjectManager.DEBUG_LAYOUT) {
      waveForm.setOpaque(true);
      waveForm.setBackground(Color.GREEN);
    }
    mainPanel.add(waveForm);
    mainPanel.add(infoBoxWrapper);
    add(mainPanel, BorderLayout.NORTH);
    // add(sliderPanel, BorderLayout.SOUTH);
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
