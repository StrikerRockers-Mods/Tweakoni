package com.srkw.tweakoni.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import static com.srkw.tweakoni.Tweakoni.proxy;

public class BlockBase extends BlockBed {

    String name = "bed";

    public BlockBase(Material material, MapColor mapColor) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    public void registerItemModel(Block block) {
        proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    // @Override
    // public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

    //   if (worldIn.isRemote) {

    //      playerIn.openGui(Tweakoni.instance, GuiHandler.BLOCK_BED, worldIn, pos.getX(), pos.getY(), pos.getZ());

    //   } else {

    //       return false;

    //   }

    //   return true;
    // }


}
