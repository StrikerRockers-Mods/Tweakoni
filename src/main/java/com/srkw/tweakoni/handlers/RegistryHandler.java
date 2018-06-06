package com.srkw.tweakoni.handlers;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.block.minecraft.bed.TileEntityBed;
import com.srkw.tweakoni.block.minecraft.bed.TileEntityBedRenderer;
import com.srkw.tweakoni.block.minecraft.hopper.TileEntityHopper;
import com.srkw.tweakoni.block.minecraft.piston.TileEntityPiston;
import com.srkw.tweakoni.block.minecraft.piston.TileEntityPistonRenderer;
import com.srkw.tweakoni.events.CommonEvents;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.init.PotionInit;
import com.srkw.tweakoni.network.PacketHandler;
import com.srkw.tweakoni.tileentity.TESpawnBlocker;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.srkw.tweakoni.init.BlockInit.*;

@EventBusSubscriber
public class RegistryHandler {

    public static void preInitRegistries() {

    }

    public static void initRegistries() {
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        onObjectRegister();       
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

    @SubscribeEvent
    public static void onPotionTypeRegister(RegistryEvent.Register<PotionType> event) {
    	PotionInit.register(event.getRegistry());
    }
    
    
    public static void onObjectRegister() {
        PacketHandler.registerMessages("tweakoni");
    	GameRegistry.registerTileEntity(TileEntityHopper.class, "hopper_TE");
        GameRegistry.registerTileEntity(TESpawnBlocker.class, "spawnblocker_TE");
        GameRegistry.registerTileEntity(TileEntityPiston.class, "piston_TE");
        GameRegistry.registerTileEntity(TileEntityBed.class, "bed_TE");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPiston.class, new TileEntityPistonRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBed.class, new TileEntityBedRenderer());
        NetworkRegistry.INSTANCE.registerGuiHandler(Tweakoni.instance, new GuiHandler());
    }
    
}