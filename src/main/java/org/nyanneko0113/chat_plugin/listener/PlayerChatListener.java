package org.nyanneko0113.chat_plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.nyanneko0113.chat_plugin.manager.GoogleManager;
import org.nyanneko0113.chat_plugin.manager.TeamChatManager;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IOException {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String normal_format = "<" + player.getName() + ">:" + message;

        for (TeamChatManager.Chat chat : TeamChatManager.allTeamChat()) {
            String team_format = "[" + chat.getChatName() + "]" + normal_format;
            Set<OfflinePlayer> chat_players = chat.getPlayers(true);

            if (chat_players.contains(player)) {
                for (OfflinePlayer players : chat_players) {
                    sendMessage(message, team_format, players);
                    event.setCancelled(true);
                }
            }

        }
    }

    private static void sendMessage(String message, String format, OfflinePlayer player) throws IOException {
        if (player != null) {
            if (player.isOnline()) {
                if (!message.equalsIgnoreCase(GoogleManager.getText(message))) {
                    player.getPlayer().sendMessage(format + "(" + GoogleManager.getText(message) + ")");
                }
                else {
                    //変換と同じの場合はGoogleから取得しない
                    player.getPlayer().sendMessage(format);
                }
            }
        }
    }
}
