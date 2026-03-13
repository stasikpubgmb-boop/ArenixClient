package com.arenix.client.module.impl.movement;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;

public class Speed extends Module {

    private double speed = 5;
    private KeyBinding keyBinding;

    public Speed() {
        super("Speed", Category.MOVEMENT);
        keyBinding = new KeyBinding("key.speed", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "category.movement");
    }

    @Override
    public void onTick() {
        super.onTick();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.world != null && client.options.forwardKey.isPressed() && keyBinding.wasPressed()) {
            PlayerEntity player = client.player;
            double speed = this.speed;
            player.setVelocity(player.getVelocity().x * speed, player.getVelocity().y, player.getVelocity().z * speed);
        }
    }
}
