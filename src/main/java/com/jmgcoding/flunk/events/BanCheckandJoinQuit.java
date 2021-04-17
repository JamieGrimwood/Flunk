package com.jmgcoding.flunk.events;

import com.jmgcoding.flunk.MainClasses.Flunk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BanCheckandJoinQuit implements Listener {

    public BanCheckandJoinQuit(Flunk plugin) {
        instance = plugin;
    }
    private Flunk instance;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage(null);
        if (instance.getConfig().contains("BannedPlayers." + event.getPlayer().getUniqueId())) return;
        String quitMessage = instance.getConfig().getString("QuitMessage");
        quitMessage = quitMessage.replace("{player}", event.getPlayer().getName());
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', quitMessage));
    }

    @EventHandler
    public void onBPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        if (instance.getConfig().contains("BannedPlayers." + player.getUniqueId())) {
            String reason = instance.getConfig().getString("BannedPlayers." + player.getUniqueId() + ".Reason");
            player.kickPlayer("§cYou are banned from this server! Reason: " + reason);
        } else {
            String joinMessage = instance.getConfig().getString("JoinMessage");
            joinMessage = joinMessage.replace("{player}", event.getPlayer().getName());
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
        }
    }
}
