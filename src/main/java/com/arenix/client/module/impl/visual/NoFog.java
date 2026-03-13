package com.arenix.client.module.impl.visual;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class NoFog extends Module {

    public NoFog() {
        super("NoFog", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftClient client = MinecraftClient.getInstance();
        GameOptions options = client.options;
        options.fogFancy = false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftClient client = MinecraftClient.getInstance();
        GameOptions options = client.options;
        options.fogFancy = true;
    }
}
