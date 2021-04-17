package com.jmgcoding.flunk.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to user this command!");
            return false;
        }
        if (!sender.hasPermission("flunk.fly")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command!");
            return false;
        }
        Player player = (Player) sender;
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.RED + "disabled " + ChatColor.YELLOW + "fly!");
            return false;
        }
        player.setAllowFlight(true);


        player.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.GREEN + "enabled " + ChatColor.YELLOW + "fly!");

        return false;
    }

}
