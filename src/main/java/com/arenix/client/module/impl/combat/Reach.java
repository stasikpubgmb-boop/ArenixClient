package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.SliderSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Reach extends Module {

    private final SliderSetting reachSetting = new SliderSetting(3, 3, 6);

    public Reach() {
        super("Reach", Category.COMBAT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            double reach = reachSetting.getValue();

            for (Entity entity : MinecraftClient.getInstance().world.getEntities()) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    Box box = livingEntity.getBoundingBox();
                    Vec3d vec3d = new Vec3d(box.minX, box.minY, box.minZ);
                    Vec3d vec3d2 = new Vec3d(box.maxX, box.maxY, box.maxZ);
                    double distance = vec3d.distanceTo(player.getPos());
                    if (distance < reach) {
                        // Attack entity
                    }
                }
            }
        }
    }
}
