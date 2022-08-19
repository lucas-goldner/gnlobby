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

public class GNLobbyInvJuanes implements InventoryHolder {
    private Inventory shopInv;

    public GNLobbyInvJuanes() {
        shopInv = Bukkit.getServer().createInventory(this, 9, ChatColor.GOLD + "Hermano de Juanes Shop");
        init();
    }

    private void init() {
        shopInv.setItem(0, createItem("Test", Material.BLACK_WOOL, Collections.singletonList("TestKauf")));
    }

    @Override
    public Inventory getInventory() {
        return shopInv;
    }

    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
