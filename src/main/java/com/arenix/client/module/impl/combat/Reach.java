package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Reach extends Module {

    private final double reach = 10;

    public Reach() {
        super("Reach", Category.COMBAT);
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
                    Box box = livingEntity.getBoundingBox();
                    Vec3d vec3d = new Vec3d(box.minX, box.minY, box.minZ);
                    Vec3d vec3d2 = new Vec3d(box.maxX, box.maxY, box.maxZ);
                    double distance = vec3d.distanceTo(player.getPos());
                    if (distance < reach) {
                        // Attack the entity
                    }
                }
            }
        }
    }
}
