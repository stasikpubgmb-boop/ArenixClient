package com.arenix.client.module;

import com.arenix.client.config.ConfigManager;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();
    private final ConfigManager configManager;

    public ModuleManager() {
        configManager = new ConfigManager();
        registerModules();
    }

    private void registerModules() {
        modules.add(new KillAura());
        modules.add(new AutoClicker());
        modules.add(new Reach());
        modules.add(new Criticals());
        modules.add(new Scaffold());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new BunnyHop());
        modules.add(new NoFall());
        modules.add(new Fly());
        modules.add(new ESP());
        modules.add(new Fullbright());
        modules.add(new NoFog());
    }

    public List<Module> getModules() {
        return modules;
    }

    public void saveConfig() {
        configManager.saveConfig(this);
    }

    public void loadConfig() {
        configManager.loadConfig(this);
    }
}
