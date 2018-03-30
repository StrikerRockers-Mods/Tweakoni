package com.srkw.tweakoni.init;

import java.util.ArrayList;
import java.util.List;

import com.srkw.tweakoni.block.BlockBase;
import com.srkw.tweakoni.block.BlockBed;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
    public static final BlockBase BLOCK_BED = new BlockBed("minecraft:bed", Material.IRON);

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
