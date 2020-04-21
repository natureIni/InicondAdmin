package com.github.natureini.inicondadmin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Bukkit.broadcast("§7[CMD]" + event.getPlayer().getName() + "> " + event.getMessage(), "minecraft.command.op");
    }

}
