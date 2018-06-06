package com.srkw.tweakoni.init;

import com.srkw.tweakoni.potion.PotionHaste;
import com.srkw.tweakoni.potion.PotionStrongHaste;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.registries.IForgeRegistry;

public class PotionInit {

	 public static final PotionHaste HASTE = new PotionHaste("haste", new PotionEffect(MobEffects.HASTE, 4000, 0, true, true));
	 public static final PotionStrongHaste STRONG_HASTE = new PotionStrongHaste("strong.haste", new PotionEffect(MobEffects.HASTE, 4000, 1, true, true));

	 public static void register(IForgeRegistry<PotionType> registry) {
		 registry.registerAll(
				 HASTE, STRONG_HASTE
				 );
	 }
}
