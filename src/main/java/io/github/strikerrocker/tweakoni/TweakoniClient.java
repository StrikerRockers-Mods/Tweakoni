package io.github.strikerrocker.tweakoni;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public class TweakoniClient implements ClientModInitializer {
    public static FabricKeyBinding dgamma;
    public static FabricKeyBinding igamma;
    public static FabricKeyBinding bb;

    @Override
    public void onInitializeClient() {
        KeyBindingRegistry.INSTANCE.addCategory("category.tweakoni");
        KeyBindingRegistry.INSTANCE.register(dgamma = FabricKeyBinding.Builder.create(new Identifier("tweakoni", "dgamma"), InputUtil.Type.KEYSYM, 333, "category.tweakoni").build());
        KeyBindingRegistry.INSTANCE.register(igamma = FabricKeyBinding.Builder.create(new Identifier("tweakoni", "igamma"), InputUtil.Type.KEYSYM, 334, "category.tweakoni").build());
        KeyBindingRegistry.INSTANCE.register(bb = FabricKeyBinding.Builder.create(new Identifier("tweakoni", "bb"), InputUtil.Type.KEYSYM, 334, "category.tweakoni").build());
    }
}