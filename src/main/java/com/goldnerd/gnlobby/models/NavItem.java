package com.goldnerd.gnlobby.models;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NavItem {
    String metaDisplayName;
    List<String> lore;
    ItemStack itemStack;

    public NavItem(String metaDisplayName, List<String> lore, Material material) {
        this.metaDisplayName = metaDisplayName;
        this.lore = lore;
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(metaDisplayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        this.itemStack = item;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
