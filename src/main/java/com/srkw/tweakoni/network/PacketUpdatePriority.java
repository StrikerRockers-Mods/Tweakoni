package com.srkw.tweakoni.network;

import com.srkw.tweakoni.block.minecraft.hopper.TileEntityHopper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        	
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	WorldServer world = player.getServerWorld();
        	
        	world.addScheduledTask(() -> {  
        		if(world.getTileEntity(message.blockPos) instanceof TileEntityHopper) {
        			TileEntityHopper hopper = (TileEntityHopper)world.getTileEntity(message.blockPos);
                	hopper.setIsNew(!hopper.getIsNew());
        		}
        	});
        	
            return null;
        }
    }
}
