package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Mouse;

public class AutoClicker extends Module {

    private final KeyBinding keyBinding;
    private final Mouse mouse;

    public AutoClicker() {
        super("AutoClicker", Category.COMBAT);
        keyBinding = new KeyBinding("key.auto_clicker", InputUtil.Type.MOUSE, 0, "category.combat");
        mouse = MinecraftClient.getInstance().options mouse;
    }

    @Override
    public void onTick() {
        super.onTick();
        if (keyBinding.wasPressed()) {
            mouse.click(0);
        }
    }
}
