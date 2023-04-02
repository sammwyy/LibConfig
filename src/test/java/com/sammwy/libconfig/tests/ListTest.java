package com.sammwy.libconfig.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.junit.Before;
import org.junit.Test;

import com.sammwy.libconfig.Configuration;
import com.sammwy.libconfig.errors.ConfigLoadException;

public class ListTest {
    Configuration config = new Configuration("""
                strings:
                    - Hello
                    - World
                    - Foo
                    - Bar
                sounds:
                    - ENTITY_PLAYER_LEVELUP
                    - ENTITY_PLAYER_LEVELUP
                materials:
                    - STICK
                    - STICK
                    - STICK
            """);

    @Before
    public void setup() throws ConfigLoadException {
        config.load();
    }

    @Test
    public void stringListTest() {
        List<String> list = config.getStringList("strings");
        assertEquals(4, list.size());
        assertEquals("Hello", list.get(0));
    }

    @Test
    public void soundListTest() {
        List<Sound> list = config.getSoundList("sounds");
        assertEquals(2, list.size());
        assertEquals(Sound.ENTITY_PLAYER_LEVELUP, list.get(0));
    }

    @Test
    public void materialListTest() {
        List<Material> list = config.getMaterialList("materials");
        assertEquals(3, list.size());
        assertEquals(Material.STICK, list.get(0));
    }
}