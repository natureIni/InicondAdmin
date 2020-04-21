package com.github.natureini.inicondadmin;

import com.github.natureini.inicondlib.api.command.CommandContainer;
import com.github.natureini.inicondlib.api.command.CommandOption;
import io.netty.handler.logging.LogLevel;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class AccessoryCommand implements CommandContainer {

    @CommandOption(permission = "time")
    public void morning(Player sender) {
        sender.getWorld().setTime(0);
    }

    @CommandOption(permission = "time")
    public void day(Player sender) {
        sender.getWorld().setTime(6000);
    }

    @CommandOption(permission = "time")
    public void night(Player sender) {
        sender.getWorld().setTime(18000);
    }

    @CommandOption(permission = "gamemode")
    public void gms(Player sender) {
        sender.setGameMode(GameMode.SURVIVAL);
    }

    @CommandOption(permission = "gamemode")
    public void gms(Player sender, String affectedName) {

        Player affected = getPlayerWeakly(affectedName);

        if (affected == null)
            InicondAdmin.getProfile().getMessage().sendMessage(sender, LogLevel.ERROR, "指定されたプレイヤーが見つかりません");

        else
            gms(affected);

    }

    @CommandOption(permission = "gamemode")
    public void gmc(Player sender) {
        sender.setGameMode(GameMode.CREATIVE);
    }

    @CommandOption(permission = "gamemode")
    public void gmc(Player sender, String affectedName) {

        Player affected = getPlayerWeakly(affectedName);

        if (affected == null)
            InicondAdmin.getProfile().getMessage().sendMessage(sender, LogLevel.ERROR, "指定されたプレイヤーが見つかりません");

        else
            gmc(affected);

    }

    @CommandOption(permission = "teleport")
    public void tp(Player sender, double x, double y, double z) {
        sender.teleport(new Location(sender.getWorld(), x, y, z));
    }

    @CommandOption(permission = "teleport")
    public void tp(Player sender, double x, double z) {
        tp(sender, x, 100.0, z);
    }

    @CommandOption(permission = "teleport")
    public void tp(Player sender, String targetName) {

        Player target = getPlayerWeakly(targetName);

        if (target == null)
            InicondAdmin.getProfile().getMessage().sendMessage(sender, LogLevel.ERROR, "テレポート先のプレイヤーが見つかりません");

        else
            sender.teleport(target);

    }

    @CommandOption(permission = "teleport")
    public void tp(Player sender, String affectedName, String targetName) {

        Player affected = getPlayerWeakly(affectedName);
        Player target = getPlayerWeakly(targetName);

        if (target == null || affected == null)
            InicondAdmin.getProfile().getMessage().sendMessage(sender, LogLevel.ERROR, "指定されたプレイヤーが見つかりません");

        else
            affected.teleport(target);

    }

    @CommandOption(permission = "teleport")
    public void tphere(Player sender, String affectedName) {

        if (affectedName.equals("@a"))
            Bukkit.getOnlinePlayers().forEach(p -> p.teleport(sender));

        else {

            Player affected = getPlayerWeakly(affectedName);

            if (affected == null)
                InicondAdmin.getProfile().getMessage().sendMessage(sender, LogLevel.ERROR, "指定されたプレイヤーが見つかりません");

            else
                affected.teleport(sender);

        }

    }

    private static Player getPlayerWeakly(String name) {

        Player target = Bukkit.getPlayerExact(name);

        if (target != null || (target = Bukkit.getPlayer(name)) != null)
            return target;

        char[] entered = name.toLowerCase().toCharArray();
        int equivalent = -1;

        for (Player p : Bukkit.getOnlinePlayers()) {

            char[] pName = p.getName().toLowerCase().toCharArray();

            for (int i = 0; i < Math.min(entered.length, pName.length); i++) {

                if (entered[i] == pName[i])
                    continue;

                if (equivalent < i) {

                    target = p;
                    equivalent = i;

                    break;

                }

            }

        }

        return target;

    }

}
