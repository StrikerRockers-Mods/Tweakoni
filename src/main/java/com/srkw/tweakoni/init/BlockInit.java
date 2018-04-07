package com.srkw.tweakoni.init;

import com.srkw.tweakoni.block.BlockPistonMovingNew;
import com.srkw.tweakoni.block.BlockSea;
import com.srkw.tweakoni.block.BlockSpawnBlocker;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
    public static BlockSea sea_lantern = new BlockSea(Material.ROCK);
    public static Block PIS_MOVING = new BlockPistonMovingNew();
    public static BlockSpawnBlocker SPAWN_BLOCKER = new BlockSpawnBlocker(Material.IRON, "spawn_blocker");

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                sea_lantern, PIS_MOVING, SPAWN_BLOCKER
        );
    }

    public static void registerModels() {
    	SPAWN_BLOCKER.registerItemModel();
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
        		Item.getItemFromBlock(SPAWN_BLOCKER)
        );
    }
}
