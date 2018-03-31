package com.srkw.tweakoni.init;

import com.srkw.tweakoni.item.ItemSleepingBag;
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
