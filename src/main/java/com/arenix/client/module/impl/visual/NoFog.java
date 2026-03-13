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
        GameOptions options = MinecraftClient.getInstance().options;
        options.fogFancy = false;
    }

    @Override
    public void onDisable() {
        GameOptions options = MinecraftClient.getInstance().options;
        options.fogFancy = true;
    }
}
