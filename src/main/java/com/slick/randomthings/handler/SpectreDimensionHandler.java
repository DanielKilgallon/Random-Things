package com.slick.randomthings.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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

            // Teleport Player to Spectre Dimension
            System.out.println("Teleporting player to spectre dimension");
            if (cubes.containsKey(player.getUUID())) {
                BlockPos pos = cubes.get(player.getUUID());
                player.changeDimension(spectreLevel, new SimpleTeleporter(pos));
            } else {
                BlockPos pos = createNextSpectreCube(level);
                cubes.put(player.getUUID(), pos);
                player.changeDimension(spectreLevel, new SimpleTeleporter(pos));
            }
        }
    }

    private BlockPos createNextSpectreCube(Level level) {
        int X = positionCounter * 16;
        int Y = positionCounter * 16;
        int Z = 54;
        positionCounter++;
        System.out.println("Creating new spectre cube at " + X + " " + Y + " " + Z);
        return new BlockPos(X, Y, Z);
    }
}
