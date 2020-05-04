package com.minecraft.plugins.deathrun.chat;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Utils {

    public static File getPlayerFile(Player player) {
        return new File(Chat.getInstance().getDataFolder() + "/playerData", player.getName() + ".yml");
    }

    public static YamlConfiguration getPlayerConfig(Player player) {
        return YamlConfiguration.loadConfiguration(getPlayerFile(player));
    }

}
