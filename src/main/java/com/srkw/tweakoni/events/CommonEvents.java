package com.srkw.tweakoni.events;

import com.srkw.tweakoni.block.JukeBoxProvider;
import com.srkw.tweakoni.block.bed.BlockBed;
import com.srkw.tweakoni.block.bed.GuiSleepMP;

import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.BlockMagma;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.SleepResult;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

import static com.srkw.tweakoni.Tweakoni.MOD_ID;

@Mod.EventBusSubscriber()
public class CommonEvents {
	
	@SubscribeEvent
	public static void sleepEvent(LivingEvent.LivingUpdateEvent event) {
		
		if(event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().getEntityWorld().isRemote) {
			
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			
			if(player.isPlayerSleeping()) {
				if(!(Minecraft.getMinecraft().currentScreen instanceof GuiSleepMP)) {
					Minecraft.getMinecraft().displayGuiScreen(null);
					Minecraft.getMinecraft().displayGuiScreen(new com.srkw.tweakoni.block.bed.GuiSleepMP());	
				}
			}
			
			if(!player.isPlayerSleeping()) {
				if(Minecraft.getMinecraft().currentScreen instanceof com.srkw.tweakoni.block.bed.GuiSleepMP) {
					Minecraft.getMinecraft().displayGuiScreen(null);	
				}
			}
			
		}
		
	}

    @SubscribeEvent
    public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {

        if (event.getTarget() instanceof EntityVillager && event.getEntity() instanceof EntityPlayer && !event.getWorld().isRemote) {

            EntityVillager villager = (EntityVillager) event.getTarget();
            EntityPlayer player = (EntityPlayer) event.getEntity();
            EnumHand hand = player.getActiveHand();

            if (villager.getLeashed() && villager.getLeashHolder() == player) {
                villager.clearLeashed(true, !player.capabilities.isCreativeMode);
                event.setCanceled(true);
                return;
            } else {
                ItemStack itemstack = player.getHeldItem(hand);

                if (itemstack.getItem() == Items.LEAD) {
                    villager.setLeashHolder(player, true);
                    itemstack.shrink(1);
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntity().getEntityWorld().isRemote) {
            BlockPos pos = event.getEntity().getPosition();
            if (event.getEntity().getEntityWorld().getBlockState(pos.down()).getBlock() instanceof BlockMagma) {
                event.getEntity().attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onAttach(AttachCapabilitiesEvent<TileEntity> event) {
        if (event.getObject() instanceof TileEntityJukebox) {
            event.addCapability(new ResourceLocation("srkw", "itemhandler"), new JukeBoxProvider((TileEntityJukebox) event.getObject()));
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MOD_ID))
            ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
    }
}