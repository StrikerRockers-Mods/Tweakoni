package com.srkw.tweakoni.init;

import com.srkw.tweakoni.block.BlockBase;
import com.srkw.tweakoni.block.BlockSea;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
    public static final BlockBase BLOCK_BED = new BlockBase(Material.CLOTH, MapColor.DIRT);
    public static BlockSea sea_lantern = new BlockSea(Material.ROCK);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                BLOCK_BED, sea_lantern
        );
    }

    public static void registerModels() {
        BLOCK_BED.registerItemModel(BLOCK_BED);
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                BLOCK_BED.createItemBlock()
        );
    }
}
