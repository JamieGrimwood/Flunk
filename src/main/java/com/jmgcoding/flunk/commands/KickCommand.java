package com.jmgcoding.flunk.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class KickCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("kick")) {
            if (sender.hasPermission("Flunk.kick")) {
                if (args.length != 2) {
                    sender.sendMessage("§cUsage: /kick [player] [reason]");
                    return true;
                }

                Player kick = getServer().getPlayer(args[0]);
                if (kick == null) {
                    sender.sendMessage("§cThat player is not online!");
                    return true;
                }

                sender.sendMessage("§aThe player was successfully kicked!");
                kick.kickPlayer("You have been kicked from this server! Reason: " + args[1]);

            } else {
                sender.sendMessage("§4You do not have permission to perform this command!");
            }
        }
        return true;
    }
}
