package com.srkw.tweakoni.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.client.Minecraft.getMinecraft;

public class PacketSendLoc implements IMessage {
    private BlockPos blockPos;

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }

    public PacketSendLoc() {
        //noinspection MethodCallSideOnly
        RayTraceResult mouseOver = getMinecraft().objectMouseOver;
        blockPos = mouseOver.getBlockPos();
    }

    public static class Handler implements IMessageHandler<PacketSendLoc, IMessage> {
        @Override
        public IMessage onMessage(PacketSendLoc message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendLoc message, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            if (world.isBlockLoaded(message.blockPos)) {
                Block block = world.getBlockState(message.blockPos).getBlock();
                Item item = playerEntity.getHeldItem(EnumHand.MAIN_HAND).getItem();
                playerEntity.getHeldItem(EnumHand.MAIN_HAND).shrink(1);
                BlockPos pos = message.blockPos.down();
                world.setBlockState(pos,Block.getBlockFromItem(item).getDefaultState());
            }
        }
    }
}
