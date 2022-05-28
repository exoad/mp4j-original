package project.components.sub_components.infoview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import it.sauronsoftware.jave.AudioInfo;

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
import project.constants.ResourceDistributor;
import project.constants.Size;
import project.usables.DeImage;
import strict.RuntimeConstant;

import java.awt.Graphics;

public class TopView extends JPanel {
  private JPanel mainPanel;
  private JLabel artStyle;
  private JScrollPane infoBoxWrapper;
  private JEditorPane informationBox;
  private transient Overseer seer;
  private transient Thread spinWorker;
  private transient AudioInfoEditor aie = RuntimeConstant.DEFAULT_AIE;
  private double artStyleRotation = 0.0;
  private boolean spin = false;
  public AppsView av;

  public TopView() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(),
        Size.PREV_HEIGHT - 320));
    setMinimumSize(new Dimension((int) getPreferredSize().getWidth() - 10,
        Size.PREV_HEIGHT - 320));
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, false));
    mainPanel = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(DeImage.createGradientVertical(aie.getCoverArtBI(), 255, 0), 0, 0, null);

      }
    };
    mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    mainPanel.setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.PREV_HEIGHT - 200));
    informationBox = new JEditorPane();
    informationBox.setEditable(false);
    informationBox.setAutoscrolls(false);
    informationBox.setContentType("text/html");
    if (ProjectManager.DEBUG_LAYOUT)
      informationBox.setBackground(Color.RED);
    informationBox.setText(AudioInfoEditor.getBlank());
    informationBox.setFont(new Font("Arial", Font.PLAIN, 10));
    informationBox.setToolTipText(AudioInfoEditor.getBlank());
    infoBoxWrapper = new JScrollPane(informationBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    infoBoxWrapper.setBorder(BorderFactory.createEmptyBorder());
    infoBoxWrapper.setMaximumSize(new Dimension(240, 200));
    infoBoxWrapper.setPreferredSize(new Dimension(240, 200));
    infoBoxWrapper.setMinimumSize(new Dimension(240, 200));
    infoBoxWrapper.getViewport().setPreferredSize(new Dimension(240, 190));
    infoBoxWrapper.getViewport().setMaximumSize(new Dimension(500, 190));
    infoBoxWrapper.getViewport().setMinimumSize(new Dimension(240, 190));
    infoBoxWrapper.setBorder(BorderFactory.createEmptyBorder());
    informationBox.setPreferredSize(infoBoxWrapper.getPreferredSize());

    artStyle = new JLabel() {

      @Override
      public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage i = DeImage.imagetoBI(RuntimeConstant.runtimeRD.getDiskPNG().getImage());
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        AffineTransform at = new AffineTransform();
        at.rotate(artStyleRotation, i.getWidth() / 2.0d, i.getHeight() / 2.0d);
        g2d.transform(at);
        if (spin)
          artStyleRotation += Math.PI / 140;
        g2d.drawImage(i, at, null);
      }
    };
    artStyle.setIcon(RuntimeConstant.runtimeRD.getDiskPNG());
    av = new AppsView(new Dimension(getPreferredSize().width, getPreferredSize().height));
    if (ProjectManager.DEBUG_LAYOUT) {
      mainPanel.setBackground(Color.PINK);
    }
    mainPanel.add(infoBoxWrapper);
    mainPanel.add(artStyle);
    add(mainPanel, BorderLayout.NORTH);
    add(av, BorderLayout.SOUTH);
    spinWorker = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(60);
        } catch (

        InterruptedException e) {
          // IGNORE EXCEPTION
        }
        artStyle.repaint();
      }
    });
    spinWorker.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  public void stopSpinning() {
    spin = false;
  }

  /**
   * @return AudioInfoEditor
   */
  public AudioInfoEditor getAIE() {
    return aie;
  }

  public void startSpinning() {
    spin = true;
  }

  /**
   * @return JPanel
   */
  public JPanel getMainP() {
    return mainPanel;
  }

  /**
   * @param seer
   */
  public void setSeer(Overseer seer) {
    this.seer = seer;
  }

  /**
   * @param aie
   */
  public void setAie(AudioInfoEditor aie) {
    this.aie = aie;
    informationBox.setText(aie.toString());
    informationBox.revalidate();
    this.mainPanel.repaint();
    /**
     * BufferedImage ico = null;
     * try {
     * ico = ImageIO.read(aie.getUtilFile());
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * artStyle.setIcon(new ImageIcon(ico));
     */
    informationBox.setToolTipText(aie.noCheck());
    seer.pokeFile(aie.getUtilFile());
  }
}
