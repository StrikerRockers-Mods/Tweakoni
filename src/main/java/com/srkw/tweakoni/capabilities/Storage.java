package com.srkw.tweakoni.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class Storage implements Capability.IStorage<ShiftHandler> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ShiftHandler> capability, ShiftHandler instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("shift", instance.getShift());
        return tag;
    }

    @Override
    public void readNBT(Capability<ShiftHandler> capability, ShiftHandler instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setShift(tag.getBoolean("shift"));
    }
}
