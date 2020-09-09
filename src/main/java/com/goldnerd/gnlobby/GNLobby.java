package com.goldnerd.gnlobby;

import com.goldnerd.gnlobby.commands.GNLobbyCommands;
import com.goldnerd.gnlobby.events.GNLobbyEvents;
import com.goldnerd.gnlobby.items.GNLobbyItems;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class GNLobby extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        GNLobbyItems.init();
        
        GNLobbyCommands commands = new GNLobbyCommands();
        getServer().getPluginManager().registerEvents(new GNLobbyEvents(), this);
        getCommand("juanes").setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[GNLobby]: Plugin started");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[GNLobby]: Plugin stopped");
    }
}
