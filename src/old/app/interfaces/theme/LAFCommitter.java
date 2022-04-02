package app.interfaces.theme;

import app.interfaces.dialog.ErrorMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LAFCommitter {
  private static ArrayList<Frame> jfxs = new ArrayList<>();

  public LAFCommitter(Frame... jfxs) {
    LAFCommitter.jfxs.addAll(java.util.Arrays.asList(jfxs));
  }

  public void setMultTheme(app.interfaces.theme.Refresh theme) {
    try {
      for (Frame jfts : jfxs) {
        if (theme == null) {
          new ErrorMessage("Unable to process the specified Refresh theme.\nTrace Dump: "
              + Arrays.toString(jfxs.toArray()) + "\nArgument Type:" + jfts.getClass().getName());
          return;
        }
        theme.refresh(jfts);

      }
    } catch (Exception ex) {
      // do nothing
    }
  }

  public Color setBorderColor() {
    /**
     * PropertiesReader pr = new PropertiesReader();
     * String v = pr.getVal("gui.defaultTheme");
     * if(v.equals("material"))
     * return new Material().getBorderColor();
     * else if (v.equals("onedark"))
     * return new OneDark().getBorderColor();
     * else if (v.equals("regulardark"))
     * return new RegularDark().getBorderColor();
     * else if (v.equals("arcdark"))
     * return new ArcDark().getBorderColor();
     * else if (v.equals("dracula"))
     * return new Dracula().getBorderColor();
     * else if (v.equals("materiallighter"))
     * return new MaterialLighter().getBorderColor();
     * else if (v.equals("gruvbox"))
     * return new Gruvbox().getBorderColor();
     * else if (v.equals("vuesion"))
     * return new Vuesion().getBorderColor();
     * else if (v.equals("nord"))
     * return new Nord().getBorderColor();
     * else if (v.equals("regularlight"))
     * return new RegularLight().getBorderColor();
     * else if (v.equals("solarized"))
     * return new SolarizedLight().getBorderColor();
     */
    return new Color(71, 71, 71);
  }
}
