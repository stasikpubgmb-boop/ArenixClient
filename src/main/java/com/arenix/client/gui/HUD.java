package com.arenix.client.gui;

import com.arenix.ArenixClient;
import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ColorHelper;

import java.awt.Color;

public class HUD {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final ModuleManager moduleManager = ArenixClient.INSTANCE.getModuleManager();
    private int x = 10;
    private int y = 10;

    public void render(MatrixStack matrices, float tickDelta) {
        for (Module module : moduleManager.getModules()) {
            if (module.isEnabled()) {
                drawModule(matrices, module, x, y);
                y += 10;
            }
        }
    }

    private void drawModule(MatrixStack matrices, Module module, int x, int y) {
        fill(matrices, x, y, x + 100, y + 10, new Color(50, 50, 50, 255).getRGB());

        drawTextWithShadow(matrices, client.textRenderer, module.getName(), x + 10, y + 5, new Color(255, 255, 255, 255).getRGB());
    }
}
