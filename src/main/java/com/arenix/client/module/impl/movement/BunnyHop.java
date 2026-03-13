package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class BunnyHop extends Module {

    public BunnyHop() {
        super("BunnyHop", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        super.onTick();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null && client.options.forwardKey.isPressed() && client.player.isOnGround()) {
            PlayerEntity player = client.player;
            player.jump();
        }
    }
}
