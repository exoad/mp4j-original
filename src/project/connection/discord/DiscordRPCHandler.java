package project.connection.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRPCHandler {
  private DiscordRichPresence presence;
  public static final String NOTHING_MUSIC = "Nothing.mp3";
  private static final String STATE = "Listening to\n ";
  private static final String BIG_IMG_DETAIL = "Halcyon!";
  private static final String SMALL_IMG_DETAIL = NOTHING_MUSIC;

  public synchronized void setCurrState(String m) {
    DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {
      System.out.println("Connected to Discord user: " + user.username + "#" + user.discriminator);
    }).build();
    DiscordRPC.discordInitialize(DiscordConst.APP_ID, handlers, true);

    presence = new DiscordRichPresence.Builder(STATE + m)
        .setBigImage("app-logo_512", BIG_IMG_DETAIL).setStartTimestamps(System.currentTimeMillis())
        .setSmallImage("play_button_512", SMALL_IMG_DETAIL)
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
        .setBigImage("app-logo_512", BIG_IMG_DETAIL).setStartTimestamps(System.currentTimeMillis())
        .setSmallImage("play_button_512", SMALL_IMG_DETAIL)

        .build();
    DiscordRPC.discordUpdatePresence(presence);
  }
}
