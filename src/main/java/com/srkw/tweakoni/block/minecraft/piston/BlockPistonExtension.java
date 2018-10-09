package com.srkw.tweakoni.block.minecraft.piston;

import com.srkw.tweakoni.init.BlockInit;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPistonExtension extends net.minecraft.block.BlockPistonExtension {

    public BlockPistonExtension(String name) {
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        setRegistryName("minecraft", name);
        setTranslationKey(name);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(state.getValue(TYPE) == net.minecraft.block.BlockPistonExtension.EnumPistonType.STICKY ? BlockInit.STICKY_PISTON : BlockInit.PISTON);
    }
}
