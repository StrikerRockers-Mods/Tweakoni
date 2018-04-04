package com.srkw.tweakoni.events;

import net.minecraft.block.BlockMagma;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonEvents {

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
}