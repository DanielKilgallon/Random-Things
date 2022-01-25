package com.slick.randomthings.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.UUID;

import com.slick.randomthings.RandomThingsMod;

public class SpectreDimensionHandler {

    public static final String ID = "SpectreHandler";
    private HashMap<UUID, BlockPos> cubes;
    private int positionCounter;
    private static SpectreDimensionHandler instance;

    public SpectreDimensionHandler(int i) {
        cubes = new HashMap<>();
        positionCounter = i;
    }

    public static SpectreDimensionHandler getInstance() {
        // world.getPerWorldStorage().setData(ID, handler);
        if (instance == null) {
            instance = new SpectreDimensionHandler(0);
        }
        return instance;
    }

    public void teleportPlayerToSpectreCube(Level level, Player player) {
        // tries to get the spectre dimension for the world
        MinecraftServer minecraftServer = level.getServer();
        ServerLevel spectreLevel = null;
        if (minecraftServer != null) {
            spectreLevel = minecraftServer.getLevel(RandomThingsMod.SPECTRE_DIMENSION);
        }

        if (spectreLevel != null) {

            // Save Old Position / Dimension
            // CompoundTag compoundTag = player.getPersistentData();
            // compoundTag.putDouble("spectrePosX", player.getX());
            // compoundTag.putDouble("spectrePosY", player.getY());
            // compoundTag.putDouble("spectrePosZ", player.getZ());
            // compoundTag.putInt("spectreDimension", 1);
            // player.setPersistentData(compoundTag);

            if (cubes.containsKey(player.getUUID())) {
                BlockPos pos = cubes.get(player.getUUID());
                player.changeDimension(spectreLevel, new SimpleTeleporter(pos));
            } else {
                BlockPos pos = createNextSpectreCube(spectreLevel);
                cubes.put(player.getUUID(), pos);
                player.changeDimension(spectreLevel, new SimpleTeleporter(pos));
            }
        }
    }

    private BlockPos createNextSpectreCube(Level level) {
        BlockPos uniquePlayerSpawn = new BlockPos(positionCounter * 16 + .5, 1, positionCounter * 16 + .5);
        positionCounter++;
        System.out.println("Creating new spectre cube at " + uniquePlayerSpawn);
    
        createSpaceForCube(level, uniquePlayerSpawn);
        return uniquePlayerSpawn;
    }

    private void createSpaceForCube(Level level, BlockPos uniquePlayerSpawn) {
        BlockPos corner1 = new BlockPos(uniquePlayerSpawn.getX(), 1, uniquePlayerSpawn.getZ());
        BlockPos corner2 = new BlockPos(uniquePlayerSpawn.getX()+15, 2, uniquePlayerSpawn.getZ()+15);

        int minX = Math.min(corner1.getX(), corner2.getX());
		int minY = Math.min(corner1.getY(), corner2.getY());
		int minZ = Math.min(corner1.getZ(), corner2.getZ());

		int maxX = Math.max(corner1.getX(), corner2.getX());
		int maxY = Math.max(corner1.getY(), corner2.getY());
		int maxZ = Math.max(corner1.getZ(), corner2.getZ());

		for (int x = minX; x <= maxX; x++)
		{
			for (int y = minY; y <= maxY; y++)
			{
				for (int z = minZ; z <= maxZ; z++)
				{
					if (x == minX || y == minY || z == minZ || x == maxX || y == maxY || z == maxZ)
					{
						// worldObj.setBlockState(new BlockPos(x, y, z), state, flag);
                        // System.out.println("Setting block at " + x + " " + y + " " + z);
                        level.setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					}
				}
			}
		}
    }
}
