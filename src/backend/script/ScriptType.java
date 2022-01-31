package backend.script;

public enum ScriptType {
  TEST("Test.js");

  private String file;
  private ScriptType(String file) {
    this.file = file;
  }

  public String getFile() {
    return file;
  }
}
