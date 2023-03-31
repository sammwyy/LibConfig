package com.sammwy.libconfig.adapters;

import org.bukkit.Location;
import org.bukkit.World;

import com.sammwy.libconfig.ConfigurationSection;

public class LocationAdapter {
    public static Location deserialize(ConfigurationSection config, String key) {
        if (!key.isEmpty()) {
            key += ".";
        }

        World world = WorldAdapter.deserialize(config, key);
        double x = config.getDouble(key + "x");
        double y = config.getDouble(key + "y");
        double z = config.getDouble(key + "z");
        float yaw = config.getFloat(key + "yaw");
        float pitch = config.getFloat(key + "pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static void serialize(ConfigurationSection config, String key, Location value) {
        if (!key.isEmpty()) {
            key += ".";
        }

        String worldName = value.getWorld().getName();
        config.set(key + "world", worldName);
        config.set(key + "x", value.getX());
        config.set(key + "y", value.getY());
        config.set(key + "z", value.getZ());
        config.set(key + "pitch", value.getPitch());
        config.set(key + "yaw", value.getYaw());
    }

}
