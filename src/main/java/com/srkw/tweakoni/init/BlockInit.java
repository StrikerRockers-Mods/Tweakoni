package com.srkw.tweakoni.init;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.block.BlockSpawnBlocker;
import com.srkw.tweakoni.block.minecraft.BlockCarpet;
import com.srkw.tweakoni.block.minecraft.BlockSea;
import com.srkw.tweakoni.block.minecraft.bed.BlockBed;
import com.srkw.tweakoni.block.minecraft.hopper.BlockHopper;
import com.srkw.tweakoni.block.minecraft.piston.BlockPistonBase;
import com.srkw.tweakoni.block.minecraft.piston.BlockPistonExtension;
import com.srkw.tweakoni.block.minecraft.piston.BlockPistonMoving;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
	
    public static BlockSea sea_lantern = new BlockSea(Material.GLASS);
    public static BlockSpawnBlocker SPAWN_BLOCKER = new BlockSpawnBlocker(Material.IRON);
    public static BlockCarpet CARPET = new BlockCarpet();

    public static BlockPistonBase PISTON = new BlockPistonBase(false, "piston");
    public static BlockPistonBase STICKY_PISTON = new BlockPistonBase(true, "sticky_piston");
    public static BlockPistonExtension PISTON_HEAD = new BlockPistonExtension("piston_head");
    public static BlockPistonMoving PISTON_EXTENSION = new BlockPistonMoving("piston_extension");
    public static BlockHopper HOPPER = new BlockHopper("hopper");
    public static BlockBed BED = new BlockBed("bed");

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                sea_lantern, SPAWN_BLOCKER, CARPET,
                PISTON, STICKY_PISTON, PISTON_HEAD, PISTON_EXTENSION,
                HOPPER, BED
        );
    }

    public static void registerModels() {
        SPAWN_BLOCKER.registerItemModel();
        Tweakoni.proxy.registerItemRenderer(Item.getItemFromBlock(PISTON), 0, "piston");
        Tweakoni.proxy.registerItemRenderer(Item.getItemFromBlock(STICKY_PISTON), 0, "sticky_piston");
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                new ItemBlock(SPAWN_BLOCKER).setRegistryName(SPAWN_BLOCKER.getRegistryName())
        );
    }
}
