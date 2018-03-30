package com.srkw.tweakoni.utils.handlers;

import org.lwjgl.input.Keyboard;

import com.srkw.tweakoni.init.BlockInit;
import com.srkw.tweakoni.init.ItemInit;
import com.srkw.tweakoni.utils.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

	public static void preInitRegistries() {

		
	}

	public static void initRegistries() {
		
		registerKeyBindings();
		
	}

	public static void postInitRegistries() {

		
	}
	
	public static void registerKeyBindings () {
		
		KeyBinding[] keyBindings = new KeyBinding[2]; 
		 
		keyBindings[0] = new KeyBinding("key.toggle.shift", Keyboard.KEY_L, "key.tweaktoni.category");
		  
		for (int i = 0; i < keyBindings.length; ++i) {
		    ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
		
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));

	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ItemInit.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
}
