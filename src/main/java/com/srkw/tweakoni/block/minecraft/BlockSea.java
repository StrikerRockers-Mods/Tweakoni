package com.srkw.tweakoni.block.minecraft;

import net.minecraft.block.BlockSeaLantern;
import net.minecraft.block.material.Material;

public class BlockSea extends BlockSeaLantern {
    public BlockSea(Material materialIn) {
        super(materialIn);
        this.setRegistryName("minecraft", "sea_lantern");
        this.setUnlocalizedName("sea_lantern");
    }
}
