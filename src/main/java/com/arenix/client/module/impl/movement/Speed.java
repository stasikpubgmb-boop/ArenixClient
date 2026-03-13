package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.SliderSetting;
import com.arenix.module.ModeSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class Speed extends Module {

    private final ModeSetting modeSetting = new ModeSetting("Strafe", List.of("Strafe", "Vanilla"));
    private final SliderSetting speedSetting = new SliderSetting(5, 1, 10);

    public Speed() {
        super("Speed", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            String mode = modeSetting.getValue();
            double speed = speedSetting.getValue();

            if (mode.equals("Strafe")) {
                // Strafe movement
            } else if (mode.equals("Vanilla")) {
                // Vanilla movement
            }
        }
    }
}
