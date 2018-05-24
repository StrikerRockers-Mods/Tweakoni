package com.srkw.tweakoni.network;

import com.srkw.tweakoni.block.hopper.TileEntityHopper;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by StrikerRocker on 24/5/18.
 */
public class PacketUpdatePriority implements IMessage {

    private BlockPos blockPos;

    public PacketUpdatePriority() {
    }

    public PacketUpdatePriority(BlockPos hopperPOS) {
        blockPos = hopperPOS;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketUpdatePriority, IMessage> {
        @Override
        public IMessage onMessage(PacketUpdatePriority message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketUpdatePriority message, MessageContext ctx) {
            BlockPos pos = message.blockPos;
            TileEntityHopper hopper = (TileEntityHopper) ctx.getServerHandler().player.world.getTileEntity(pos);
            hopper.setIsNew(!hopper.getIsNew());
        }
    }
}
