package com.srkw.tweakoni.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetSneak implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public PacketSetSneak() {
    }

    public static class Handler implements IMessageHandler<PacketSetSneak, IMessage> {
        private void handle(PacketSetSneak message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            if (playerEntity.isSneaking()) {
                playerEntity.setSneaking(false);
            } else playerEntity.setSneaking(true);
        }

        @Override
        public IMessage onMessage(PacketSetSneak message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }
    }
}
