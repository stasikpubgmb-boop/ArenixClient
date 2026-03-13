package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import com.arenix.module.SliderSetting;
import com.arenix.module.BooleanSetting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;

public class KillAura extends Module {

    private final SliderSetting rangeSetting = new SliderSetting(4.5, 3, 6);
    private final SliderSetting cpsSetting = new SliderSetting(12, 1, 20);
    private final BooleanSetting mobsSetting = new BooleanSetting(true);
    private final BooleanSetting playersSetting = new BooleanSetting(true);

    public KillAura() {
        super("KillAura", Category.COMBAT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            double range = rangeSetting.getValue();
            double CPS = cpsSetting.getValue();
            boolean mobs = mobsSetting.getValue();
            boolean players = playersSetting.getValue();

            for (Entity entity : MinecraftClient.getInstance().world.getEntities()) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    double distance = player.getDistance(livingEntity);
                    if (distance <= range) {
                        if (mobs && livingEntity instanceof Mob) {
                            // Attack mob
                        } else if (players && livingEntity instanceof PlayerEntity) {
                            // Attack player
                        }
                    }
                }
            }
        }
    }
}
