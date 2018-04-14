package com.srkw.tweakoni.events;

import com.srkw.tweakoni.block.JukeBoxProvider;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.BlockMagma;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.srkw.tweakoni.Tweakoni.MOD_ID;

@Mod.EventBusSubscriber()
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
    public static void onInteract(PlayerInteractEvent.EntityInteractSpecific event)
    {
        if (!event.getEntity().getEntityWorld().isRemote) {
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