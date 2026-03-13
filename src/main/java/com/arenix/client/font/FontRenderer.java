package com.arenix.client.font;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.Window;

import java.awt.Color;
import java.util.function.Function;

public class FontRenderer extends TextRenderer {

    private final Window window;

    public FontRenderer(MinecraftClient client) {
        super(client);
        this.window = client.getWindow();
    }

    public void drawTextWithShadow(MatrixStack matrices, String text, int x, int y, Color color) {
        fill(matrices, x - 1, y, x + getStringWidth(text), y + 9, new Color(0, 0, 0, 128).getRGB());
        fill(matrices, x, y - 1, x + getStringWidth(text), y + 8, new Color(0, 0, 0, 128).getRGB());
        fill(matrices, x + 1, y, x + getStringWidth(text) + 1, y + 9, new Color(0, 0, 0, 128).getRGB());
        fill(matrices, x, y + 1, x + getStringWidth(text), y + 9, new Color(0, 0, 0, 128).getRGB());
        drawTextWithShadow(matrices, text, x, y, color.getRGB());
    }

    public void drawTextWithShadow(MatrixStack matrices, String text, int x, int y, int color) {
        drawTextWithShadow(matrices, text, x - 1, y, color);
        drawTextWithShadow(matrices, text, x + 1, y, color);
        drawTextWithShadow(matrices, text, x, y - 1, color);
        drawTextWithShadow(matrices, text, x, y + 1, color);
        drawTextWithShadow(matrices, text, x, y, color);
    }
}
