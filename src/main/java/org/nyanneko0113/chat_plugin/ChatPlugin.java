package org.nyanneko0113.chat_plugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.nyanneko0113.chat_plugin.commands.TeamCommand;
import org.nyanneko0113.chat_plugin.listener.PlayerChatListener;
import org.nyanneko0113.chat_plugin.manager.TeamChatManager;

public final class ChatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    public void registerCommands() {
        getCommand("team_create").setExecutor(new TeamCommand());
        getCommand("team_join").setExecutor(new TeamCommand());
    }

    public void registerListeners() {
        PluginManager plm = getServer().getPluginManager();

        plm.registerEvents(new PlayerChatListener(), this);
    }

    public static ChatPlugin getInstance() {
        return ChatPlugin.getPlugin(ChatPlugin.class);
    }
}
