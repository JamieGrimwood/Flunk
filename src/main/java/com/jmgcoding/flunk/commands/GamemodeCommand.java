package com.jmgcoding.flunk.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class GamemodeCommand implements CommandExecutor {
    private LinkedHashMap<String, GameMode> gameModes = new LinkedHashMap<>();

    public GamemodeCommand() {
        gameModes.put("gmc", GameMode.CREATIVE);
        gameModes.put("gms", GameMode.SURVIVAL);
        gameModes.put("gmsp", GameMode.SPECTATOR);
        gameModes.put("gma", GameMode.ADVENTURE);

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            String cmd = command.getName();
            if (!gameModes.containsKey(cmd)) return false;

            if (!sender.hasPermission("hubessentials." + cmd)) {
                sender.sendMessage("§cYou do not have permission to perform this command!");
                return true;
            }

            if (!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            player.setGameMode(gameModes.get(cmd));
            player.sendMessage("§eYour game mode has been updated to §c" + gameModes.get(cmd).name());
        } else if (args.length == 1) {
            String cmd = command.getName();
            if (!gameModes.containsKey(cmd)) return false;


            if (!sender.hasPermission("lirus." + cmd + ".others")) {
                sender.sendMessage("§cYou don't have required permission");
                return false;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage("§cPlayer not found");
                return false;
            }

            player.setGameMode(gameModes.get(cmd));
            sender.sendMessage("§6" + player.getName() + " game mode has been changed to §d" + gameModes.get(cmd).name());
            player.sendMessage("§6Your game mode has been changed to §d" + gameModes.get(cmd).name());
        }
        return false;
    }
}
