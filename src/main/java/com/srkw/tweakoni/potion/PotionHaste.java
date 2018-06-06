package com.srkw.tweakoni.potion;

import net.minecraft.init.Blocks;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;

public class PotionHaste extends PotionType {
	
	public PotionHaste(String name, PotionEffect effect) {
		super(name, effect);
		setRegistryName("minecraft", name);
		PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromItem(Item.getItemFromBlock(Blocks.GOLD_BLOCK)), this);
	}
	
}
