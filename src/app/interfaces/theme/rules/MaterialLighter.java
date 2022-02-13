package app.interfaces.theme.rules;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import java.awt.Color;

import javax.swing.*;

public class MaterialLighter implements Refresh {

  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (NullPointerException e) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
  }

  public String toString() {
    return "MaterialLighter";
  }

  @Override
  public Color getBorderColor() {
    return new Color(10, 189, 201);
  }
}
