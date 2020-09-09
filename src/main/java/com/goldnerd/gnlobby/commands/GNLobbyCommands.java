package com.goldnerd.gnlobby.commands;

import com.goldnerd.gnlobby.entities.GNLobbyVillager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GNLobbyCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!(sender instanceof Player)){

        } else {
            if(player.hasPermission("GNLobby.juanes")) {
                if(command.getName().equalsIgnoreCase("juanes")){
                    new GNLobbyVillager(player.getLocation());
                }
            } else {
                player.sendMessage(ChatColor.RED+ "Nur"+ ChatColor.GOLD + "Gold_Nerd "+ChatColor.RED+"darf den Juanes herbeirufen!");
            }
        }
        return false;
    }
}
