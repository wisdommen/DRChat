package com.minecraft.plugins.deathrun.chat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Chatter {
    private final Player player;
    private final File player_file;
    private final YamlConfiguration player_config;

    public Chatter(Player player) {
        this.player = player;
        this.player_file = Utils.getPlayerFile(player);
        this.player_config = Utils.getPlayerConfig(player);
    }

    public Boolean hasPermission() {
        return player.hasPermission("cc.vip");
    }

    public Boolean isVip() {
        return Chat.getInstance().getConfig().getStringList("vip").contains(player.getName());
    }

    public ChatColor getChatColor() {
        return ChatColor.valueOf(player_config.getString("color"));
    }

    public void setChatColor(ChatColor chatColor) throws IOException {
        if (chatColor == ChatColor.RED) {
            player_config.set("color", "RED");
        } else if (chatColor == ChatColor.GRAY) {
            player_config.set("color", "GRAY");
        }
        player_config.save(player_file);
    }

    public void addPermission() {
        if (this.hasPermission()) {
            PermissionAttachment attachment = player.addAttachment(Chat.getInstance());
            attachment.setPermission("cc.vip", true);
        }
    }

    public void addPermission(String permission) {
        if (this.hasPermission()) {
            PermissionAttachment attachment = player.addAttachment(Chat.getInstance());
            attachment.setPermission(permission, true);
        }
    }

    public void removePermission() {
        PermissionAttachment attachment = player.addAttachment(Chat.getInstance());
        attachment.setPermission("cc.vip", false);
    }

    public void removePermission(String permission) {
        PermissionAttachment attachment = player.addAttachment(Chat.getInstance());
        attachment.setPermission(permission, false);
    }

    public void addVip() throws IOException {
        if (this.hasPermission()) {
            List<String> vips = Chat.getInstance().getConfig().getStringList("vip");
            vips.add(player.getName());
            Chat.getInstance().getConfig().set("vip", vips);
            Chat.getInstance().saveConfig();
        }
    }

    public void saveDefaultFile() throws IOException {
        player_config.set("color", "GRAY");
        player_config.save(player_file);
    }

}
