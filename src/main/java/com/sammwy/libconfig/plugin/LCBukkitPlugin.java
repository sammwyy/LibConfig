package com.sammwy.libconfig.plugin;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import com.sammwy.libconfig.Configuration;
import com.sammwy.libconfig.ConfigurationManager;

public class LCBukkitPlugin extends JavaPlugin {
    private ConfigurationManager configManager;
    private int testIndex = 0;

    public void ensure(Object value, Object toBe) throws Exception {
        if (!value.equals(toBe)) {
            throw new Exception("Error in test(#" + testIndex + "): " + value + " must be " + toBe);
        } else {
            System.out.println("Test(#" + testIndex + ") passed OK!");
        }

        testIndex++;
    }

    @Override
    public void onEnable() {
        this.configManager = ConfigurationManager.forBukkit(this);
        Configuration config = this.getMainConfig();

        try {
            System.out.println("=== Starting tests: Primitives ===");
            ensure(config.getString("type_string"), "Hello World");
            ensure(config.getInt("type_int"), 123);
            ensure(config.getFloat("type_float"), 1.23456f);
            ensure(config.getDouble("type_double"), 1234567.89D);
            ensure(config.getBoolean("type_boolean"), true);
            ensure(config.getLong("type_long"), 9999999999L);

            System.out.println("=== Starting tests: Bukkit ===");
            ensure(config.getLocation("type_location").getY(), 100);
            ensure(config.getMaterial("type_material"), Material.STICK);
            ensure(config.getSound("type_sound"), Sound.ENTITY_PLAYER_LEVELUP);
            ensure(config.getWorld("type_world").getName(), "world");

            System.out.println("=== Starting tests: Lists ===");
            ensure(config.getStringList("type_string_list").get(0), "Hello");
            ensure(config.getSoundList("type_sound_list").get(0), Sound.ENTITY_PLAYER_LEVELUP);
            ensure(config.getMaterialList("type_material_list").get(0), Material.STICK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Configuration getMainConfig() {
        return this.configManager.getConfig("config.yml");
    }
}