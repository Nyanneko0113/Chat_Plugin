package org.nyanneko0113.chat_plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.nyanneko0113.chat_plugin.manager.GoogleManager;

import java.io.IOException;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IOException {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String format = "<" + player.getName() + ">:" + message;
        
        event.setCancelled(true);
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (!message.equalsIgnoreCase(GoogleManager.getText(message))) {
                players.sendMessage(format + "(" + GoogleManager.getText(message) + ")");
            }
            else {
                //変換と同じの場合はGoogleから取得しない
                players.sendMessage(format);
            }

        }
    }

}
