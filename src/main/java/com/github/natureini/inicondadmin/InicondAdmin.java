package com.github.natureini.inicondadmin;

import com.github.natureini.inicondlib.api.InicondLibProfile;
import com.github.natureini.inicondlib.api.command.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class InicondAdmin extends JavaPlugin {

    private static InicondLibProfile profile;
    private static CommandHandler handler;

    @Override
    public void onEnable() {
        profile = new InicondLibProfile("InicondAdmin");
        handler = new CommandHandler(profile, new AccessoryCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return handler.handle(sender, command, args);
    }

    public static InicondLibProfile getProfile() {
        return profile;
    }

}
