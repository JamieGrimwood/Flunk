package com.jmgcoding.flunk.commands;

import com.jmgcoding.flunk.MainClasses.Flunk;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;
import java.util.UUID;

public class UnbanCommand implements CommandExecutor {
    private final Flunk main;
    public UnbanCommand(Flunk instance) {
        main = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("Flunk.unban")) {
            if (args.length >= 1) {
                for(String str_uuid: main.getConfig().getConfigurationSection("BannedPlayers").getKeys(false)) {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(str_uuid));
                    if (Objects.equals(player.getName(), args[0])) {
                        main.getConfig().set("BannedPlayers." + player.getUniqueId(), null);
                        main.saveConfig();
                        sender.sendMessage("§aThe player has been successfully unbanned.");
                        return true;
                    }
                }
                sender.sendMessage("§cThat player is not banned!");
            } else {
                sender.sendMessage("§cUsage: /unban [player]");
            }
        } else {
            sender.sendMessage("§cYou don't have the required permission");
        }
        return true;
    }
}

