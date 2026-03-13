package com.arenix.client.module.impl.visual;

import com.arenix.module.Category;
import com.arenix.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ESP extends Module {

    public ESP() {
        super("ESP", Category.VISUAL);
    }

    @Override
    public void onRenderTick() {
        super.onRenderTick();
        MinecraftClient client = MinecraftClient.getInstance();
        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                Box box = livingEntity.getBoundingBox();
                Vec3d vec3d = new Vec3d(box.minX, box.minY, box.minZ);
                Vec3d vec3d2 = new Vec3d(box.maxX, box.maxY, box.maxZ);
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferBuilder = tessellator.getBuffer();
                bufferBuilder.begin(3, net.minecraft.client.render.VertexFormat.Position);
                bufferBuilder.vertex(vec3d.x, vec3d.y, vec3d.z).next();
                bufferBuilder.vertex(vec3d2.x, vec3d.y, vec3d.z).next();
                bufferBuilder.vertex(vec3d2.x, vec3d2.y, vec3d.z).next();
                bufferBuilder.vertex(vec3d.x, vec3d2.y, vec3d.z).next();
                Tessellator.getInstance().draw();
            }
        }
    }
}
