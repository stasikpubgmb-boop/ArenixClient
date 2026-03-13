package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.BooleanSetting;
import net.minecraft.entity.player.PlayerEntity;

public class Sprint extends Module {

    private final BooleanSetting omniSprintSetting = new BooleanSetting(false);

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            boolean omniSprint = omniSprintSetting.getValue();

            if (omniSprint) {
                // Omni sprint
            } else {
                // Normal sprint
            }
        }
    }
}
