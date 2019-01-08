package io.github.strikerrocker.tweakoni.events;

import net.fabricmc.fabric.util.HandlerArray;
import net.fabricmc.fabric.util.HandlerRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Events {
    public static final HandlerRegistry<EntityUpdate> ENTITY_UPDATE_EVENT = new HandlerArray(EntityUpdate.class);

    private Events() {
    }

    @FunctionalInterface
    public interface EntityUpdate {
        void update(LivingEntity var1, World var2);
    }
}
