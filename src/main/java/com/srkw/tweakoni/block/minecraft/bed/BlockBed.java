package com.srkw.tweakoni.block.minecraft.bed;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockBed extends net.minecraft.block.BlockBed {

    public BlockBed(String name) {
        this.hasTileEntity = true;
        setRegistryName("minecraft", name);
        setTranslationKey(name);
    }

    @SideOnly(Side.CLIENT)
    public static boolean isHeadPiece(int metadata) {
        return (metadata & 8) != 0;
    }

    @Nullable
    private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos) {
        for (EntityPlayer entityplayer : worldIn.playerEntities) {
            if (entityplayer.isPlayerSleeping() && entityplayer.bedLocation.equals(pos)) {
                return entityplayer;
            }
        }

        return null;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            if (state.getValue(PART) != BlockBed.EnumPartType.HEAD) {
                pos = pos.offset(state.getValue(FACING));
                state = worldIn.getBlockState(pos);

                if (state.getBlock() != this) {
                    return true;
                }
            }
            if (worldIn.provider.canRespawnHere() && worldIn.getBiome(pos) != Biomes.HELL) {
                EntityPlayer entityplayer = this.getPlayerInBed(worldIn, pos);
                if (entityplayer != null) {
                    playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.occupied"), true);
                    return true;
                }
                EntityPlayer.SleepResult entityplayer$sleepresult = playerIn.trySleep(pos);
                if (entityplayer$sleepresult == EntityPlayer.SleepResult.OK) {
                    return true;
                } else {
                    if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW) {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep"), true);
                    } else if (entityplayer$sleepresult == EntityPlayer.SleepResult.NOT_SAFE) {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe"), true);
                    } else if (entityplayer$sleepresult == EntityPlayer.SleepResult.TOO_FAR_AWAY) {
                        playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway"), true);
                    }

                    return true;
                }
            } else {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos = pos.offset(state.getValue(FACING).getOpposite());

                if (worldIn.getBlockState(blockpos).getBlock() == this) {
                    worldIn.setBlockToAir(blockpos);
                }
                worldIn.newExplosion(null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 5.0F, true, true);
                return true;
            }
        }
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return super.createTileEntity(world, state);
    }
}
  
