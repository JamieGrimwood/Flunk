package com.jmgcoding.flunk.commands;

import com.jmgcoding.flunk.MainClasses.Flunk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BanCommand implements CommandExecutor {
    private Flunk main;
    public BanCommand(Flunk instance) {
        main = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("Flunk.ban")) {
            if (args.length >= 2) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) return true;

                String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length - 1));
                main.getConfig().set("BannedPlayers." + player.getUniqueId() + ".Reason", Arrays.stream(args).skip(1 /* number */).collect(Collectors.joining(" ")));
                main.saveConfig();
                player.kickPlayer("§cYou have been banned from this server! Reason: " + Arrays.stream(args).skip(1 /* number */).collect(Collectors.joining(" ")));
            } else {
                sender.sendMessage("§cUsage: /ban [player] [reason]");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
        }
        return true;
    }
}
