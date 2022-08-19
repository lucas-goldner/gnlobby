package com.goldnerd.gnlobby.models;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.bukkit.entity.Player;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import net.milkbowl.vault.economy.Economy;

public class User {
    String userUUID;
    String userName;
    Player player;
    ArrayList<String> boughItems;
    Economy economy;

    public User(Player player, ArrayList<String> boughItems, Economy economy) {
        this.player = player;
        this.userUUID = player.getUniqueId().toString();
        this.userName = player.getDisplayName();
        this.boughItems = boughItems;
        this.economy = economy;
    }

    public double getBalance() {
        return economy.getBalance(player);
    }

    public boolean hasItem(String item) {
        return boughItems.contains(item);
    }

    public boolean existsInDatabase() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(userUUID);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
