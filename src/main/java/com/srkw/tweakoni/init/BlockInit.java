package com.srkw.tweakoni.init;

import com.srkw.tweakoni.block.BlockPistonMovingNew;
import com.srkw.tweakoni.block.BlockSea;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
    public static BlockSea sea_lantern = new BlockSea(Material.ROCK);
    public static Block PIS_MOVING = new BlockPistonMovingNew();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                sea_lantern, PIS_MOVING
        );
    }

    public static void registerModels() {
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
        );
    }
}
