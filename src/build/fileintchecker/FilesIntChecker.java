package build.fileintchecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import build.fileintchecker.global.Exclude;


public class FilesIntChecker {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new File("src/build/fileintchecker/.loader"));
    String loadedPath = sc.nextLine();
    System.out.println("> " + loadedPath);
    sc.close();
    
    File mainPath = new File(loadedPath);
    File[] files = mainPath.listFiles();
    StringBuilder filesSeq = new StringBuilder();
    assert files != null;
    for(var f : files) {
      if(f.getPath().contains("deprecated") || f.isDirectory() || filesSeq.toString().contains(f.getName())) {
        continue;
      }
      filesSeq.append(f.getName()).append("\n");
      System.out.println(filesSeq.toString());
    }
    for(String secondPaths : Exclude.ndPaths) {
      File secondPath = new File(loadedPath + secondPaths);
      System.out.println(secondPath.getName());
      if(secondPath.isDirectory()) {
        File[] secondFiles = secondPath.listFiles();
        assert secondFiles != null;
        for(var f : secondFiles) {
          if(f.isDirectory() || filesSeq.toString().contains(f.getName())) {
            continue;
          }
          filesSeq.append(secondPaths).append(f.getName()).append("\n");
          System.out.println(secondPaths + filesSeq.toString());
        }
      }
    }
    filesSeq.deleteCharAt(filesSeq.length() - 1);
    File f = new File(loadedPath + "/files.txt");
    f.createNewFile();
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
      bw.write(filesSeq.toString());
      bw.flush();
      bw.close();
    }
  }
}
