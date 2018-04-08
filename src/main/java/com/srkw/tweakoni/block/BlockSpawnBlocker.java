package com.srkw.tweakoni.block;

import javax.swing.text.html.parser.Entity;

import com.srkw.tweakoni.Tweakoni;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.Console;

public class BlockSpawnBlocker extends Block implements ITickable {

	private World world;
	private BlockPos blockPos;
	
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
	public void update() {

		world.getChunkFromBlockCoords(blockPos).removeEntity(new EntityZombie(world));
		
	}
	
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    	
    	world = worldIn;
    	blockPos = pos;
    	
    }
	

}
