package com.arenix.client.module.impl.visual;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.BooleanSetting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;

public class ESP extends Module {

    private final BooleanSetting playersSetting = new BooleanSetting(true);
    private final BooleanSetting mobsSetting = new BooleanSetting(true);

    public ESP() {
        super("ESP", Category.VISUAL);
    }

    @Override
    public void onRenderTick() {
        MinecraftClient client = MinecraftClient.getInstance();
        boolean players = playersSetting.getValue();
        boolean mobs = mobsSetting.getValue();

        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                Box box = livingEntity.getBoundingBox();

                if (players && livingEntity instanceof PlayerEntity) {
                    // Draw player ESP
                } else if (mobs && livingEntity instanceof Mob) {
                    // Draw mob ESP
                }
            }
        }
    }
}
