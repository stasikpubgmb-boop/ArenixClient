package com.arenix.client.module.impl.visual;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class Fullbright extends Module {

    public Fullbright() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftClient client = MinecraftClient.getInstance();
        GameOptions options = client.options;
        options.gamma = 16f;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftClient client = MinecraftClient.getInstance();
        GameOptions options = client.options;
        options.gamma = 1f;
    }
}
