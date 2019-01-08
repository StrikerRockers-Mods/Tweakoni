package io.github.strikerrocker.tweakoni.mixins;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
public class MixinVillagerEntity {
    @Inject(method = "canBeLeashedBy", cancellable = true, at = @At("HEAD"))
    public void canBeLeashedBy(PlayerEntity playerEntity_1, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(true);
    }
}
