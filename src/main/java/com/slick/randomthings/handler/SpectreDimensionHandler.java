package com.slick.randomthings.handler;

import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.UUID;

import com.slick.randomthings.RandomThingsMod;

//TODO: Teleport Player back to overworld when leaving spectre dimension
//BUG: Spectre Dimension data is held over between two worlds if minecraft instance is not fully closed before making another world
public class SpectreDimensionHandler extends SavedData {

    public static final String ID = "SpectreHandler";
    private HashMap<UUID, SpectreCube> cubes;
    private int cubeNumber;
    private static SpectreDimensionHandler instance;

    public SpectreDimensionHandler(int i) {
        cubes = new HashMap<>();
        cubeNumber = i;
    }

    public static SpectreDimensionHandler getInstance() {
        if (instance == null) {
            instance = new SpectreDimensionHandler(0);
        }
        return instance;
    }

    public void teleportPlayerToSpectreCube(Level level, Player player) {
        // tries to get the spectre dimension for the world
        MinecraftServer minecraftServer = level.getServer();
        ServerLevel serverSpectreLevel = null;
        if (minecraftServer != null) {
            serverSpectreLevel = minecraftServer.getLevel(RandomThingsMod.SPECTRE_DIMENSION);
        }

        if (serverSpectreLevel != null) {
            super.setDirty();
            // Save Old Position / Dimension
            // CompoundTag compoundTag = player.getPersistentData();
            // compoundTag.putDouble("spectrePosX", player.getX());
            // compoundTag.putDouble("spectrePosY", player.getY());
            // compoundTag.putDouble("spectrePosZ", player.getZ());
            // compoundTag.putInt("spectreDimension", 1);

            if (cubes.containsKey(player.getUUID())) {
                System.out.println("old player found");
                SpectreCube cube = cubes.get(player.getUUID());
                player.changeDimension(serverSpectreLevel, new SimpleTeleporter(cube.playerSpawnPosition));
            } else {
                System.out.println("new player found");
                SpectreCube cube = new SpectreCube(player.getUUID(), cubeNumber);
                cube.createBaseCube(serverSpectreLevel);
                cubes.put(player.getUUID(), cube);
                cubeNumber++;
                player.changeDimension(serverSpectreLevel, new SimpleTeleporter(cube.playerSpawnPosition));
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        compoundTag.putInt("cubeNumber", cubeNumber);
        for (SpectreCube cube : cubes.values()) {
            compoundTag.put(cube.owner.toString(), cube.save(new CompoundTag()));
        }
        return compoundTag;
    }

    public static SpectreDimensionHandler load(CompoundTag compoundTag) {
        SpectreDimensionHandler newHandler = SpectreDimensionHandler.getInstance();
        newHandler.cubeNumber = compoundTag.getInt("cubeNumber");
        newHandler.cubes.clear();
        for (String key : compoundTag.getAllKeys()) {
            if (key.equals("cubeNumber")) {
                continue;
            }
            newHandler.cubes.put(UUID.fromString(key), SpectreCube.load(compoundTag.getCompound(key)));
        }
        return newHandler;
    }
}
