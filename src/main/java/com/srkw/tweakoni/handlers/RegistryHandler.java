package com.srkw.tweakoni.handlers;

import com.srkw.tweakoni.block.hopper.TileEntityHopper;
import com.srkw.tweakoni.block.piston.TileEntityPiston;
import com.srkw.tweakoni.block.piston.TileEntityPistonRenderer;
import com.srkw.tweakoni.events.CommonEvents;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.network.PacketHandler;
import com.srkw.tweakoni.tileentity.TESpawnBlocker;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.srkw.tweakoni.init.BlockInit.*;

import com.srkw.tweakoni.Tweakoni;

@EventBusSubscriber
public class RegistryHandler {


    public static void preInitRegistries() {

    }

    public static void initRegistries() {
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        PacketHandler.registerMessages("tweakoni");
        GameRegistry.registerTileEntity(TESpawnBlocker.class, "spawnblocker_TE");
        GameRegistry.registerTileEntity(TileEntityPiston.class, "piston_TE");
        GameRegistry.registerTileEntity(TileEntityHopper.class, "hopper_TE");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPiston.class, new TileEntityPistonRenderer());
        NetworkRegistry.INSTANCE.registerGuiHandler(Tweakoni.instance, new GuiHandler());
    }

    public static void postInitRegistries() {
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ItemInit.register(event.getRegistry());
        registerItemBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        register(event.getRegistry());
    }


    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ItemInit.registerModels();
        registerModels();
    }

}
