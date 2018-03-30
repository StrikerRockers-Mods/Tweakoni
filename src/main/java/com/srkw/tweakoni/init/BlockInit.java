package com.srkw.tweakoni.init;

import java.util.ArrayList;
import java.util.List;

import com.srkw.tweakoni.block.BlockBed;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block BLOCK_BED = new BlockBed("minecraft:bed", Material.IRON);
	
}
