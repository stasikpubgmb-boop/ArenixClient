package com.arenix.client.gui;

import com.arenix.ArenixClient;
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
                // Render module name with keybind
                String moduleName = module.getName();
                String keybind =-moduleManager.getModuleByName(moduleName).getCategory().getName();
                String text = moduleName + " (" + keybind + ")";

                // Render text with rainbow color
                int color = ColorHelper.Argb.getArgb(255, (int) (Math.sin(tickDelta * 0.1) * 128 + 128), (int) (Math.cos(tickDelta * 0.1) * 128 + 128), 255);
                matrices.push();
                matrices.translate(x, y, 0);
                client.textRenderer.drawWithShadow(matrices, text, 0, 0, color);
                matrices.pop();
                y += 10;
            }
        }
    }

    public void handleMouse(int mouseX, int mouseY, int button) {
        // Handle mouse click
        if (button == 0) {
            x = mouseX;
            y = mouseY;
        }
    }
}
