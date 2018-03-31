package com.srkw.tweakoni.init;

import java.util.ArrayList;
import java.util.List;

import com.srkw.tweakoni.block.BlockBase;
import com.srkw.tweakoni.item.ItemSleepingBag;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {
	 public static final ItemSleepingBag SLEEPING_BAG = new ItemSleepingBag("sleeping_bag");
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(SLEEPING_BAG);
		
    }

    public static void registerModels() {
    	SLEEPING_BAG.registerItemModel();
    	
    }
}
