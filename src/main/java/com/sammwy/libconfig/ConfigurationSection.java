package com.sammwy.libconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;

import com.sammwy.libconfig.adapters.LocationAdapter;
import com.sammwy.libconfig.adapters.MaterialAdapter;
import com.sammwy.libconfig.adapters.SoundAdapter;
import com.sammwy.libconfig.adapters.WorldAdapter;

public class ConfigurationSection {
    protected Map<String, Object> values;

    public ConfigurationSection() {
    }

    public ConfigurationSection(Map<String, Object> values) {
        this.values = values;
    }

    // Getters
    public Object get(String key) {
        ConfigurationSection section = this;
        String[] paths = key.split("[.]");

        for (int i = 1; i < paths.length; i++) {
            section = section.getConfigurationSection(paths[i - 1]);
            key = paths[i];
        }

        return section == null ? null : section.values.get(key);
    }

    public boolean getBoolean(String key) {
        Object value = this.get(key);
        return value == null ? false : (boolean) value;
    }

    public double getDouble(String key) {
        Object value = this.get(key);
        return value == null ? 0 : (double) value;
    }

    public float getFloat(String key) {
        return (float) this.getDouble(key);
    }

    public int getInt(String key) {
        Object value = this.get(key);
        return value == null ? 0 : (int) value;
    }

    public Location getLocation(String key) {
        return LocationAdapter.deserialize(this, key);
    }

    public List<Location> getLocationList(String key) {
        List<ConfigurationSection> sections = this.getConfigurationSectionList(key);
        List<Location> locations = new ArrayList<>();

        for (ConfigurationSection section : sections) {
            locations.add(LocationAdapter.deserialize(section, ""));
        }

        return locations;
    }

    public long getLong(String key) {
        Object value = this.get(key);
        return value == null ? 0 : (long) value;
    }

    public Material getMaterial(String key) {
        return MaterialAdapter.deserialize(this, key);
    }

    public List<Material> getMaterialList(String key) {
        List<String> materialNames = this.getStringList(key);
        List<Material> list = new ArrayList<>();

        for (String materialName : materialNames) {
            list.add(MaterialAdapter.deserialize(materialName));
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    public ConfigurationSection getConfigurationSection(String key) {
        Map<String, Object> child = (Map<String, Object>) this.get(key);
        return new ConfigurationSection(child);
    }

    @SuppressWarnings("unchecked")
    public List<ConfigurationSection> getConfigurationSectionList(String key) {
        List<Map<String, Object>> children = (List<Map<String, Object>>) this.get(key);
        List<ConfigurationSection> sections = new ArrayList<>();
        for (Map<String, Object> child : children) {
            sections.add(new ConfigurationSection(child));
        }
        return sections;
    }

    public Sound getSound(String key) {
        return SoundAdapter.deserialize(this, key);
    }

    public List<Sound> getSoundList(String key) {
        List<String> soundNames = this.getStringList(key);
        List<Sound> list = new ArrayList<>();

        for (String soundName : soundNames) {
            list.add(SoundAdapter.deserialize(soundName));
        }

        return list;
    }

    public String getString(String key) {
        Object value = this.get(key);
        return value == null ? null : (String) value;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(String key) {
        Object value = this.get(key);
        return value == null ? null : (List<String>) value;
    }

    public World getWorld(String key) {
        return WorldAdapter.deserialize(this, key);
    }

    public List<World> getWorldList(String key) {
        List<String> worldNames = this.getStringList(key);
        List<World> list = new ArrayList<>();

        for (String worldName : worldNames) {
            list.add(WorldAdapter.deserialize(worldName));
        }

        return list;
    }

    // Setters
    public void set(String key, Object value) {
        this.values.put(key, value);
    }

    public void set(String key, Location location) {
        LocationAdapter.serialize(this, key, location);
    }

    public void set(String key, Material material) {
        MaterialAdapter.serialize(this, key, material);
    }

    public void set(String key, Sound sound) {
        SoundAdapter.serialize(this, key, sound);
    }

    public void set(String key, World world) {
        WorldAdapter.serialize(this, key, world);
    }

    // Utils
    public Map<String, Object> toMap() {
        return this.values;
    }
}
