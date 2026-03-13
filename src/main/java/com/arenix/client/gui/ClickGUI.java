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
        fill(matrices, 0, 0, width, height, new Color(0, 0, 0, 128).getRGB());

        for (Module module : modules) {
            if (module.getCategory() == Category.COMBAT) {
                drawModule(matrices, module, 10, 10);
            } else if (module.getCategory() == Category.MOVEMENT) {
                drawModule(matrices, module, 220, 10);
            } else if (module.getCategory() == Category.VISUAL) {
                drawModule(matrices, module, 10, 220);
            }
        }
    }

    private void drawModule(MatrixStack matrices, Module module, int x, int y) {
        fill(matrices, x, y, x + 200, y + 20, new Color(50, 50, 50, 255).getRGB());

        drawTextWithShadow(matrices, client.textRenderer, module.getName(), x + 10, y + 5, new Color(255, 255, 255, 255).getRGB());

        if (isMouseOver(x, y, 200, 20)) {
            hoveredModule = moduleManager.getModules().indexOf(module);
            fill(matrices, x, y, x + 200, y + 20, new Color(100, 100, 100, 255).getRGB());
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
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
}
