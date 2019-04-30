package io.github.strikerrocker.tweakoni.mixins;

import io.github.strikerrocker.tweakoni.EntityTickCallback;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(at = @At(value = "INVOKE_STRING",
            target = "Lnet/minecraft/util/Profiler;push(Ljava/lang/String;)V",
            args = "ldc=livingEntityBaseTick"
    ), method = "baseTick")
    public void updateLogic(CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        EntityTickCallback.EVENT.invoker().update(entity);
    }
}
