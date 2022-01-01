package main.advisors;

import java.io.IOException;
import java.net.URL;

public class CXX {
  public CXX() {

  }
  
  /** 
   * @return String
   * @throws IOException
   */
  public String callAPI() throws IOException {
    if(System.getProperty("os.name").contains("Windows")) {
      URL windowsAPI = getClass().getResource("/apiwrapper.exe");
      assert windowsAPI != null;
      return Host.runProcess(Runtime.getRuntime(), windowsAPI.getPath());
    }
    return null;
  }

  
  /** 
   * @return String
   * @throws IOException
   */
  public String veriyFile() throws IOException {
    if(System.getProperty("os.name").contains("Windows")) {
      URL windowsAPI = getClass().getResource("/fileint.exe");
      assert windowsAPI != null;
      return Host.runProcess(Runtime.getRuntime(), windowsAPI.getPath());
    }
    return null;
  }

  
  /** 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    new CXX().callAPI();
  }
  
}
