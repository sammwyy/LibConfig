package com.sammwy.libconfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Configuration extends ConfigurationSection {
    private File file;
    private Yaml yaml;

    public Configuration(File file) {
        this.file = file;
        this.yaml = new Yaml();
    }

    // Utils
    public Map<String, Object> toMap() {
        return this.values;
    }

    public String toString() {
        return this.yaml.dump(this.values);
    }

    // Load/save
    public void load() throws IOException {
        String raw = Files.readString(this.file.toPath());
        Map<String, Object> values = this.yaml.load(raw);
        this.values = values;
    }

    public void save() throws IOException {
        String raw = this.toString();
        Files.writeString(this.file.toPath(), raw, StandardCharsets.UTF_8);
    }

    public void tryLoad() {
        try {
            this.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trySave() {
        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
