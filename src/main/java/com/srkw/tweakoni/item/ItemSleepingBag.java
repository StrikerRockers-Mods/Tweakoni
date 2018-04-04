package com.srkw.tweakoni.item;

import com.srkw.tweakoni.Tweakoni;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketUseBed;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemSleepingBag extends Item {

    public ItemSleepingBag(String name) {
        super();
        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxStackSize(1);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (player.isRiding()) player.dismountRidingEntity();

            ObfuscationReflectionHelper.setPrivateValue(EntityPlayer.class, player, true, "sleeping", "field_71083_bS");
            ObfuscationReflectionHelper.setPrivateValue(EntityPlayer.class, player, 0, "sleepTimer", "field_71076_b");

            AxisAlignedBB box = worldIn.getBlockState(pos).getCollisionBoundingBox(worldIn, pos);
            if (box == null || box.maxY < 1) pos = pos.up();

            player.bedLocation = pos;

            player.motionX = player.motionZ = player.motionY = 0;
            worldIn.updateAllPlayersSleepingFlag();

            SPacketUseBed sleepPacket = new SPacketUseBed(player, pos);

            EntityPlayerMP player_diff = (EntityPlayerMP) player;

            player_diff.getServerWorld().getEntityTracker().sendToTrackingAndSelf(player, sleepPacket);
            player_diff.connection.sendPacket(sleepPacket);
        }

        return EnumActionResult.PASS;
    }


    public void registerItemModel() {
        Tweakoni.proxy.registerItemRenderer(this, 0, "sleeping_bag");
    }

}
