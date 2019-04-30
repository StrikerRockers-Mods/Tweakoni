package io.github.strikerrocker.tweakoni.blocker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SpawnBlockerBlock extends Block implements BlockEntityProvider {

    public SpawnBlockerBlock(Settings var1) {
        super(var1);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new BlockerBlockEntity();
    }
}
