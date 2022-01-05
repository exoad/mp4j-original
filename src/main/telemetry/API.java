package main.telemetry;

import java.io.IOException;

public interface API {
  public abstract String run() throws IOException;
  public abstract java.net.URL returnLink() throws IOException;
}