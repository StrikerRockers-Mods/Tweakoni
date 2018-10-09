package com.srkw.tweakoni.block.minecraft.redstone;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRedstone extends Item {

    public ItemRedstone(String name) {
        this.setCreativeTab(CreativeTabs.REDSTONE);
        setRegistryName("minecraft", name);
        setTranslationKey(name);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        /**boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
         BlockPos blockpos = flag ? pos : pos.offset(facing);
         ItemStack itemstack = player.getHeldItem(hand);

         if (player.canPlayerEdit(blockpos, facing, itemstack) && worldIn.mayPlace(worldIn.getBlockState(blockpos).getBlock(), blockpos, false, facing, (Entity)null) && BlockInit.REDSTONE_WIRE.canPlaceBlockAt(worldIn, blockpos))
         {
         worldIn.setBlockState(blockpos, BlockInit.REDSTONE_WIRE.getDefaultState());

         if (player instanceof EntityPlayerMP)
         {
         CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, blockpos, itemstack);
         }

         itemstack.shrink(1);
         return EnumActionResult.SUCCESS;
         }
         else
         {
         return EnumActionResult.FAIL;
         }*/
        return EnumActionResult.FAIL;
    }

}
