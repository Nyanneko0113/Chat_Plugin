package org.nyanneko0113.chat_plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nyanneko0113.chat_plugin.ChatPlugin;
import org.nyanneko0113.chat_plugin.manager.TeamChatManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("team_create")) {
            TeamChatManager.addTeam(args[0], ((Player) send), new HashSet<>());
            send.sendMessage(ChatPlugin.TEXT_INFO + "チームを作成しました。");
        }
        else if (cmd.getName().equalsIgnoreCase("team_join")) {
            try {
                TeamChatManager.joinTeam(args[0], Bukkit.getOfflinePlayer(args[1]).getName());
                send.sendMessage( ChatPlugin.TEXT_INFO + "チームに追加しました。");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
