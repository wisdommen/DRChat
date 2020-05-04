package com.minecraft.plugins.deathrun.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;

import java.io.IOException;
import java.io.File;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) throws IOException {
        Player player = event.getPlayer();
        Chatter chatter = new Chatter(player);
        File file = Utils.getPlayerFile(player);
        if (!file.exists()) {
            chatter.saveDefaultFile();
        }
        if (chatter.hasPermission()) {
            event.setMessage(chatter.getChatColor() + event.getMessage());
        } else {
            event.setMessage(ChatColor.valueOf("GRAY") + event.getMessage());
        }
    }
}
