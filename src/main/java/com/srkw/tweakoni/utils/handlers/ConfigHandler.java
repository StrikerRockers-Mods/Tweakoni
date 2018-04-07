package com.srkw.tweakoni.utils.handlers;

import com.srkw.tweakoni.Tweakoni;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.RangeInt;

@Config(modid = Tweakoni.MOD_ID)
public class ConfigHandler {
    @RangeInt(max = 2, min = 0)
    @Config.Comment("1 for Top-left" +
            "2 for Bottom-left")
    public static int elytraPos = 1;
}
