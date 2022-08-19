package com.goldnerd.gnlobby.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.goldnerd.gnlobby.models.NavItem;

public class GNLobbyNavItems {
    public static ItemStack nav, hider, chest;

    public static void init() {
        createNav();
        createHider();
        createChest();
    }

    private static void createHider() {
        List<String> lore = new ArrayList<>();
        lore.add("Ein äußerst spezieller");
        lore.add("Goldbatzen auf den man oft");
        lore.add("achten muss. Versteckt Spieler btw.");
        String itemMeta = ChatColor.LIGHT_PURPLE + "Hider";
        NavItem item = new NavItem(itemMeta, lore, Material.GOLD_INGOT);
        hider = item.getItemStack();
    }

    private static void createNav() {
        List<String> lore = new ArrayList<>();
        lore.add("Der alte verlorene Kompass");
        lore.add("von Hermano de Junaes.");
        lore.add("Führt dich zu geheimen Orten");
        String itemMeta = ChatColor.LIGHT_PURPLE + "Navigator";
        NavItem item = new NavItem(itemMeta, lore, Material.COMPASS);
        nav = item.getItemStack();
    }

    private static void createChest() {
        List<String> lore = new ArrayList<>();
        lore.add("Eine dubiose Kiste");
        lore.add("mit deinen erworbenen Items.");
        String itemMeta = ChatColor.LIGHT_PURPLE + "Inventar";
        NavItem item = new NavItem(itemMeta, lore, Material.CHEST);
        nav = item.getItemStack();
    }
}
