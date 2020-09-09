package com.goldnerd.gnlobby.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GNLobbyItems {
    public static ItemStack nav, hider;

    public  static void init(){
        createNav();
        createHider();
    }

    private static void createHider() {
        ItemStack item2 = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.LIGHT_PURPLE+"Verstecke Spieler");
        List<String> lore2 = new ArrayList<>();
        lore2.add("Ein äußerst spezieller");
        lore2.add("Goldbatzen auf den man oft");
        lore2.add("achten muss. Versteckt Spieler btw.");
        meta2.setLore(lore2);
        item2.setItemMeta(meta2);
        hider = item2;
    }

    private static void createNav() {
        ItemStack item = new ItemStack(Material.COMPASS,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Navigator "+ChatColor.GRAY+"(Rechtsklick)");
        List<String> lore = new ArrayList<>();
        lore.add("Der alte verlorene Kompass");
        lore.add("von Hermano de Junaes.");
        lore.add("Führt dich zu geheimen Orten");
        meta.setLore(lore);
        item.setItemMeta(meta);
        nav = item;
    }
}
