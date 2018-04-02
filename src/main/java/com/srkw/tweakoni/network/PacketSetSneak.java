package com.srkw.tweakoni.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.client.Minecraft.getMinecraft;

public class PacketSetSneak implements IMessage {
    private BlockPos blockPos;

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }

    public PacketSetSneak() {
        //noinspection MethodCallSideOnly
        RayTraceResult mouseOver = getMinecraft().objectMouseOver;
        blockPos = mouseOver.getBlockPos();
    }

    public static class Handler implements IMessageHandler<PacketSetSneak, IMessage> {
        private void handle(PacketSetSneak message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
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
