package com.srkw.tweakoni.utils.handlers;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.block.Frame;
import com.srkw.tweakoni.events.CommonEvents;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry.EntityRegistration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.srkw.tweakoni.init.BlockInit.*;

import org.lwjgl.input.Keyboard;

@EventBusSubscriber
public class RegistryHandler {


    public static void preInitRegistries() {

    }

    public static void initRegistries() {
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
        PacketHandler.registerMessages("tweakoni");

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
