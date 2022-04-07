package project;

import project.components.BigContainer;
import project.components.ProcessesSchedule;
import project.test_components.SmallPanel;

public class Main {
  public static void main(String[] args) {
    ProcessesSchedule.main();
    new BigContainer(new SmallPanel()).run();
  }
}
