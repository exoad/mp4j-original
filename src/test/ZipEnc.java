package test;

import app.secure.ZipProtocol;

public class ZipEnc {
  public static void main(String[] args) throws java.io.IOException {
    ZipProtocol zp = new app.secure.ZipProtocol();
    zp.zipResources();
  }
}
