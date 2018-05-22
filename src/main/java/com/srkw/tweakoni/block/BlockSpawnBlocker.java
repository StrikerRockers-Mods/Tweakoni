package com.srkw.tweakoni.block;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.tileentity.TESpawnBlocker;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpawnBlocker extends Block implements ITileEntityProvider
{

    public BlockSpawnBlocker(Material materialIn) {

        super(materialIn);
        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(18000000F);
        setHarvestLevel("pickaxe", 0);
        setUnlocalizedName("blocker");
        setRegistryName("blocker");
        setCreativeTab(CreativeTabs.MISC);

    }

    public void registerItemModel() {
    	
        Tweakoni.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "blocker");
        
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TESpawnBlocker();
        
    }
    
    @Override
    public BlockRenderLayer getBlockLayer() {
    	
        return BlockRenderLayer.CUTOUT;
        
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
    	
        return false;
        
    }


}
