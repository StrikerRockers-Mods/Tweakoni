package com.srkw.tweakoni.init;

import com.srkw.tweakoni.block.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
    public static final BlockBase BLOCK_BED = new BlockBase(Material.CLOTH, MapColor.DIRT);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                BLOCK_BED
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
