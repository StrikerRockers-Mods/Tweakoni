package com.srkw.tweakoni.block.minecraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCarpetMod extends BlockCarpet {

    public BlockCarpetMod(String name) {
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        setRegistryName("minecraft", name);
        setTranslationKey(name);
        setSoundType(SoundType.CLOTH);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i = 0; i < 16; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
    }
}