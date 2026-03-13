package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Criticals extends Module {

    public Criticals() {
        super("Criticals", Category.COMBAT);
    }

    @Override
    public void onTick() {
        super.onTick();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null) {
            PlayerEntity player = client.player;
            for (Entity entity : client.world.getEntities()) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    // Attack the entity with critical hit
                }
            }
        }
    }
}
