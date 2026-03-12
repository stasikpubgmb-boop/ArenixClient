package com.arenix.module;

import com.arenix.ArenixClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Session;

public abstract class Module {

    protected final MinecraftClient client = MinecraftClient.getInstance();
    private final String name;
    private final Category category;
    private boolean enabled;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    // Called when the module is enabled
    public void onEnable() {
        // Default implementation does nothing
    }

    // Called when the module is disabled
    public void onDisable() {
        // Default implementation does nothing
    }

    // Called on every client tick while the module is enabled
    public void onTick() {
        // Default implementation does nothing
    }

    // Called on render tick while the module is enabled (for visual modules)
    public void onRenderTick() {
        // Default implementation does nothing
    }

    // Placeholder for future methods like onKeyPress, onPacket, etc.
}
