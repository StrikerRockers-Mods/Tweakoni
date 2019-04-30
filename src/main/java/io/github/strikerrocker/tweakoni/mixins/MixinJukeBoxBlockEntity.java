package io.github.strikerrocker.tweakoni.mixins;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(JukeboxBlockEntity.class)
public abstract class MixinJukeBoxBlockEntity implements Inventory {
    @Shadow
    private ItemStack record;


    @Shadow
    public abstract void setRecord(ItemStack var1);

    @Override
    public int getInvSize() {
        return 1;
    }

    @Override
    public boolean isInvEmpty() {
        return record == ItemStack.EMPTY;
    }

    @Override
    public ItemStack getInvStack(int var1) {
        return var1 == 0 ? record : ItemStack.EMPTY;
    }

    @Override
    public ItemStack takeInvStack(int var1, int var2) {
        if (var1 == 0 && !this.record.isEmpty()) {
            ItemStack var3 = this.record;
            setRecord(ItemStack.EMPTY);
            onRemoved();
            return var3;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeInvStack(int var1) {
        if (var1 == 0) {
            ItemStack var2 = this.record;
            setRecord(ItemStack.EMPTY);
            onRemoved();
            return var2;
        } else {
            return ItemStack.EMPTY;
        }
    }

    private void onRemoved() {
        BlockEntity blockEntity = (BlockEntity) (Object) this;
        ;
        blockEntity.getWorld().playLevelEvent(1010, blockEntity.getPos(), 0);
    }

    @Override
    public void setInvStack(int var1, ItemStack var2) {
        if (var1 == 0 && var2.getItem() instanceof MusicDiscItem) {
            setRecord(var2);
            if (var2 == ItemStack.EMPTY) {
                onRemoved();
            }
        }
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity var1) {
        return false;
    }
}
