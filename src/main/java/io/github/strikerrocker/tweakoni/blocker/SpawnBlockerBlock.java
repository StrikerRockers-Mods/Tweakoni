package io.github.strikerrocker.tweakoni.blocker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SpawnBlockerBlock extends Block implements BlockEntityProvider {

    public SpawnBlockerBlock(Settings var1) {
        super(var1);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new BlockerBlockEntity();
    }

    @Override
    public void onPlaced(World var1, BlockPos var2, BlockState var3, LivingEntity var4, ItemStack var5) {
        int x1 = var1.getChunk(var2).getPos().getXStart() - 16;
        int z1 = var1.getChunk(var2).getPos().getZStart() - 16;

        int x2 = var1.getChunk(var2).getPos().getXEnd() + 16;
        int z2 = var1.getChunk(var2).getPos().getZEnd() + 16;
        BoundingBox boundingBox = new BoundingBox(new BlockPos(x1, 0, z1), new BlockPos(x2, 256, z2));
    }

    @Override
    public boolean allowsSpawning(BlockState blockState_1, Entity entity_1) {
        return super.allowsSpawning(blockState_1, entity_1);
    }
}
