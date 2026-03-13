package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        super.onTick();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null && client.options.forwardKey.isPressed()) {
            PlayerEntity player = client.player;
            player.setSprinting(true);
        }
    }
}
