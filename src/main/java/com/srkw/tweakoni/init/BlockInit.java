package com.srkw.tweakoni.init;

import com.srkw.tweakoni.block.BlockSea;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit {
    public static BlockSea sea_lantern = new BlockSea(Material.ROCK);

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                sea_lantern
        );
    }

    public static void registerModels(){
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
        );
    }
}
