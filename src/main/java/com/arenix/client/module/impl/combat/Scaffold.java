package com.arenix.client.module.impl.combat;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.entity.player.PlayerEntity;

public class Scaffold extends Module {

    public Scaffold() {
        super("Scaffold", Category.COMBAT);
    }

    @Override
    public void onTick() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.isOnGround()) {
            return;
        }
        if (isBlockBelowAir(player)) {
            placeBlockBelowPlayer(player);
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
