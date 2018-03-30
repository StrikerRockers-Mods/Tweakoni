package com.srkw.tweakoni.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import static com.srkw.tweakoni.Tweakoni.proxy;

public class BlockBase extends Block {

    String name;

    BlockBase(Material material, MapColor mapColor) {
        super(material, mapColor);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public void registerItemModel(Block block) {
        proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }
}
