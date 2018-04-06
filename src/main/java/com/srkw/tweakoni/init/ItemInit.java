package com.srkw.tweakoni.init;

import com.srkw.tweakoni.item.ItemRotator;
import com.srkw.tweakoni.item.ItemSleepingBag;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {
    
	public static final Item ROTATOR = new ItemRotator("rotator");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
        		ROTATOR
        );
    }

    public static void registerModels() {

    }
}
