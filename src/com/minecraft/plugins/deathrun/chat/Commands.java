package com.minecraft.plugins.deathrun.chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Chatter chatter = new Chatter(player);
            switch (command.getName().toLowerCase()) {
                case "cc":
                    if (chatter.hasPermission() && strings.length == 1) {
                        if (strings[0].equalsIgnoreCase("red")) {
                            try {
                                chatter.setChatColor(ChatColor.RED);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (strings[0].equalsIgnoreCase("gray")) {
                            try {
                                chatter.setChatColor(ChatColor.GRAY);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return true;
                case "ccgiveprms":
                    if (player.isOp() && strings.length == 1) {
                        Chatter chatter2 = new Chatter(Chat.getInstance().getServer().getPlayer(strings[0]));
                        chatter2.addPermission();
                    }
                    return true;
                case "ccremoveprms":
                    if (player.isOp() && strings.length == 1) {
                        Chatter chatter2 = new Chatter(Chat.getInstance().getServer().getPlayer(strings[0]));
                        chatter2.removePermission();
                    }
                    return true;
            }
        }
        return false;
    }
}
