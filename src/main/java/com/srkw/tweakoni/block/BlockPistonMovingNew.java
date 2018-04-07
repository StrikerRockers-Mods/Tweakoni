package com.srkw.tweakoni.block;

import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.state.IBlockState;

public class BlockPistonMovingNew extends BlockPistonMoving {

    public BlockPistonMovingNew() {
        this.setRegistryName("piston_extension");
        this.setUnlocalizedName("piston_extension");
    }

    @Override
    public boolean isTopSolid(IBlockState p_isTopSolid_1_) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState p_isFullCube_1_) {
        return true;
    }
}
