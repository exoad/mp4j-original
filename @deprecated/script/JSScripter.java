package backend.script;

import backend.Tool;
import app.CLI;
import app.global.cli.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.*;

public class JSScripter implements Tool {

  public JSScripter() {
    // for silent calls to subclasses
  }

  // suppress Warning: Nashorn engine is planned to be removed from a future JDK
  // release
  @SuppressWarnings("deprecation")
  private void engine(int type) {
    if(type == 0) {
      ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
      try {
        
        se.eval(new FileReader("src/js/Test.js"));
      } catch (ScriptException | FileNotFoundException e) {
        CLI.print(e.getMessage(), CliType.WARNING);
      }

    }
  }

  public void runScript(ScriptType st) {
    if(st == ScriptType.TEST) {
      CLI.print("Running test script...", CliType.WARNING);
      engine(0);
    }
  }

  @Override
  public void callCell(String[] args) {
    
  }
  
}
