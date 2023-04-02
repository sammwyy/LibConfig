package com.sammwy.libconfig.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sammwy.libconfig.Configuration;
import com.sammwy.libconfig.errors.ConfigLoadException;

public class MiscTest {
    Configuration config = new Configuration("""
                keys:
                    foo: bar
                    hello: world
            """);

    @Before
    public void setup() throws ConfigLoadException {
        config.load();
    }

    @Test
    public void keysTest() {
        assertEquals(2, config.getConfigurationSection("keys").getKeys().size());
    }
}