package com.srkw.tweakoni;

import com.srkw.tweakoni.proxy.CommonProxy;
import com.srkw.tweakoni.utils.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.srkw.tweakoni.Tweakoni.*;

@Mod(modid = MOD_ID, name = NAME, version = VERSION)
public class Tweakoni {

    public static final String MOD_ID = "tweakoni";
    public static final String NAME = "Mumbo Tweakoni";
    public static final String VERSION = "1.1";
    public static final String CLIENT = "com.srkw.tweakoni.proxy.ClientProxy";
    public static final String COMMON = "com.srkw.tweakoni.proxy.CommonProxy";

    @Instance
    public static Tweakoni instance;

    @SidedProxy(clientSide = CLIENT, serverSide = COMMON)
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries();
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries();
        proxy.registerKeyBindings();
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        RegistryHandler.postInitRegistries();
    }
}
