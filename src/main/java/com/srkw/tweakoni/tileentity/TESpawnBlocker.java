package com.srkw.tweakoni.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class TESpawnBlocker extends TileEntity implements ITickable {
	
	@Override
	public void update() {
	
		int x1 = world.getChunkFromBlockCoords(pos).getPos().getXStart();
		int y1 = 0;
		int z1 = world.getChunkFromBlockCoords(pos).getPos().getZStart();
		
		int x2 = world.getChunkFromBlockCoords(pos).getPos().getXEnd();
		int y2 = 256;
		int z2 = world.getChunkFromBlockCoords(pos).getPos().getXEnd();
		
		List<EntityZombie> list = new ArrayList<>();
		
		AxisAlignedBB AABB = new AxisAlignedBB(new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2));

		world.getChunkFromBlockCoords(pos).getEntitiesOfTypeWithinAABB(EntityZombie.class, AABB, list, null);
		
		for(EntityZombie zombie : list) zombie.setDead();
		
		//world.getChunkFromBlockCoords(pos).removeEntity(new EntityZombie(world));
		System.out.println("Update method being called");
		
	}

}
