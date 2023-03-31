package com.sammwy.libconfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigurationManager {
    private Map<String, Object> cache;
    private File directory;
    private File jarFile;

    public ConfigurationManager(File directory, File jarFile) {
        this.cache = new HashMap<>();
        this.directory = directory;
        this.jarFile = jarFile;
    }

    public Configuration loadConfig(File configFile, String name) {
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();

            try (JarFile jar = new JarFile(this.jarFile)) {
                ZipEntry entry = jar.getEntry(name);
                InputStream is = jar.getInputStream(entry);
                Files.copy(is, configFile.toPath());
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Configuration config = new Configuration(configFile);

        try {
            config.load();
            return config;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Configuration getConfig(String name) {
        File configFile = new File(this.directory, name);
        String path = configFile.getAbsolutePath();

        if (this.cache.containsKey(path)) {
            this.cache.get(path);
        }

        Configuration config = this.loadConfig(configFile, name);
        this.cache.put(path, config);
        return config;
    }

    public static ConfigurationManager forBukkit(JavaPlugin plugin) {
        try {
            Method getFileMethod = JavaPlugin.class.getDeclaredMethod("getFile");
            getFileMethod.setAccessible(true);
            File jarFile = (File) getFileMethod.invoke(plugin);
            return new ConfigurationManager(plugin.getDataFolder(), jarFile);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }

    }
}
