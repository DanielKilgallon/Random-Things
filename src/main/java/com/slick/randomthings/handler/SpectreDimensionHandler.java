package com.slick.randomthings.handler;

import net.minecraft.commands.arguments.NbtTagArgument;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import com.slick.randomthings.RandomThingsMod;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WorldData;
import net.minecraftforge.common.util.INBTSerializable;

public class SpectreDimensionHandler extends SavedData {

    public static final String ID = "SpectreHandler";
    private HashMap<UUID, SpectreCube> cubes;
    private int cubeNumber;
    private static SpectreDimensionHandler instance;
    private Level spectreLevel;

    public SpectreDimensionHandler(int i) {
        cubes = new HashMap<>();
        cubeNumber = i;
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
        this.spectreLevel = level;
        MinecraftServer minecraftServer = level.getServer();
        ServerLevel serverSpectreLevel = null;
        if (minecraftServer != null) {
            serverSpectreLevel = minecraftServer.getLevel(RandomThingsMod.SPECTRE_DIMENSION);
        }

        if (serverSpectreLevel != null) {
            // Save Old Position / Dimension
            // CompoundTag compoundTag = player.getPersistentData();
            // compoundTag.putDouble("spectrePosX", player.getX());
            // compoundTag.putDouble("spectrePosY", player.getY());
            // compoundTag.putDouble("spectrePosZ", player.getZ());
            // compoundTag.putInt("spectreDimension", 1);

            // if (cubes.containsKey(player.getUUID())) {
            if (false) {
                SpectreCube cube = cubes.get(player.getUUID());
                player.changeDimension(serverSpectreLevel, new SimpleTeleporter(cube.playerSpawnPosition));
            } else {
                SpectreCube cube = new SpectreCube(player.getUUID(), spectreLevel, cubeNumber);
                cube.createCube(spectreLevel);
                cubes.put(player.getUUID(), cube);
                cubeNumber++;
                player.changeDimension(serverSpectreLevel, new SimpleTeleporter(cube.playerSpawnPosition));
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        System.out.println("tomato save");
        compoundTag.putInt("cubeNumber", cubeNumber);
        for (SpectreCube cube : cubes.values()) {
            compoundTag.put(cube.owner.toString(), cube.save(new CompoundTag()));
        }
        return compoundTag;
    }


    public void load(CompoundTag compoundTag) {
        System.out.println("tomato load");
        cubeNumber = compoundTag.getInt("cubeNumber");
        cubes.clear();
        for (String key : compoundTag.getAllKeys()) {
            cubes.put(UUID.fromString(key), new SpectreCube(UUID.fromString(key), spectreLevel, cubeNumber));
        }
    }
}
