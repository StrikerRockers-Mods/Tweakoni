package com.srkw.tweakoni.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static KeyBinding shift = new KeyBinding("key.toggle.shift", Keyboard.KEY_L, "key.tweaktoni.category");
    public static KeyBinding alt = new KeyBinding("key.toggle.shift", Keyboard.KEY_LMENU, "key.tweaktoni.category");

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    public static void registerKeyBindings() {
        ClientRegistry.registerKeyBinding(shift);
        ClientRegistry.registerKeyBinding(alt);
    }
}
