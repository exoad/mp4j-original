package project.connection.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRPCHandler {
  private DiscordRichPresence presence;
  public static final String NOTHING_MUSIC = "Nothing.mp3";
  private static final String STATE = "Listening to\n ";

  public synchronized void setCurrState(String m) {
    DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {
      System.out.println("Connected to Discord user: " + user.username + "#" + user.discriminator);
    }).build();
    DiscordRPC.discordInitialize(DiscordConst.APP_ID, handlers, true);

    presence = new DiscordRichPresence.Builder(STATE + m)
        .setBigImage("app-logo_512", "Exoad4J/MP4J").setStartTimestamps(System.currentTimeMillis())
        .setSmallImage("git-logo_512", "https://github.com/Exoad4JVM/mp4j")
        .build();
    DiscordRPC.discordUpdatePresence(presence);
  }

  public void start() {
    DiscordEventHandlers handlers = new DiscordEventHandlers.Builder()
        .setReadyEventHandler(
            user -> System.out.println("Connected to Discord user: " + user.username + "#" + user.discriminator))
        .build();
    DiscordRPC.discordInitialize(DiscordConst.APP_ID, handlers, true);

    presence = new DiscordRichPresence.Builder(STATE
        + NOTHING_MUSIC)
        .setBigImage("app-logo_512", "Exoad4J/MP4J").setStartTimestamps(System.currentTimeMillis())
        .setSmallImage("git-logo_512", "https://github.com/Exoad4JVM/mp4j")

        .build();
    DiscordRPC.discordUpdatePresence(presence);
  }
}
