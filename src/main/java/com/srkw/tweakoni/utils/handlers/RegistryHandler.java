package com.srkw.tweakoni.utils.handlers;

import com.srkw.tweakoni.events.InteractEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import com.srkw.tweakoni.init.BlockInit;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.utils.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.srkw.tweakoni.proxy.ClientProxy.registerKeyBindings;

@EventBusSubscriber
public class RegistryHandler {

    public static void preInitRegistries() {
    }

    public static void initRegistries() {
        MinecraftForge.EVENT_BUS.register(new InteractEvent());
    }

    public static void postInitRegistries() {
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ItemInit.register(event.getRegistry());
        BlockInit.registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        BlockInit.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ItemInit.registerModels();
        BlockInit.registerModels();
    }

}
