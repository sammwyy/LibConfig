package com.sammwy.libconfig.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.junit.Before;
import org.junit.Test;

import com.sammwy.libconfig.Configuration;
import com.sammwy.libconfig.errors.ConfigLoadException;

public class BukkitTest {
    Configuration config = new Configuration("""
                location:
                    x: 100
                    y: 100
                    z: 100
                    pitch: 90
                    yaw: 15
                material: STICK
                sound: ENTITY_PLAYER_LEVELUP
            """);

    @Before
    public void setup() throws ConfigLoadException {
        config.load();
    }

    @Test
    public void locationTest() {
        assertTrue(new Location(null, 100, 100, 100, 15, 90).equals(config.getLocation("location", false)));
    }

    @Test
    public void materialTest() {
        assertEquals(Material.STICK, config.getMaterial("material"));
    }

    @Test
    public void nestedBooleanTest() {
        assertEquals(Sound.ENTITY_PLAYER_LEVELUP, config.getSound("sound"));
    }
}