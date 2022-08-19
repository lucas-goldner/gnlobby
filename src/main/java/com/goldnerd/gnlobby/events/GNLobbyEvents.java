package com.goldnerd.gnlobby.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.goldnerd.gnlobby.GNLobby;
import com.goldnerd.gnlobby.constants.TPLocations;
import com.goldnerd.gnlobby.inventories.GNLobbyChestInv;
import com.goldnerd.gnlobby.inventories.GNLobbyInvJuanes;
import com.goldnerd.gnlobby.inventories.GNLobbyInvNav;
import com.goldnerd.gnlobby.items.GNLobbyNavItems;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

public class GNLobbyEvents implements Listener {
    private static boolean hidden;
    static Firestore db = FirestoreClient.getFirestore();
    static GNLobbyChestInv theChest = new GNLobbyChestInv();

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) throws ExecutionException, InterruptedException {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.YELLOW + "Willkommen auf GoldNerd.de " + player.getName() + " !");
        player.getInventory().setItem(0, GNLobbyNavItems.nav);
        player.getInventory().setItem(1, GNLobbyNavItems.hider);
        player.getInventory().setItem(2, GNLobbyNavItems.chest);

        // Nach Item suchen
        DocumentReference docRef = db.collection("users").document(player.getUniqueId().toString());
        ApiFuture<DocumentSnapshot> receive = docRef.get();
        DocumentSnapshot document = receive.get();
        if (document.exists()) {
            ArrayList<String> items = (ArrayList<String>) document.get("items");
            int i = 1;
            for (String item : items) {
                theChest.getInventory().setItem(i, new ItemStack(Material.valueOf(item)));
                i++;
            }
        } else {
            // hat kein Dokument
        }

    }

    @EventHandler
    public static void onNavClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(GNLobbyNavItems.nav.getItemMeta())) {
                    // player.sendMessage(ChatColor.YELLOW + "Du hast das Kompass Item ausgewählt");
                    GNLobbyInvNav navInv = new GNLobbyInvNav();
                    player.openInventory(navInv.getInventory());
                }
            }
        }
    }

    @EventHandler
    public static void onHiderClick(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(GNLobbyNavItems.hider.getItemMeta())) {
                    // player.sendMessage(ChatColor.YELLOW + "Du hast das Hider Item ausgewählt");
                    if (hidden) {
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
    public static void onChestClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(GNLobbyNavItems.chest.getItemMeta())) {
                    player.openInventory(theChest.getInventory());
                }
            }
        }
    }

    @EventHandler
    public static void onSonicClick(InventoryClickEvent event) {
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
                if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    // player.sendMessage(ChatColor.YELLOW + "Du hast das Sonic Item ausgewählt!");
                    Location loc = TPLocations.SonicSGPoint;
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
    public static void onChestInvClick(InventoryClickEvent event) {
        if (event.getInventory() == null || event.getCurrentItem() == null) {
            return;
        }
        if (event.getInventory().getHolder() instanceof GNLobbyChestInv) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if (event.isLeftClick()) {
                if (event.getCurrentItem() == null) {
                    return;
                }
                if (event.getCurrentItem().getType() == Material.BLACK_WOOL) {
                    player.sendMessage("Schwarze Wolle angeklickt");
                    player.getInventory().setHelmet(new ItemStack(Material.BLACK_WOOL));
                }
                if (event.getCurrentItem().getType() == Material.CREEPER_HEAD) {
                    player.sendMessage("Hut entfernt");
                    player.getInventory().setHelmet(new ItemStack(Material.AIR));
                }
            }
        }
    }

    @EventHandler
    public void handleShopInteract(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Villager && event.getRightClicked().getCustomName() != null)) {
            return;
        } else {
            Villager shop = (Villager) event.getRightClicked();
            if (shop.getCustomName().equals(ChatColor.GOLD + "Hermano de Juanes")) {
                event.setCancelled(true);
                Player player = event.getPlayer();
                player.sendMessage(
                        ChatColor.GOLD + "Hermano de Juanes: " + ChatColor.GRAY + "Buenos dias.¿Quieres comprar algo?");
                GNLobbyInvJuanes juanes = new GNLobbyInvJuanes();
                player.openInventory(juanes.getInventory());
            }
        }
    }

    // ShopItems
    @EventHandler
    public void BuyItem1(InventoryClickEvent event) throws ExecutionException, InterruptedException {
        if (event.getInventory() == null || event.getCurrentItem() == null) {
            return;
        }
        if (event.getInventory().getHolder() instanceof GNLobbyInvJuanes) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();

            if (event.isLeftClick()) {
                if (event.getCurrentItem() == null) {
                    return;
                }
                DocumentReference docRef = db.collection("users").document(player.getUniqueId().toString());
                ApiFuture<DocumentSnapshot> receive = docRef.get();
                DocumentSnapshot document = receive.get();

                if (event.getCurrentItem().getType() == Material.BLACK_WOOL) {
                    if (document.exists()) {
                        ArrayList<String> items = (ArrayList<String>) document.get("items");
                        if (items.contains("BLACK_WOOL")) {
                            // Hat einen Eintrag und es gekauft
                            player.sendMessage(ChatColor.GOLD + "Hermano de Juanes: " + ChatColor.GRAY
                                    + "Hahaha ya has comprado el item.¡Mira en tú inventario!");
                        } else {
                            // Hat Eintrag aber dieses Item nicht
                            HashMap postsMap = new HashMap();
                            postsMap.put("userName", ChatColor.stripColor(player.getDisplayName()));
                            postsMap.put("userUUID", player.getUniqueId().toString());
                            postsMap.put("items", Arrays.asList("BLACK_WOOL"));
                            postsMap.put("balance", GNLobby.eco.getBalance(player) - 100);
                            ApiFuture<WriteResult> write = db.collection("users")
                                    .document(player.getUniqueId().toString()).set(postsMap);
                            player.sendMessage("Du hast 100Yen gezahlt");
                            GNLobby.eco.withdrawPlayer(player, 100);
                            player.getInventory().setItem(2, new ItemStack(Material.BLACK_WOOL));
                        }
                    } else {
                        // Hat keinen Eintrag also kann es gar nicht haben
                        HashMap postsMap = new HashMap();
                        postsMap.put("userName", ChatColor.stripColor(player.getDisplayName()));
                        postsMap.put("userUUID", player.getUniqueId().toString());
                        postsMap.put("items", Arrays.asList("BLACK_WOOL"));
                        postsMap.put("balance", GNLobby.eco.getBalance(player) - 100);
                        ApiFuture<WriteResult> write = db.collection("users").document(player.getUniqueId().toString())
                                .set(postsMap);
                        player.sendMessage("Du hast 100Yen gezahlt");
                        GNLobby.eco.withdrawPlayer(player, 100);
                        player.getInventory().setItem(2, new ItemStack(Material.BLACK_WOOL));
                    }
                }
            }
        }
    }

    // Verhindern dass LobbyItems gemoved/gedropped werden
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack drop = event.getItemDrop().getItemStack();
        if (drop != null && (drop.getType() == Material.COMPASS || drop.getType() == Material.GOLD_INGOT
                || drop.getType() == Material.CHEST)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void LockInv(InventoryClickEvent e) {
        ItemStack clicked = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        if (clicked != null && (clicked.getType() == Material.COMPASS || clicked.getType() == Material.GOLD_INGOT
                || clicked.getType() == Material.CHEST || clicked.getType() == Material.BLACK_WOOL)) {
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public static void onChestPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.CHEST) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void ChestInvLock(InventoryClickEvent e) {
        ItemStack clicked = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        Inventory chest = e.getInventory();
        if (clicked != null && chest instanceof GNLobbyChestInv) {
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onChestInvDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack drop = event.getItemDrop().getItemStack();
        if (drop != null && (drop.getType() == Material.CREEPER_HEAD)) {
            event.setCancelled(true);
        }
    }
}
