package project.components.sub_components.infoview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.StyleConstants.FontConstants;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import project.audio.Overseer;
import project.audio.content.AudioInfoEditor;
import project.audio.content.AudioUtil;
import project.components.windows.ErrorWindow;
import project.constants.Size;

public class TopView extends JPanel {
  private JPanel mainPanel;
  private JLabel artStyle;
  private JScrollPane infoBoxWrapper;
  private JEditorPane informationBox;
  private Overseer seer;
  private transient AudioInfoEditor aie;

  public TopView() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 300));
    setBorder(BorderFactory.createLineBorder(new Color(173, 173, 173), 1, false));
    
    informationBox = new JEditorPane();
    informationBox.setEditable(false);
    informationBox.setAutoscrolls(false);
    informationBox.setContentType("text/html");
    informationBox.setMaximumSize(new Dimension(200, 300));
    informationBox.setText(AudioInfoEditor.getBlank());
    informationBox.setFont(new Font("Arial", Font.PLAIN, 13));
    infoBoxWrapper = new JScrollPane(informationBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    infoBoxWrapper.setBorder(BorderFactory.createEmptyBorder());    
    infoBoxWrapper.setMaximumSize(new Dimension(300, 200));
    infoBoxWrapper.setPreferredSize(new Dimension(300, 200));
    infoBoxWrapper.setMinimumSize(new Dimension(300, 200));
    artStyle = new JLabel();
    artStyle.setIcon(new ImageIcon("resource/icons/others/disk.png"));
    add(artStyle);
    add(Box.createHorizontalStrut(100));
    add(infoBoxWrapper);
  }

  public void setSeer(Overseer seer) {
    this.seer = seer;

  }

  public synchronized void setAie(AudioInfoEditor aie) {
    this.aie = aie;
    informationBox.setText(aie.toString());
    informationBox.setPreferredSize(informationBox.getPreferredSize());
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
