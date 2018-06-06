package com.srkw.tweakoni.potion;

import com.srkw.tweakoni.init.PotionInit;

import net.minecraft.init.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;

public class PotionStrongHaste extends PotionType {

	public PotionStrongHaste(String name, PotionEffect effect) {
		super(name, effect);
		setRegistryName("minecraft", name);
		PotionHelper.addMix(PotionInit.HASTE, Ingredient.fromItem(Items.GLOWSTONE_DUST), this);
	}
	
}
