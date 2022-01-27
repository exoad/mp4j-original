package backend;

import backend.Tool;

public class ToolInterface {
  private Tool clazz;
  private String[] args;
  public ToolInterface(String[] args, Tool tool) {
    this.args = args;
    this.clazz = tool;
  }

  public ToolInterface() {
    // for silent calls to subclasses
  }

  public void call() {
    clazz.callCell(args);
  }
}
