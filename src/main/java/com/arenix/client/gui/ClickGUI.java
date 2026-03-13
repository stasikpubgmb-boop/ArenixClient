package com.arenix.client.gui;

import com.arenix.ArenixClient;
import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.ColorHelper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final ModuleManager moduleManager = ArenixClient.INSTANCE.getModuleManager();
    private final List<Module> modules = new ArrayList<>();
    private int x = 10;
    private int y = 10;
    private int hoveredModule = -1;

    public ClickGUI() {
        super(new Text("ClickGUI"));
        for (Module module : moduleManager.getModules()) {
            modules.add(module);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Draw background
        fill(matrices, 0, 0, width, height, ColorHelper.Argb.getArgb(100, 0, 0, 0));

        // Draw category panels
        int panelX = 10;
        int panelY = 10;
        for (Category category : Category.values()) {
            // Draw panel background
            fill(matrices, panelX, panelY, panelX + 200, panelY + 20, ColorHelper.Argb.getArgb(255, 50, 50, 50));

            // Draw panel header
            drawHeader(matrices, category, panelX, panelY);

            // Draw modules in panel
            drawModules(matrices, category, panelX, panelY + 20);

            panelX += 210;
        }

        // Draw search bar
        drawSearchBar(matrices, mouseX, mouseY);
    }

    private void drawHeader(MatrixStack matrices, Category category, int x, int y) {
        // Draw header background
        fill(matrices, x, y, x + 200, y + 20, ColorHelper.Argb.getArgb(255, 100, 100, 100));

        // Draw category name
        drawTextWithShadow(matrices, client.textRenderer, category.getName(), x + 10, y + 5, ColorHelper.Argb.getArgb(255, 255, 255, 255));
    }

    private void drawModules(MatrixStack matrices, Category category, int x, int y) {
        int moduleY = y;
        for (Module module : moduleManager.getModulesByCategory(category)) {
            // Draw module background
            int moduleX = x;
            int moduleHeight = 20;
            fill(matrices, moduleX, moduleY, moduleX + 200, moduleY + moduleHeight, module.isEnabled() ? ColorHelper.Argb.getArgb(255, 0, 255, 0) : ColorHelper.Argb.getArgb(255, 50, 50, 50));

            // Draw module name
            drawTextWithShadow(matrices, client.textRenderer, module.getName(), moduleX + 10, moduleY + 5, ColorHelper.Argb.getArgb(255, 255, 255, 255));

            // Handle mouse hover
            if (isMouseOver(moduleX, moduleY, 200, moduleHeight)) {
                hoveredModule = moduleManager.getModules().indexOf(module);
                // Draw hover effect
                fill(matrices, moduleX, moduleY, moduleX + 200, moduleY + moduleHeight, ColorHelper.Argb.getArgb(255, 100, 100, 100));
            }

            moduleY += 20;
        }
    }

    private void drawSearchBar(MatrixStack matrices, int mouseX, int mouseY) {
        // Draw search bar background
        fill(matrices, 10, 10, 200, 30, ColorHelper.Argb.getArgb(255, 50, 50, 50));

        // Draw search bar text
        drawTextWithShadow(matrices, client.textRenderer, "Search", 15, 15, ColorHelper.Argb.getArgb(255, 255, 255, 255));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            // Handle module click
            if (hoveredModule != -1) {
                Module module = modules.get(hoveredModule);
                module.setEnabled(!module.isEnabled());
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOver(int x, int y, int width, int height) {
        return x < client.mouse.getX() && client.mouse.getX() < x + width && y < client.mouse.getY() && client.mouse.getY() < y + height;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 340) {
            // Handle right shift press
            client.setScreen(null);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
