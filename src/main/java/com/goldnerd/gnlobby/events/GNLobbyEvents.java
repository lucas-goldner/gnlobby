package com.goldnerd.gnlobby.events;

import com.goldnerd.gnlobby.GNLobby;
import com.goldnerd.gnlobby.entities.GNLobbyVillager;
import com.goldnerd.gnlobby.inventories.GNLobbyInvNav;
import com.goldnerd.gnlobby.items.GNLobbyItems;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class GNLobbyEvents implements Listener {
    private static boolean hidden;

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.YELLOW + "Willkommen auf GoldNerd.de " + player.getName()+ " !");
        player.getInventory().setItem(0, GNLobbyItems.nav);
        player.getInventory().setItem(1, GNLobbyItems.hider);
    }

    @EventHandler
    public static void onNavClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction()== Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getItem()!=null){
                if(event.getItem().getItemMeta().equals(GNLobbyItems.nav.getItemMeta())){
                    //player.sendMessage(ChatColor.YELLOW + "Du hast das Kompass Item ausgewählt");
                    GNLobbyInvNav navInv = new GNLobbyInvNav();
                    player.openInventory(navInv.getInventory());
                }
            }
        }
    }

    @EventHandler
    public static void onHiderClick(PlayerInteractEvent event){

        if(event.getAction()== Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Player player = event.getPlayer();
            if(event.getItem()!=null){
                if(event.getItem().getItemMeta().equals(GNLobbyItems.hider.getItemMeta())){
                    //player.sendMessage(ChatColor.YELLOW + "Du hast das Hider Item ausgewählt");
                    if(hidden){
                        for (Player toHide : Bukkit.getServer().getOnlinePlayers()) {
                            player.showPlayer(toHide);
                        }
                        hidden = false;
                        event.getPlayer().sendMessage(ChatColor.YELLOW + "Alle Spieler werden angezeigt");
                    } else {
                        for (Player toHide : Bukkit.getServer().getOnlinePlayers()) {
                            player.hidePlayer(toHide);
                        }
                        hidden = true;
                        event.getPlayer().sendMessage(ChatColor.YELLOW + "Alle Spieler werden versteckt");
                    }
                }
            }
        }
    }

    @EventHandler
    public static void onSonicClick(InventoryClickEvent event){
        if(event.getInventory()==null || event.getCurrentItem()==null){
            return;
        }
        if(event.getInventory().getHolder() instanceof GNLobbyInvNav) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if (event.isLeftClick()) {
                if (event.getCurrentItem() == null) {
                    return;
                }
                if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    // player.sendMessage(ChatColor.YELLOW + "Du hast das Sonic Item ausgewählt!");
                    Location loc = new Location(Bukkit.getWorld("world"), -25, 4, 14, 0, 0);
                    player.teleport(loc);
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public static void onHubClick(InventoryClickEvent event) {
        if (event.getInventory() == null || event.getCurrentItem() == null) {
            return;
        }
        if (event.getInventory().getHolder() instanceof GNLobbyInvNav) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if (event.isLeftClick()) {
                if (event.getCurrentItem() == null) {
                    return;
                }
                if (event.getCurrentItem().getType() == Material.GOLD_BLOCK) {
                    Location loc = Bukkit.getWorld("world").getSpawnLocation();
                    player.teleport(loc);
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void handleShopInteract(PlayerInteractEntityEvent event){
        if(!(event.getRightClicked() instanceof Villager && event.getRightClicked().getCustomName() != null)){
            return;
        } else {
            Villager shop = (Villager) event.getRightClicked();
            if(shop.getCustomName().equals(ChatColor.GOLD+"Hermano de Juanes")){
                event.setCancelled(true);
                Player player = event.getPlayer();
                player.sendMessage("Shop wird geöffnet.");
            }
        }
    }

    //Verhindern dass LobbyItems gemoved/gedropped werden
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack drop = event.getItemDrop().getItemStack();
        if (drop != null && (drop.getType() == Material.COMPASS||drop.getType() == Material.GOLD_INGOT)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void LockInv(InventoryClickEvent e) {
        ItemStack clicked = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        if(clicked != null && (clicked.getType() == Material.COMPASS||clicked.getType() == Material.GOLD_INGOT)) {
            e.setCancelled(true);
            return;
        }
    }
}
