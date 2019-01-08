package io.github.strikerrocker.tweakoni.blocker;

import io.github.strikerrocker.tweakoni.Tweakoni;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;

import java.util.List;

public class BlockerBlockEntity extends BlockEntity implements Tickable {
    public BlockerBlockEntity() {
        super(Tweakoni.BLOCKER);
    }

    @Override
    public void tick() {
        int tick = 0;
        if (tick++ % 20 == 0) {
            int x1 = world.getChunk(pos).getPos().getXStart() - 16;
            int z1 = world.getChunk(pos).getPos().getZStart() - 16;

            int x2 = world.getChunk(pos).getPos().getXEnd() + 16;
            int z2 = world.getChunk(pos).getPos().getZEnd() + 16;

            BoundingBox boundingBox = new BoundingBox(new BlockPos(x1, 0, z1), new BlockPos(x2, 256, z2));

            List<Entity> list = world.getEntities(MobEntity.class, boundingBox, (entity -> entity instanceof MobEntity && entity.isValid()));
            for (Entity entity : list) {
                entity.kill();
            }
        }
    }
}