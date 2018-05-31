package com.srkw.tweakoni.potions;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;

/**
 * Created by StrikerRocker on 31/5/18.
 */
public class PotionHaste extends Potion {
    public PotionHaste() {
        super(false, 2552550);
        setPotionName("effect.digSpeed");
        setIconIndex(2, 0);
        setEffectiveness(1.5D);
        setBeneficial();
        setRegistryName("minecraft:haste");
        registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.10000000149011612D, 2);
    }

}
