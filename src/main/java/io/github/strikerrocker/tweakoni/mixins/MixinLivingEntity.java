package io.github.strikerrocker.tweakoni.mixins;

import io.github.strikerrocker.tweakoni.events.Events;
import net.fabricmc.fabric.util.HandlerArray;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
        @Inject(at = @At(value = "INVOKE_STRING",
            target = "Lnet/minecraft/util/Profiler;begin(Ljava/lang/String;)V",
            args = "ldc=livingEntityBaseTick"
    ),method = "updateLogic")
    public void updateLogic(CallbackInfo info){
        Events.EntityUpdate[] var3 = (Events.EntityUpdate[])((HandlerArray) Events.ENTITY_UPDATE_EVENT).getBackingArray();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Events.EntityUpdate handler = var3[var5];
            LivingEntity entity =(LivingEntity)(Object)this;
            handler.update(entity,entity.world);
        }
    }
}
