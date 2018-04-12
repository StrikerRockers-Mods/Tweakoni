package com.srkw.tweakoni.init;

import com.srkw.tweakoni.item.ItemRotator;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit
{

    public static final ItemRotator ROTATOR = new ItemRotator("rotator");

    public static void register(IForgeRegistry<Item> registry)
    {
        registry.registerAll(
                ROTATOR
        );
    }

    public static void registerModels()
    {
        ROTATOR.registerItemModel();
    }
}
