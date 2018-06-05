package com.srkw.tweakoni.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSleepSpawn implements IMessage {

    private BlockPos spawnpoint;
    private int dimension;

    public PacketSleepSpawn() {
    }

    public PacketSleepSpawn(BlockPos spawnpoint, int dimension) {
        this.spawnpoint = spawnpoint;
        this.dimension = dimension;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	spawnpoint = BlockPos.fromLong(buf.readLong());
    	dimension = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(spawnpoint.toLong());
        buf.writeInt(dimension);
    }

    public static class Handler implements IMessageHandler<PacketSleepSpawn, IMessage> {

		@Override
		public IMessage onMessage(PacketSleepSpawn message, MessageContext ctx) {
			
			ctx.getServerHandler().player.wakeUpPlayer(true, true, false);
			ctx.getServerHandler().player.getEntityWorld().setWorldTime(1000);
			
			return null;
		}
    }
}
