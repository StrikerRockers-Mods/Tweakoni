package com.srkw.tweakoni.handlers;

import com.srkw.tweakoni.block.minecraft.hopper.ContainerHopper;
import com.srkw.tweakoni.block.minecraft.hopper.GuiHopper;
import com.srkw.tweakoni.block.minecraft.hopper.TileEntityHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final int HOPPER_ID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch (ID) {

            case 0:
                return new ContainerHopper(player.inventory, (TileEntityHopper) world.getTileEntity(new BlockPos(x, y, z)), player);

        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch (ID) {

            case 0:
                return new GuiHopper(player.inventory, (TileEntityHopper) world.getTileEntity(new BlockPos(x, y, z)));

        }

        return null;
    }

}
