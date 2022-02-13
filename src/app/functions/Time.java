package app.functions;

import java.util.concurrent.TimeUnit;

public class Time {
  private Time() {}
  public static String msToHHMMSS(long microseconds) {
    long hours = TimeUnit.MICROSECONDS.toHours(microseconds);
    long minutes = TimeUnit.MICROSECONDS.toMinutes(microseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MICROSECONDS.toHours(microseconds));
    long seconds = TimeUnit.MICROSECONDS.toSeconds(microseconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MICROSECONDS.toMinutes(microseconds));
    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }

  public static void main(String[] args) {
    System.out.println(msToHHMMSS(1000000));
  }
}
