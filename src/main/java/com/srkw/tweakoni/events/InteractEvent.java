package com.srkw.tweakoni.events;

import com.srkw.tweakoni.capabilities.Provider;
import net.minecraft.block.BlockMagma;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.srkw.tweakoni.utils.handlers.RegistryHandler.SHIFT_HANDLER_CAPABILITY;

@Mod.EventBusSubscriber
public class InteractEvent {


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
    public static void onInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof EntityVillager) {
            if (event.getEntityPlayer().getHeldItemMainhand().getItem() == Items.LEAD) {
                EntityLiving entity = (EntityLiving) event.getTarget();
                if (!entity.getLeashed()) {
                    entity.setLeashHolder(event.getEntityPlayer(), true);
                    event.getEntityPlayer().getHeldItemMainhand().shrink(1);
                } else entity.clearLeashed(true, true);
            }
        }
    }

    @SubscribeEvent
    public static void onRegistrar(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer)
            event.addCapability(new ResourceLocation("srkw", "shift"), new Provider());
    }

    @SubscribeEvent
    public static void onUpdate(LivingEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (player.getCapability(SHIFT_HANDLER_CAPABILITY, null).getShift() == true && !player.isSneaking()) {
                player.setSneaking(true);
            } else player.setSneaking(false);
        }
    }
}
