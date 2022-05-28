package project.usables;

public class TimeTool {

  /**
   * NOTE:
   * This method should work and return the correct time, else idk.
   * @param milliSeconds
   * @return
   */
  public static String getTimeFromMS(long milliSeconds) {
    long seconds = milliSeconds / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;
    seconds = seconds % 60;
    minutes = minutes % 60;
    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }

  public static String getTime(long systemMillis) {
    return String.format("%02d:%02d:%02d", systemMillis / 3600000, systemMillis / 60000, systemMillis / 1000);
  }
}