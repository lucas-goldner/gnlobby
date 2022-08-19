package com.goldnerd.gnlobby.entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GNLobbyVillager {

    public static final String VILLAGER_NAME = ChatColor.GOLD + "Hermano de Juanes";

    public GNLobbyVillager(Location location) {
        Villager shop = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        shop.setCustomName(VILLAGER_NAME);
        shop.setCustomNameVisible(true);
        shop.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 500));
    }
}
