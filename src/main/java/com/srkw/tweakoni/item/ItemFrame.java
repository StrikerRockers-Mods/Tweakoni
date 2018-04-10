package com.srkw.tweakoni.item;

import com.srkw.tweakoni.block.Frame;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFrame extends Item {

	 @Override
	    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	    	
	    //spawm emtity
	    		
	    	
	    	
	        return EnumActionResult.PASS;
	    }
}
