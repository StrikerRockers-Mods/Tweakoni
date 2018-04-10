package com.srkw.tweakoni.item;

import java.util.Set;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.Console;

public class ItemRotator extends Item {

	public ItemRotator(String name) {
		
		super();
        setCreativeTab(CreativeTabs.TOOLS);
        setMaxStackSize(1);
		setMaxDamage(50);
        setUnlocalizedName(name);
        setRegistryName(name);

		
	}
	
    public void registerItemModel() {
        Tweakoni.proxy.registerItemRenderer(this, 0, "rotator");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	
    	//Declaring reference variables
    	Block block = worldIn.getBlockState(pos).getBlock();
    	IBlockState startState = worldIn.getBlockState(pos);
    	
    	if (player.isAllowEdit() && hand == EnumHand.MAIN_HAND) { 
    		
    		//Rotate the block around the facing axis
    		block.rotateBlock(worldIn, pos, facing); 
        	
        	//Reference to check if the new block is different from the original one
        	IBlockState endState = worldIn.getBlockState(pos);   	
        	
        	if (startState != endState) {
        		
             	//Damage the item
        		player.getHeldItem(hand).damageItem(1, player);
        		
        		//Play the block placing sound
        		SoundType soundtype = block.getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getHitSound(), SoundCategory.BLOCKS, soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
                
                //Force the arm to swing
            	player.swingArm(hand);         
            	
            	return EnumActionResult.SUCCESS;
            	
        	}
        	
        	return EnumActionResult.FAIL;
    		
    	}
    	
        return EnumActionResult.PASS;
    }
    
}
