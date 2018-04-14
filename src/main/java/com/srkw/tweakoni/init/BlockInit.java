package com.srkw.tweakoni.init;

import com.srkw.tweakoni.block.BlockCarpet;
import com.srkw.tweakoni.block.BlockSea;
import com.srkw.tweakoni.block.BlockSpawnBlocker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockInit
{
    public static BlockSea sea_lantern = new BlockSea(Material.ROCK);
    public static BlockSpawnBlocker SPAWN_BLOCKER = new BlockSpawnBlocker(Material.IRON);
    public static BlockCarpet CARPET = new BlockCarpet();

    public static void register(IForgeRegistry<Block> registry)
    {
        registry.registerAll(
                sea_lantern, SPAWN_BLOCKER, CARPET
        );
    }

    public static void registerModels()
    {
        SPAWN_BLOCKER.registerItemModel();
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                new ItemBlock(SPAWN_BLOCKER).setRegistryName(SPAWN_BLOCKER.getRegistryName())
        );
    }
}
