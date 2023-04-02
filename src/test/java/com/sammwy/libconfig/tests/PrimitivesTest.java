package com.sammwy.libconfig.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sammwy.libconfig.Configuration;
import com.sammwy.libconfig.errors.ConfigLoadException;

public class PrimitivesTest {
    Configuration config = new Configuration("""
                string: \"Hello World\"
                int: 123
                float: 1.0
                double: 1.0
                boolean: true
                long: 9999999999
            """);

    @Before
    public void setup() throws ConfigLoadException {
        config.load();
    }

    @Test
    public void stringTest() {
        assertEquals("Hello World", config.getString("string"));
    }

    @Test
    public void intTest() {
        assertEquals(123, config.getInt("int"));
    }

    @Test
    public void floatTest() {
        assertEquals(1.00f, config.getFloat("float"), 0.0002);
    }

    @Test
    public void doubleTest() {
        assertEquals(1.00D, config.getDouble("double"), 0.0002);
    }

    @Test
    public void booleanTest() {
        assertTrue(config.getBoolean("boolean"));
    }

    @Test
    public void longTest() {
        assertEquals(9999999999L, config.getLong("long"));
    }

}