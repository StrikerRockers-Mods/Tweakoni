package com.srkw.tweakoni.events;

import com.srkw.tweakoni.network.PacketHandler;
import com.srkw.tweakoni.network.PacketSendLoc;
import com.srkw.tweakoni.network.PacketSetSneak;
import com.srkw.tweakoni.proxy.ClientProxy;
import com.srkw.tweakoni.utils.RayTrace;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@SuppressWarnings("MethodCallSideOnly")
@Mod.EventBusSubscriber
public class ClientEvents {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().isRemote) {
            Item item = event.getItemStack().getItem();
            if (item instanceof ItemBlock && ClientProxy.block_below.isKeyDown()) {
                RayTraceResult result = RayTrace.rayTrace(event.getWorld(), event.getEntityPlayer(), false);
                if (result.sideHit == EnumFacing.UP) {
                    PacketHandler.INSTANCE.sendToServer(new PacketSendLoc());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (ClientProxy.d_shift.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketSetSneak());
        }
    }

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent.Post event) {
        ScaledResolution resolution = event.getResolution();
        int height = resolution.getScaledHeight() / 100;
        int width = resolution.getScaledWidth() / 100;

    }
}
