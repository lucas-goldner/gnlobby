package com.goldnerd.gnlobby;

import com.goldnerd.gnlobby.commands.GNLobbyCommands;
import com.goldnerd.gnlobby.events.GNLobbyEvents;
import com.goldnerd.gnlobby.items.GNLobbyItems;
import com.goldnerd.gnlobby.services.FirebaseInitialize;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public class GNLobby extends JavaPlugin {
    public static Economy eco;


    @Override
    public void onEnable() {
        super.onEnable();
        GNLobbyItems.init();
        try {
            FirebaseInitialize.initalize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setUpEconomy();
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

    private boolean setUpEconomy(){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null){
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if(rsp==null){
            return false;
        }
        eco = rsp.getProvider();
        return eco!=null;
    }
}
