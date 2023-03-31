package com.sammwy.libconfig.adapters;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Sound;

import com.sammwy.libconfig.ConfigurationSection;

public class SoundAdapter {
    private static Map<String, Sound> cache = new HashMap<>();

    public static Sound deserialize(String soundName) {
        if (soundName == null || soundName.isEmpty()) {
            return null;
        }

        if (cache.containsKey(soundName)) {
            return cache.get(soundName);
        }

        for (Sound sound : Sound.values()) {
            if (sound.name().equalsIgnoreCase(soundName)) {
                cache.put(sound.name().toLowerCase(), sound);
                return sound;
            }
        }

        cache.put(soundName.toLowerCase(), null);
        return null;
    }

    public static Sound deserialize(ConfigurationSection config, String key) {
        String soundName = config.getString(key);
        return deserialize(soundName);
    }

    public static void serialize(ConfigurationSection config, String key, Sound value) {
        config.set(key, value.name());
    }
}
