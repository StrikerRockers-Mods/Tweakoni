package com.srkw.tweakoni.handlers;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.block.bed.TileEntityBed;
import com.srkw.tweakoni.block.bed.TileEntityBedRenderer;
import com.srkw.tweakoni.block.hopper.TileEntityHopper;
import com.srkw.tweakoni.block.piston.TileEntityPiston;
import com.srkw.tweakoni.block.piston.TileEntityPistonRenderer;
import com.srkw.tweakoni.events.CommonEvents;
import com.srkw.tweakoni.init.ItemInit;
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

    private static PotionType HASTE = new PotionType("haste", new PotionEffect(MobEffects.HASTE, 400, 0, true, false));
    private static PotionType STRONG_HASTE = new PotionType("strong.haste", new PotionEffect(MobEffects.HASTE, 400, 1, true, false));

    public static void preInitRegistries() {

    }

    public static void initRegistries() {
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        PacketHandler.registerMessages("tweakoni");
        GameRegistry.registerTileEntity(TileEntityHopper.class, "hopper_TE");
        GameRegistry.registerTileEntity(TESpawnBlocker.class, "spawnblocker_TE");
        GameRegistry.registerTileEntity(TileEntityPiston.class, "piston_TE");
        GameRegistry.registerTileEntity(TileEntityBed.class, "bed_TE");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPiston.class, new TileEntityPistonRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBed.class, new TileEntityBedRenderer());
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

    @SubscribeEvent
    public static void onPotionTypeRegister(RegistryEvent.Register<PotionType> event) {
        //TODO Fix the color
        HASTE.setRegistryName("haste");
        STRONG_HASTE.setRegistryName("strong.haste");
        event.getRegistry().register(STRONG_HASTE);
        event.getRegistry().register(HASTE);
        PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromItem(Item.getItemFromBlock(Blocks.GOLD_BLOCK)), HASTE);
        PotionHelper.addMix(HASTE, Ingredient.fromItem(Items.GLOWSTONE_DUST), STRONG_HASTE);

    }
}