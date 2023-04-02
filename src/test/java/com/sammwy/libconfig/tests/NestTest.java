package com.sammwy.libconfig.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sammwy.libconfig.Configuration;
import com.sammwy.libconfig.errors.ConfigLoadException;

public class NestTest {
    Configuration config = new Configuration("""
                nest:
                    string: Hello World
                    int: 123
                    boolean: true
            """);

    @Before
    public void setup() throws ConfigLoadException {
        config.load();
    }

    @Test
    public void nestedStringTest() {
        assertEquals("Hello World", config.getString("nest.string"));
    }

    @Test
    public void nestedIntTest() {
        assertEquals(123, config.getInt("nest.int"));
    }

    @Test
    public void nestedBooleanTest() {
        assertTrue(config.getBoolean("nest.boolean"));
    }
}