package com.sammwy.libconfig.adapters;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;

import com.sammwy.libconfig.ConfigurationSection;

public class MaterialAdapter {
    private static Map<String, Material> cache = new HashMap<>();

    public static Material deserialize(String materialName) {
        if (materialName == null || materialName.isEmpty()) {
            return null;
        }

        if (cache.containsKey(materialName)) {
            return cache.get(materialName);
        }

        for (Material material : Material.values()) {
            if (material.name().equalsIgnoreCase(materialName)) {
                cache.put(material.name().toLowerCase(), material);
                return material;
            }
        }

        cache.put(materialName.toLowerCase(), null);
        return null;
    }

    public static Material deserialize(ConfigurationSection config, String key) {
        String materialName = config.getString(key);
        return deserialize(materialName);

    }

    public static void serialize(ConfigurationSection config, String key, Material value) {
        config.set(key, value.name());
    }
}
