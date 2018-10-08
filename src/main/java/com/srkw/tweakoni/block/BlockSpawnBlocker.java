package com.srkw.tweakoni.block;

import com.srkw.tweakoni.tileentity.TESpawnBlocker;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;

public class BlockSpawnBlocker extends Block implements ITileEntityProvider {

    public BlockSpawnBlocker(String name, Material materialIn) {
        super(materialIn);
        setSoundType(SoundType.METAL);
        setHardness(5.0F);
        setResistance(18000F);
        setHarvestLevel("pickaxe", 0);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TESpawnBlocker();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
