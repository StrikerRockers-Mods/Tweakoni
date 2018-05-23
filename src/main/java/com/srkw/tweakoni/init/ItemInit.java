package com.srkw.tweakoni.init;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.item.ItemRotator;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.model.ModelLoader;
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
