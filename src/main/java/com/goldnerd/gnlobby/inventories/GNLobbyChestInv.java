package com.goldnerd.gnlobby.inventories;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GNLobbyChestInv implements InventoryHolder {
    private Inventory chestInv;

    public GNLobbyChestInv() {
        chestInv = Bukkit.getServer().createInventory(this, 54, ChatColor.GOLD + "Gekauftes Zeugs");
        init();
    }

    private void init() {
        chestInv.setItem(0, createItem(ChatColor.LIGHT_PURPLE + "Season 1 Hüte", Material.CREEPER_HEAD,
                Collections.singletonList(ChatColor.GRAY + "Hier klicken um wieder auszuziehen")));
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return chestInv;
    }
}
