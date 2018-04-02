package com.srkw.tweakoni.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.srkw.tweakoni.utils.handlers.RegistryHandler.SHIFT_HANDLER_CAPABILITY;

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
            playerEntity.getCapability(SHIFT_HANDLER_CAPABILITY, EnumFacing.DOWN).setShift(false);
        }

        @Override
        public IMessage onMessage(PacketSetSneak message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }
    }
}
