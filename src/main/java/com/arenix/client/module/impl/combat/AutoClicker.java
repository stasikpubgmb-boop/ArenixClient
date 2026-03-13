package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.SliderSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Mouse;

public class AutoClicker extends Module {

    private final SliderSetting minCpsSetting = new SliderSetting(8, 8, 20);
    private final SliderSetting maxCpsSetting = new SliderSetting(12, 8, 20);
    private final KeyBinding keyBinding;
    private final Mouse mouse;

    public AutoClicker() {
        super("AutoClicker", Category.COMBAT);
        this.keyBinding = new KeyBinding("key.auto_clicker", InputUtil.Type.MOUSE, 0, "category.combat");
        this.mouse = MinecraftClient.getInstance().options.mouse;
    }

    @Override
    public void onTick() {
        if (keyBinding.wasPressed()) {
            double minCps = minCpsSetting.getValue();
            double maxCps = maxCpsSetting.getValue();
            double CPS = minCps + Math.random() * (maxCps - minCps);
            mouse.click(0);
        }
    }
}
