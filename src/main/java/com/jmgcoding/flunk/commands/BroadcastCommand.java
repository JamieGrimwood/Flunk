package com.jmgcoding.flunk.commands;

import com.jmgcoding.flunk.MainClasses.Flunk;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {
    private Flunk main;
    public BroadcastCommand(Flunk instance) {
        main = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("broadcast")) {
            if (sender.hasPermission("Flunk.broadcast")) {
                if (args.length > 0) {
                    StringBuilder message = new StringBuilder();
                    for (String arg : args) message.append(arg).append(" ");
                    String prefix = main.getConfig().getString("BroadcastPrefix");
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
                }

            }
            return true;
        }
        return true;
    }
}
