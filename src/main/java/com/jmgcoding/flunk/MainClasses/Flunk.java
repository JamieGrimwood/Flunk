package com.jmgcoding.flunk.MainClasses;

import com.jmgcoding.flunk.commands.*;
import com.jmgcoding.flunk.events.BanCheckandJoinQuit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.ConsoleCommandSender;
import java.util.logging.Logger;

public final class Flunk extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Logger logger = this.getLogger();

        loadCommands();
        loadEvents();
   /*     new UpdateChecker(this, 80918).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("The plugin is up to date!");
            } else {
                logger.info("There is a new update available! Get it here: https://www.spigotmc.org/resources/lirus.80918/");
            }
        });*/

        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("\n \n§8[]=====[§7Enabling Plugin§8]=====[]"
                + "\n§8| §cInformation:"
                + "\n§8|   §cName: §7HubEssentials"
                + "\n§8|   §cDeveloper: §7Jamie_"
                + "\n§8|   §cVersion: §7" + getDescription().getVersion()
                + "\n§8[]===========================[]§r\n ");

    }


    public void loadCommands(){
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("§cRegistering commands...");

        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("unban").setExecutor(new UnbanCommand(this));
        getCommand("gmc").setExecutor(new GamemodeCommand());
        getCommand("gms").setExecutor(new GamemodeCommand());
        getCommand("gma").setExecutor(new GamemodeCommand());
        getCommand("gmsp").setExecutor(new GamemodeCommand());
        getCommand("kick").setExecutor(new KickCommand());


        console.sendMessage("§aCommands Registered!");

    }
    public void loadEvents(){
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("§cRegistering events...");

        getServer().getPluginManager().registerEvents(new BanCheckandJoinQuit(this), this);

        console.sendMessage("§aEvents Registered!");
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage("\n \n§8[]=====[§7Disabling Plugin§8]=====[]"
                + "\n§8| §cInformation:"
                + "\n§8|   §cName: §7HubEssentials"
                + "\n§8|   §cDeveloper: §7Jamie_"
                + "\n§8|   §cVersion: §7" + getDescription().getVersion()
                + "\n§8[]===========================[]§r\n ");
    }
}
