package app.functions;

import java.util.ArrayList;

public class Worker {
  private Thread worker;
  private ArrayList<Runnable> args = new ArrayList<>();

  public synchronized void threadedWorker(Runnable r) {
    worker = new Thread(r);
    worker.start();
  }

  public synchronized void killWorker() {
    worker.interrupt();
  }

  public synchronized void groupedWorker(Runnable... r) {
    for (Runnable r1 : r) {
      worker = new Thread(r1);
      worker.start();
      args.add(r1);
    }
  }

  public synchronized void killSpecWorker(int index) {
    ((Thread) args.get(index)).interrupt();
  }
}
