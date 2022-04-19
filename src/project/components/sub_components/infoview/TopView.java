package project.components.sub_components.infoview;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import project.audio.Overseer;
import project.audio.content.AudioInfoEditor;
import project.audio.content.AudioUtil;
import project.components.windows.ErrorWindow;
import project.constants.Size;

public class TopView extends JPanel {
  private JPanel mainPanel;
  private JLabel fileNameLabel;
  private Overseer seer;
  private transient AudioInfoEditor aie;

  public TopView() {
    fileNameLabel = new JLabel();
    mainPanel = new JPanel();
    setPreferredSize(new Dimension(Size.WIDTH- 530, Size.HEIGHT-300));
    setOpaque(true);
    setBackground(Color.BLACK);
    add(fileNameLabel);
  }

  public void setSeer(Overseer seer) {
    this.seer = seer;
  }

  public synchronized void setAie(AudioInfoEditor aie) {
    this.aie = aie;
    System.out.println(aie.toString());
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
