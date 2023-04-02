package com.sammwy.libconfig.adapters;

import org.bukkit.Location;
import org.bukkit.World;

import com.sammwy.libconfig.ConfigurationSection;

public class LocationAdapter {
    public static Location deserialize(ConfigurationSection config, String key, boolean withWorld) {
        if (!key.isEmpty()) {
            config = config.getConfigurationSection(key);
        }

        World world = withWorld ? WorldAdapter.deserialize(config, "world") : null;
        double x = config.getDouble("x");
        double y = config.getDouble("y");
        double z = config.getDouble("z");
        float yaw = config.getFloat("yaw");
        float pitch = config.getFloat("pitch");

        return new Location(world, x, y, z, yaw, pitch);
    }

    public static void serialize(ConfigurationSection config, String key, Location value) {
        World world = value.getWorld();
        if (world != null) {
            String worldName = world.getName();
            config.set(key + "world", worldName);
        }
        config.set(key + "x", value.getX());
        config.set(key + "y", value.getY());
        config.set(key + "z", value.getZ());
        config.set(key + "pitch", value.getPitch());
        config.set(key + "yaw", value.getYaw());
    }

}
