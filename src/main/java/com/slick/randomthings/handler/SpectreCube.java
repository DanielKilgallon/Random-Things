package com.slick.randomthings.handler;

import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class SpectreCube {
    public UUID owner;
    public int height;
	public BlockPos playerSpawnPosition;

    private static final int CUBE_SIZE = 16;
    private static final int MAX_HEIGHT = 99;
    private static final int OFFSET = 32;

    public SpectreCube(UUID owner, Level level, int cubeNumber) {
    	this.owner = owner;
        this.height = 2;
        this.playerSpawnPosition = new BlockPos(cubeNumber * CUBE_SIZE + 8, 1, cubeNumber * CUBE_SIZE + 8);
    }

    // private void changeHeight(Level level, int newHeight)
	// {
	// 	BlockPos corner = new BlockPos(position * 16, 0, 0);

	// 	BlockPos pos1 = corner;
	// 	BlockPos pos2 = corner.add(15, height + 1, 15);

	// 	generateCube(worldObj, pos1, pos2, Blocks.AIR.getDefaultState(), 2);
	// 	this.height = newHeight;
	// 	generate(worldObj);

	// 	handler.markDirty();
	// }

    public void createCube(Level level) {
        BlockPos corner1 = new BlockPos(playerSpawnPosition.getX() - 8, 1, playerSpawnPosition.getZ() - 8);
        BlockPos corner2 = new BlockPos(playerSpawnPosition.getX() + 7, 2, playerSpawnPosition.getZ() + 7);

        int minX = Math.min(corner1.getX(), corner2.getX());
        int minY = Math.min(corner1.getY(), corner2.getY());
        int minZ = Math.min(corner1.getZ(), corner2.getZ());

        int maxX = Math.max(corner1.getX(), corner2.getX());
        int maxY = Math.max(corner1.getY(), corner2.getY());
        int maxZ = Math.max(corner1.getZ(), corner2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (x == minX || y == minY || z == minZ || x == maxX || y == maxY || z == maxZ) {
                        // System.out.println("Setting block at " + x + " " + y + " " + z);
                        level.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    public CompoundTag save(CompoundTag compoundTag) {
		compoundTag.putString("owner", owner.toString());
        compoundTag.putInt("height", height);
		compoundTag.putInt("positionX", playerSpawnPosition.getX());
        compoundTag.putInt("positionZ", playerSpawnPosition.getZ());
        
        return compoundTag;
	}

	public void load(CompoundTag compoundTag) {
		this.owner = UUID.fromString(compoundTag.getString("owner"));
		this.height = compoundTag.getInt("height");

        int x = compoundTag.getInt("positionX");
        int z = compoundTag.getInt("positionZ");
        this.playerSpawnPosition = new BlockPos(x, 1, z);
	}
}
