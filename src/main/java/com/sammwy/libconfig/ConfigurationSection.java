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
        return this.values.get(key);
    }

    public boolean getBoolean(String key) {
        if (this.values.containsKey(key)) {
            return (boolean) this.values.get(key);
        } else {
            return false;
        }
    }

    public double getDouble(String key) {
        if (this.values.containsKey(key)) {
            return (double) this.values.get(key);
        } else {
            return 0;
        }
    }

    public float getFloat(String key) {
        if (this.values.containsKey(key)) {
            return (float) this.values.get(key);
        } else {
            return 0;
        }
    }

    public int getInt(String key) {
        if (this.values.containsKey(key)) {
            return (int) this.values.get(key);
        } else {
            return 0;
        }
    }

    public Location getLocation(String key) {
        if (this.values.containsKey(key)) {
            return LocationAdapter.deserialize(this, key);
        } else {
            return null;
        }
    }

    public List<Location> getLocationList(String key) {
        List<ConfigurationSection> sections = this.getSectionList(key);
        List<Location> locations = new ArrayList<>();

        for (ConfigurationSection section : sections) {
            locations.add(LocationAdapter.deserialize(section, ""));
        }

        return locations;
    }

    public long getLong(String key) {
        if (this.values.containsKey(key)) {
            return (long) this.values.get(key);
        } else {
            return 0;
        }
    }

    public Material getMaterial(String key) {
        if (this.values.containsKey(key)) {
            return MaterialAdapter.deserialize(this, key);
        } else {
            return null;
        }
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
    public ConfigurationSection getSection(String key) {
        Map<String, Object> child = (Map<String, Object>) this.values.get(key);
        return new ConfigurationSection(child);
    }

    @SuppressWarnings("unchecked")
    public List<ConfigurationSection> getSectionList(String key) {
        List<Map<String, Object>> children = (List<Map<String, Object>>) this.values.get(key);
        List<ConfigurationSection> sections = new ArrayList<>();
        for (Map<String, Object> child : children) {
            sections.add(new ConfigurationSection(child));
        }
        return sections;
    }

    public Sound getSound(String key) {
        if (this.values.containsKey(key)) {
            return SoundAdapter.deserialize(this, key);
        } else {
            return null;
        }
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
        if (this.values.containsKey(key)) {
            return (String) this.values.get(key);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(String key) {
        if (this.values.containsKey(key)) {
            return (List<String>) this.values.get(key);
        } else {
            return null;
        }
    }

    public World getWorld(String key) {
        if (this.values.containsKey(key)) {
            return WorldAdapter.deserialize(this, key);
        } else {
            return null;
        }
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
