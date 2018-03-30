package com.srkw.tweakoni.block;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.init.BlockInit;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.utils.handlers.GuiHandler;
import com.srkw.tweakoni.utils.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBed extends BlockBase {

    public BlockBed(String name, Material materialIn) {

        super(materialIn, MapColor.AIR);

        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(18000000F);
        setHarvestLevel("pickaxe", 0);
        setLightLevel(0F);
        setLightOpacity(15);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (worldIn.isRemote) {

            playerIn.openGui(Tweakoni.instance, GuiHandler.BLOCK_BED, worldIn, pos.getX(), pos.getY(), pos.getZ());

        } else {

            return false;

        }

        return true;
    }

}
