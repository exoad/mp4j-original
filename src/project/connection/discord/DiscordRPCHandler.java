package project.connection.discord;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRPCHandler{
  public static void main(String[] args) {
    new Thread(() -> {
      DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
        System.out.println("Connected to Discord user: " + user.username + "#" + user.discriminator);
      }).build();

      DiscordRPC.discordInitialize(DiscordConst.APP_ID, handlers, true);

      DiscordRichPresence presence = new DiscordRichPresence.Builder("Listening to music").setBigImage("app-logo_512", "Exoad4J/MP4J").setStartTimestamps(System.currentTimeMillis()).setSmallImage("git-logo_512", "https://github.com/Exoad4JVM/mp4j").build();
      DiscordRPC.discordUpdatePresence(presence);
    }).start();

  }
}
