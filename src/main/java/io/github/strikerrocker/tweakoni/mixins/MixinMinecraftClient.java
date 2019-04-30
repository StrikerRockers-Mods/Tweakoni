package io.github.strikerrocker.tweakoni.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "method_1508", at = @At("HEAD"))
    public void keybindings(CallbackInfo info) {
        /*MinecraftClient mc = (MinecraftClient) (Object) this;
        while (TweakoniClient.dgamma.method_1436()) {
            mc.options.method_1625(GameOptions.Option.GAMMA, MinecraftClient.getInstance().options.gamma - 0.1F);
            System.out.println("d gamma");
        }
        while (TweakoniClient.igamma.method_1436()) {
            mc.options.method_1625(GameOptions.Option.GAMMA, MinecraftClient.getInstance().options.gamma + 0.1F);
            System.out.println("igamma");
        }
        if (mc.options.gamma > 5F) {
            mc.options.method_1625(GameOptions.Option.GAMMA, 5F);
        }
        if (mc.options.gamma < -5F) {
            mc.options.method_1625(GameOptions.Option.GAMMA, -5F);
        }*/
    }
}
