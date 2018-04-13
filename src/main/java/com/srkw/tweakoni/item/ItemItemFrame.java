package com.srkw.tweakoni.item;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.entity.EntityItemFrame;
import com.srkw.tweakoni.proxy.ClientProxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.Console;

public class ItemItemFrame extends Item {

	public ItemItemFrame(String name) {
		
		setRegistryName("minecraft", name);
		setUnlocalizedName(name);
	}
	
	 public void registerItemModel() {
		 
	        Tweakoni.proxy.registerItemRenderer(this, 0, "rotator");
	        
	 }

	
	@Override 
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) { 

		//worldIn.spawnEntity(new EntityItemFrame());
		worldIn.joinEntityInSurroundings(new EntityItemFrame());
      
		if (ClientProxy.item_frame.isKeyDown()) {
    	   
    	   Console.print("With KeyBind!");
    	   
       }
        return EnumActionResult.PASS; 
    } 
	
}
