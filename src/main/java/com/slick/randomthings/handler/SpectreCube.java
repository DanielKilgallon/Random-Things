package com.slick.randomthings.handler;

import java.util.UUID;

import com.slick.randomthings.block.ModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class SpectreCube {
    public UUID owner;
    public int height;
    public BlockPos playerSpawnPosition;

    private static final int CUBE_SIZE = 16;
    private static final int MAX_HEIGHT = 100;
    private static final int OFFSET = 32;

    public SpectreCube(UUID owner, int cubeNumber) {
        this.owner = owner;
        this.height = 3;
        this.playerSpawnPosition = new BlockPos(cubeNumber * CUBE_SIZE + 8, 1, cubeNumber * CUBE_SIZE + 8);
    }

    private SpectreCube(UUID owner, int height, BlockPos playerSpawnPosition) {
        this.owner = owner;
        this.height = height;
        this.playerSpawnPosition = playerSpawnPosition;
    }

    public int addHeight(Level level, int add) {
        int oldHeight = height;
        int newHeight = height + add;
        if (newHeight > MAX_HEIGHT) {
            newHeight = MAX_HEIGHT;
        }
        for (int i = height; i < newHeight; i++) {
            generateNewSlice(level, i);
        }
        height = newHeight;
        return newHeight - oldHeight;
    }

    private void generateNewSlice(Level level, int curHeight) {
        BlockPos corner1 = new BlockPos(playerSpawnPosition.getX() - 8, curHeight, playerSpawnPosition.getZ() - 8);
        BlockPos corner2 = new BlockPos(playerSpawnPosition.getX() + 7, curHeight, playerSpawnPosition.getZ() + 7);

        int minX = Math.min(corner1.getX(), corner2.getX());
        int minZ = Math.min(corner1.getZ(), corner2.getZ());

        int maxX = Math.max(corner1.getX(), corner2.getX());
        int maxZ = Math.max(corner1.getZ(), corner2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                level.setBlockAndUpdate(new BlockPos(x, curHeight, z), Blocks.AIR.defaultBlockState());
            }
        }
    }

    public void createBaseCube(ServerLevel level) {
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
                        level.setBlockAndUpdate(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
        BlockPos coreSpot = new BlockPos(playerSpawnPosition.getX(), 0, playerSpawnPosition.getZ());
        level.setBlockAndUpdate(coreSpot, ModBlocks.SPECTRE_CORE.defaultBlockState());
    }

    public CompoundTag save(CompoundTag compoundTag) {
        compoundTag.putString("owner", owner.toString());
        compoundTag.putInt("height", height);
        compoundTag.putInt("positionX", playerSpawnPosition.getX());
        compoundTag.putInt("positionZ", playerSpawnPosition.getZ());

        return compoundTag;
    }

    public static SpectreCube load(CompoundTag compoundTag) {
        UUID owner = UUID.fromString(compoundTag.getString("owner"));
        int height = compoundTag.getInt("height");

        int x = compoundTag.getInt("positionX");
        int z = compoundTag.getInt("positionZ");
        BlockPos playerSpawnPosition = new BlockPos(x, 1, z);
        return new SpectreCube(owner, height, playerSpawnPosition);
    }
}
