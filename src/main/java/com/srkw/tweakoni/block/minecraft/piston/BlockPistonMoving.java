package com.srkw.tweakoni.block.minecraft.piston;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPistonMoving extends net.minecraft.block.BlockPistonMoving {

    public BlockPistonMoving(String name) {
        this.setHardness(-1.0F);
        setRegistryName("minecraft", name);
        setTranslationKey(name);
    }


    public static TileEntity createTilePiston(IBlockState blockStateIn, EnumFacing facingIn, boolean extendingIn, boolean shouldHeadBeRenderedIn) {
        return new TileEntityPiston(blockStateIn, facingIn, extendingIn, shouldHeadBeRenderedIn);
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityPiston) {
            ((TileEntityPiston) tileentity).clearPistonTileEntity();
        } else {
            super.breakBlock(worldIn, pos, state);
        }
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        TileEntityPiston tileentitypiston = this.getTilePistonAt(worldIn, pos);
        return tileentitypiston == null ? null : tileentitypiston.getAABB(worldIn, pos);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        TileEntityPiston tileentitypiston = this.getTilePistonAt(worldIn, pos);

        if (tileentitypiston != null) {
            tileentitypiston.addCollissionAABBs(worldIn, pos, entityBox, collidingBoxes, entityIn);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        TileEntityPiston tileentitypiston = this.getTilePistonAt(source, pos);
        return tileentitypiston != null ? tileentitypiston.getAABB(source, pos) : FULL_BLOCK_AABB;
    }

    /**
     * Gets a TileEntityPiston at the given position. Returns null if the tile is not an instance of TileEntityPiston.
     */
    @Nullable
    private TileEntityPiston getTilePistonAt(IBlockAccess iBlockAccessIn, BlockPos blockPosIn) {
        TileEntity tileentity = iBlockAccessIn.getTileEntity(blockPosIn);
        return tileentity instanceof TileEntityPiston ? (TileEntityPiston) tileentity : null;
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<net.minecraft.item.ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TileEntityPiston tileentitypiston = this.getTilePistonAt(world, pos);
        if (tileentitypiston != null) {
            IBlockState pushed = tileentitypiston.getPistonState();
            drops.addAll(pushed.getBlock().getDrops(world, pos, pushed, fortune)); // use the old method until it gets removed, for backward compatibility
        }
    }
}
