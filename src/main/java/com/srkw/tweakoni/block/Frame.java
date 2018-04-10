package com.srkw.tweakoni.block;

import com.srkw.tweakoni.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import scala.Console;

public class Frame extends EntityItemFrame {

	public Frame() {
		
		super(Minecraft.getMinecraft().world);
		
	}
	
	@Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        Console.println("RClicked!");
		ItemStack itemstack = player.getHeldItem(hand);

        if (!this.world.isRemote)
        {
            if (this.getDisplayedItem().isEmpty())
            {
                if (!itemstack.isEmpty())
                {
                    this.setDisplayedItem(itemstack);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }
                }
            }
            else
            {
                if(ClientProxy.item_frame.isKeyDown()) {
            	this.playSound(SoundEvents.ENTITY_ITEMFRAME_ROTATE_ITEM, 1.0F, 1.0F);
                this.setItemRotation(this.getRotation() + 1);
                }
            }
        }

        return true;
    }

}
