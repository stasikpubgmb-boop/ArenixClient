package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.entity.player.PlayerEntity;

public class BunnyHop extends Module {

    public BunnyHop() {
        super("BunnyHop", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.isOnGround()) {
            player.jump();
        }
    }
}
