package com.srkw.tweakoni.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.srkw.tweakoni.utils.handlers.RegistryHandler.SHIFT_HANDLER_CAPABILITY;

public class Provider implements ICapabilitySerializable<NBTTagCompound> {

    ShiftHandler instance = SHIFT_HANDLER_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == SHIFT_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? SHIFT_HANDLER_CAPABILITY.cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) SHIFT_HANDLER_CAPABILITY.getStorage().writeNBT(SHIFT_HANDLER_CAPABILITY, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        SHIFT_HANDLER_CAPABILITY.getStorage().readNBT(SHIFT_HANDLER_CAPABILITY, instance, null, nbt);
    }
}
