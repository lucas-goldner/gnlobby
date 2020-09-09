package com.goldnerd.gnlobby.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public class GNLobbyInvNav implements InventoryHolder {
    private Inventory navInv;
    private ItemStack sonicHead = getSonicHead();
    private ItemStack hubItem = getHubItem();


    public GNLobbyInvNav(){
        navInv = Bukkit.createInventory(this, 27, "Navigationsmenü");
        init();
    }

    private void init(){
        navInv.setItem(11, sonicHead);
        navInv.setItem(13, hubItem);
    }

    private ItemStack createItem(String name, Material mat, List<String> lore){
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return navInv;
    }

    private ItemStack getSonicHead(){
        Material type = Material.PLAYER_HEAD;
        ItemStack item = new ItemStack(type, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner("Sonic");
        meta.setDisplayName(ChatColor.BLUE+"Sonic SG");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Führt dich zum unheimlich");
        lore.add(ChatColor.LIGHT_PURPLE+"beliebten super schnellen");
        lore.add(ChatColor.LIGHT_PURPLE+"Sonic Survival Games Modus!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack getHubItem() {
        Material type = Material.GOLD_BLOCK;
        ItemStack item = new ItemStack(type, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Zurück zum Spawn");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Verlaufen? Dann");
        lore.add(ChatColor.LIGHT_PURPLE+"klicke einfach hier");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
