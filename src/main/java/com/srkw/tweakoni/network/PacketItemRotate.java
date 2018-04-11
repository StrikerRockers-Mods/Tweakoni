package com.srkw.tweakoni.network;

import com.srkw.tweakoni.utils.RayTrace;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketItemRotate implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketItemRotate, IMessage> {
        @Override
        public IMessage onMessage(PacketItemRotate message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketItemRotate message, MessageContext ctx) {
            RayTraceResult result = RayTrace.rayTrace(ctx.getServerHandler().player.getEntityWorld(), ctx.getServerHandler().player, false);
            if (result.entityHit instanceof EntityItemFrame) {
                EntityItemFrame frame = (EntityItemFrame) result.entityHit;
                frame.setItemRotation(frame.getRotation() + 1);
            }
        }
    }
}