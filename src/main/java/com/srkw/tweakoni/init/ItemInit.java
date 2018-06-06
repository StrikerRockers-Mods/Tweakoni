package com.srkw.tweakoni.init;

import com.srkw.tweakoni.Tweakoni;
import com.srkw.tweakoni.block.minecraft.bed.ItemBed;
import com.srkw.tweakoni.block.minecraft.redstone.ItemRedstone;
import com.srkw.tweakoni.item.ItemRotator;
import com.srkw.tweakoni.potion.PotionHaste;
import com.srkw.tweakoni.potion.PotionStrongHaste;

import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {

    public static final ItemRotator ROTATOR = new ItemRotator("rotator");
    public static final ItemBed BED = new ItemBed("bed");
    public static final ItemRedstone REDSTONE = new ItemRedstone("redstone");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                ROTATOR, BED, REDSTONE
        );
    }

    public static void registerModels() {
        ROTATOR.registerItemModel();
        BED.registerItemModel();
        Tweakoni.proxy.registerItemRenderer(REDSTONE, 0, "redstone");
    }
}
