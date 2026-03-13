package com.arenix.module;

import com.arenix.ArenixClient;
import com.arenix.client.event.EventBus;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public abstract class Module {

    protected final MinecraftClient client = MinecraftClient.getInstance();
    protected final EventBus eventBus = ArenixClient.INSTANCE.getEventBus();
    private final String name;
    private final Category category;
    private final KeyBinding keyBinding;
    private boolean enabled;
    private List<Setting> settings;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.keyBinding = new KeyBinding("key." + name, InputUtil.Type.MOUSE, 2, "category." + category.getName());
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
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onTick() {
    }

    public void onRenderTick() {
    }
}
