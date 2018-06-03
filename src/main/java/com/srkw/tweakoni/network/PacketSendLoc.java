package com.srkw.tweakoni.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
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

    public PacketSendLoc() {
        //noinspection MethodCallSideOnly
        RayTraceResult mouseOver = getMinecraft().objectMouseOver;
        blockPos = mouseOver.getBlockPos();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
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
                Block blockBelow = world.getBlockState(message.blockPos.down()).getBlock();

                if (blockBelow == Blocks.AIR || blockBelow == Blocks.WATER || blockBelow == Blocks.LAVA) {

                    Item item = playerEntity.getHeldItem(EnumHand.MAIN_HAND).getItem();

                    if (!playerEntity.isCreative()) {
                        playerEntity.getHeldItem(EnumHand.MAIN_HAND).shrink(1);
                    }

                    BlockPos pos = message.blockPos.down();
                    int meta = playerEntity.getHeldItemMainhand().getMetadata();
                    world.setBlockState(pos, Block.getBlockFromItem(item).getStateFromMeta(meta));                   

                    SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, playerEntity);
                    world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundtype.getPlaceSound(), SoundCategory.BLOCKS, soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);

                }
            }
        }
    }
}
