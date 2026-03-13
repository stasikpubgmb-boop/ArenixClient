package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;

public class Scaffold extends Module {

    public Scaffold() {
        super("Scaffold", Category.COMBAT);
    }

    @Override
    public void onTick() {
        super.onTick();
        if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player.isOnGround()) {
                return;
            }
            if (isBlockBelowAir(player)) {
                placeBlockBelowPlayer(player);
            }
        }
    }

    private boolean isBlockBelowAir(PlayerEntity player) {
        BlockPos posBelow = new BlockPos(player.getX(), player.getY() - 1, player.getZ());
        return MinecraftClient.getInstance().world.getBlockState(posBelow).isAir();
    }

    private void placeBlockBelowPlayer(PlayerEntity player) {
        BlockPos posBelow = new BlockPos(player.getX(), player.getY() - 1, player.getZ());
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.inventory.getStack(i);
            if (!stack.isEmpty() && stack.getItem().canPlaceOn(BlockPos.ZERO, Direction.UP)) {
                BlockHitResult result = new BlockHitResult(posBelow, Direction.UP, posBelow, true);
                MinecraftClient.getInstance().interactionManager.interactBlock(player, Hand.MAIN_HAND, result);
                return;
            }
        }
    }
}
