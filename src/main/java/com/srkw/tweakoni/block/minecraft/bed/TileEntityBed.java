package com.srkw.tweakoni.block.minecraft.bed;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

import javax.annotation.Nullable;

public class TileEntityBed extends net.minecraft.tileentity.TileEntityBed {

    private EnumDyeColor color = EnumDyeColor.RED;

    public EnumDyeColor getColor() {
        return this.color;
    }

    public void setColor(EnumDyeColor color) {
        this.color = color;
        markForUpdate();
    }

    //===============================TWEAKONI========================================//

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    private IBlockState getState() {
        return world.getBlockState(pos);
    }

    private void markForUpdate() {
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.notifyBlockUpdate(pos, getState(), getState(), 3);
        world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
        markDirty();
    }
}
