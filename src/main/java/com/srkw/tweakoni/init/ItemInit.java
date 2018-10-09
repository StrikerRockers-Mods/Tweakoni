package com.srkw.tweakoni.init;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.block.minecraft.bed.ItemBed;
import com.srkw.tweakoni.item.ItemRotator;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {

    public static final ItemRotator ROTATOR = new ItemRotator("rotator");
    public static final ItemBed BED = new ItemBed("bed");
    //public static final ItemRedstone REDSTONE = new ItemRedstone("redstone");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                ROTATOR, BED
        );
    }

    public static void registerModels() {
        Tweakoni.proxy.registerItemRenderer(ROTATOR, 0, "rotator");
        BED.registerItemModel();
        //Tweakoni.proxy.registerItemRenderer(REDSTONE, 0, "redstone");
    }
}
