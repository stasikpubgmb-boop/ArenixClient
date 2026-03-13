package com.arenix.client.config;

import com.arenix.module.Module;
import com.arenix.module.ModuleManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConfigManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File configFile = new File("config.json");

    public void saveConfig(ModuleManager moduleManager) {
        JsonObject config = new JsonObject();
        List<Module> modules = moduleManager.getModules();
        for (Module module : modules) {
            config.addProperty(module.getName(), module.isEnabled());
        }
        try (FileWriter writer = new FileWriter(configFile)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(ModuleManager moduleManager) {
        if (configFile.exists()) {
            try (FileReader reader = new FileReader(configFile)) {
                JsonParser jsonParser = new JsonParser();
                JsonObject config = jsonParser.parse(reader).getAsJsonObject();
                List<Module> modules = moduleManager.getModules();
                for (Module module : modules) {
                    if (config.has(module.getName())) {
                        module.setEnabled(config.get(module.getName()).getAsBoolean());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
