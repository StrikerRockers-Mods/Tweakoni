package com.srkw.tweakoni.utils.handlers;

import com.srkw.tweakoni.gui.GuiBlockBed;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int BLOCK_BED = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		switch(ID) {
		
		case 0 : return new GuiBlockBed();
		
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		switch(ID) {
		
		case 0 : return new GuiBlockBed();
		
		}
		
		return null;
	}
	
}
