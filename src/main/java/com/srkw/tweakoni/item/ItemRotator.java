package com.srkw.tweakoni.item;

import com.srkw.tweakoni.Tweakoni;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketUseBed;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemRotator extends Item {

	public ItemRotator(String name) {
		
		super();
        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxStackSize(1);
        setUnlocalizedName(name);
        setRegistryName(name);
        
	}
	
    public void registerItemModel() {
        Tweakoni.proxy.registerItemRenderer(this, 0, "rotator");
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        
    	Block block = worldIn.getBlockState(pos).getBlock();
    	
    	if (player.isCreative()) { block.rotateBlock(worldIn, pos, facing); return EnumActionResult.SUCCESS;}

        return EnumActionResult.PASS;
    }

}
