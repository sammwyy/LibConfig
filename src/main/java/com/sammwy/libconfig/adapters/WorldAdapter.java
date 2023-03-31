package com.sammwy.libconfig.adapters;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.sammwy.libconfig.ConfigurationSection;

public class WorldAdapter {
    public static World deserialize(String rawValue) {
        return Bukkit.getWorld(rawValue);
    }

    public static World deserialize(ConfigurationSection config, String key) {
        String worldName = config.getString(key);
        return deserialize(worldName);
    }

    public static void serialize(ConfigurationSection config, String key, World value) {
        config.set(key, value.getName());
    }

}
