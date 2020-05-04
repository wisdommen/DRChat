package com.minecraft.plugins.deathrun.chat;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class Chat extends JavaPlugin {

    private static Chat plugin;

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("DR聊天插件已卸载！");
    }

    @Override
    public void onEnable() {
        plugin = this;
        File folder = new File(String.valueOf(getDataFolder()));
        File playerDataFolder = new File(getDataFolder() + "/playerData");
        if (!folder.exists()) {
            saveDefaultConfig();
        }
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }

        getServer().getPluginManager().registerEvents(new Listener(), this);
        Objects.requireNonNull(this.getCommand("cc")).setExecutor(new Commands());
        Objects.requireNonNull(this.getCommand("ccgiveprms")).setExecutor(new Commands());
        Objects.requireNonNull(this.getCommand("ccremoveprms")).setExecutor(new Commands());

        getServer().getConsoleSender().sendMessage("DR聊天插件已加载！");
    }

    public static Chat getInstance() {
        return plugin;
    }

}
