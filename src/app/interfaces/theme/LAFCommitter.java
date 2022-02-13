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

  public void setMultTheme(Refresh theme) {
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
}
