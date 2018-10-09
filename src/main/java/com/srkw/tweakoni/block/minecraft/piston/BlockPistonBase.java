package com.srkw.tweakoni.block.minecraft.piston;

import com.google.common.collect.Lists;
import com.srkw.tweakoni.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonExtension.EnumPistonType;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;


public class BlockPistonBase extends net.minecraft.block.BlockPistonBase {

    /**
     * This piston is the sticky one?
     */
    private final boolean isSticky;

    public BlockPistonBase(boolean isSticky, String name) {
        super(isSticky);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EXTENDED, Boolean.FALSE));
        this.isSticky = isSticky;
        this.setSoundType(SoundType.STONE);
        this.setHardness(0.5F);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        setRegistryName("minecraft", name);
        setTranslationKey(name);
    }

    /**
     * Checks if the piston can push the given BlockState.
     */
    public static boolean canPush(IBlockState blockStateIn, World worldIn, BlockPos pos, EnumFacing facing, boolean destroyBlocks, EnumFacing p_185646_5_) {
        Block block = blockStateIn.getBlock();

        if (block == Blocks.OBSIDIAN) {
            return false;
        } else if (!worldIn.getWorldBorder().contains(pos)) {
            return false;
        } else if (pos.getY() >= 0 && (facing != EnumFacing.DOWN || pos.getY() != 0)) {
            if (pos.getY() <= worldIn.getHeight() - 1 && (facing != EnumFacing.UP || pos.getY() != worldIn.getHeight() - 1)) {
                if (block != BlockInit.PISTON && block != BlockInit.STICKY_PISTON) {
                    if (blockStateIn.getBlockHardness(worldIn, pos) == -1.0F) {
                        return false;
                    }

                    switch (blockStateIn.getPushReaction()) {
                        case BLOCK:
                            return false;
                        case DESTROY:
                            return destroyBlocks;
                        case PUSH_ONLY:
                            return facing == p_185646_5_;
                        default:
                            break;
                    }
                } else if (blockStateIn.getValue(EXTENDED)) {
                    return false;
                }

                return !block.hasTileEntity(blockStateIn);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean shouldBeExtended(World worldIn, BlockPos pos, EnumFacing facing) {
        for (EnumFacing enumfacing : EnumFacing.values()) {
            if (enumfacing != facing && worldIn.isSidePowered(pos.offset(enumfacing), enumfacing)) {
                return true;
            }
        }

        if (worldIn.isSidePowered(pos, EnumFacing.DOWN)) {
            return true;
        } else {
            BlockPos blockpos = pos.up();

            for (EnumFacing enumfacing1 : EnumFacing.values()) {
                if (enumfacing1 != EnumFacing.DOWN && worldIn.isSidePowered(blockpos.offset(enumfacing1), enumfacing1)) {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Called on server when World#addBlockEvent is called. If server returns true, then also called on the client. On
     * the Server, this may perform additional changes to the world, like pistons replacing the block with an extended
     * base. On the client, the update may involve replacing tile entities or effects such as sounds or particles
     */
    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        EnumFacing enumfacing = state.getValue(FACING);

        if (!worldIn.isRemote) {
            boolean flag = this.shouldBeExtended(worldIn, pos, enumfacing);

            if (flag && id == 1) {
                worldIn.setBlockState(pos, state.withProperty(EXTENDED, Boolean.TRUE), 2);
                return false;
            }

            if (!flag && id == 0) {
                return false;
            }
        }

        if (id == 0) {
            if (!this.doMove(worldIn, pos, enumfacing, true)) {
                return false;
            }

            worldIn.setBlockState(pos, state.withProperty(EXTENDED, Boolean.TRUE), 3);
            worldIn.playSound(null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
        } else if (id == 1) {
            TileEntity tileentity1 = worldIn.getTileEntity(pos.offset(enumfacing));

            if (tileentity1 instanceof TileEntityPiston) {
                ((TileEntityPiston) tileentity1).clearPistonTileEntity();
            }

            worldIn.setBlockState(pos, BlockInit.PISTON_EXTENSION.getDefaultState().withProperty(BlockPistonMoving.FACING, enumfacing).withProperty(BlockPistonMoving.TYPE, this.isSticky ? EnumPistonType.STICKY : EnumPistonType.DEFAULT), 3);
            worldIn.setTileEntity(pos, BlockPistonMoving.createTilePiston(this.getStateFromMeta(param), enumfacing, false, true));

            if (this.isSticky) {
                BlockPos blockpos = pos.add(enumfacing.getXOffset() * 2, enumfacing.getYOffset() * 2, enumfacing.getZOffset() * 2);
                IBlockState iblockstate = worldIn.getBlockState(blockpos);
                Block block = iblockstate.getBlock();
                boolean flag1 = false;

                if (block == BlockInit.PISTON_EXTENSION) {
                    TileEntity tileentity = worldIn.getTileEntity(blockpos);

                    if (tileentity instanceof TileEntityPiston) {
                        TileEntityPiston tileentitypiston = (TileEntityPiston) tileentity;

                        if (tileentitypiston.getFacing() == enumfacing && tileentitypiston.isExtending()) {
                            tileentitypiston.clearPistonTileEntity();
                            flag1 = true;
                        }
                    }
                }

                if (!flag1 && !iblockstate.getBlock().isAir(iblockstate, worldIn, blockpos) && canPush(iblockstate, worldIn, blockpos, enumfacing.getOpposite(), false, enumfacing) && (iblockstate.getPushReaction() == EnumPushReaction.NORMAL || block == BlockInit.PISTON || block == BlockInit.STICKY_PISTON)) {
                    this.doMove(worldIn, pos, enumfacing, false);
                }
            } else {
                worldIn.setBlockToAir(pos.offset(enumfacing));
            }

            worldIn.playSound(null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
        }

        return true;
    }

    private boolean doMove(World worldIn, BlockPos pos, EnumFacing direction, boolean extending) {
        if (!extending) {
            worldIn.setBlockToAir(pos.offset(direction));
        }

        BlockPistonStructureHelper blockpistonstructurehelper = new BlockPistonStructureHelper(worldIn, pos, direction, extending);

        if (!blockpistonstructurehelper.canMove()) {
            return false;
        } else {
            List<BlockPos> list = blockpistonstructurehelper.getBlocksToMove();
            List<IBlockState> list1 = Lists.newArrayList();

            for (BlockPos blockpos : list) {
                list1.add(worldIn.getBlockState(blockpos).getActualState(worldIn, blockpos));
            }

            List<BlockPos> list2 = blockpistonstructurehelper.getBlocksToDestroy();
            int k = list.size() + list2.size();
            IBlockState[] aiblockstate = new IBlockState[k];
            EnumFacing enumfacing = extending ? direction : direction.getOpposite();

            for (int j = list2.size() - 1; j >= 0; --j) {
                BlockPos blockpos1 = list2.get(j);
                IBlockState iblockstate = worldIn.getBlockState(blockpos1);
                // Forge: With our change to how snowballs are dropped this needs to disallow to mimic vanilla behavior.
                float chance = iblockstate.getBlock() instanceof BlockSnow ? -1.0f : 1.0f;
                iblockstate.getBlock().dropBlockAsItemWithChance(worldIn, blockpos1, iblockstate, chance, 0);
                worldIn.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 4);
                --k;
                aiblockstate[k] = iblockstate;
            }

            for (int l = list.size() - 1; l >= 0; --l) {
                BlockPos blockpos3 = list.get(l);
                IBlockState iblockstate2 = worldIn.getBlockState(blockpos3);
                worldIn.setBlockState(blockpos3, Blocks.AIR.getDefaultState(), 2);
                blockpos3 = blockpos3.offset(enumfacing);
                worldIn.setBlockState(blockpos3, BlockInit.PISTON_EXTENSION.getDefaultState().withProperty(FACING, direction), 4);
                worldIn.setTileEntity(blockpos3, BlockPistonMoving.createTilePiston(list1.get(l), direction, extending, false));
                --k;
                aiblockstate[k] = iblockstate2;
            }

            BlockPos blockpos2 = pos.offset(direction);

            if (extending) {
                EnumPistonType blockpistonextension$PistonType = this.isSticky ? EnumPistonType.STICKY : EnumPistonType.DEFAULT;
                IBlockState iblockstate3 = BlockInit.PISTON_HEAD.getDefaultState().withProperty(BlockPistonExtension.FACING, direction).withProperty(BlockPistonExtension.TYPE, blockpistonextension$PistonType);
                IBlockState iblockstate1 = BlockInit.PISTON_EXTENSION.getDefaultState().withProperty(BlockPistonMoving.FACING, direction).withProperty(BlockPistonMoving.TYPE, this.isSticky ? EnumPistonType.STICKY : EnumPistonType.DEFAULT);
                worldIn.setBlockState(blockpos2, iblockstate1, 4);
                worldIn.setTileEntity(blockpos2, BlockPistonMoving.createTilePiston(iblockstate3, direction, true, true));
            }

            for (int i1 = list2.size() - 1; i1 >= 0; --i1) {
                worldIn.notifyNeighborsOfStateChange(list2.get(i1), aiblockstate[k++].getBlock(), false);
            }

            for (int j1 = list.size() - 1; j1 >= 0; --j1) {
                worldIn.notifyNeighborsOfStateChange(list.get(j1), aiblockstate[k++].getBlock(), false);
            }

            if (extending) {
                worldIn.notifyNeighborsOfStateChange(blockpos2, BlockInit.PISTON_HEAD, false);
            }

            return true;
        }
    }

}
