package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.SliderSetting;
import net.minecraft.entity.player.PlayerEntity;

public class Fly extends Module {

    private final SliderSetting speedSetting = new SliderSetting(5, 1, 10);

    public Fly() {
        super("Fly", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            double speed = speedSetting.getValue();

            // Fly movement
        }
    }
}
