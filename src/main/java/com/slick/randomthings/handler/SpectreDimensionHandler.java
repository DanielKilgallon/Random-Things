package com.slick.randomthings.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class SpectreDimensionHandler {

    public static final String ID = "SpectreHandler";
    private HashMap<UUID, BlockPos> cubes;
    private int positionCounter;

    public static SpectreDimensionHandler getInstance() {
//        world.getPerWorldStorage().setData(ID, handler);
        return new SpectreDimensionHandler();
    }

    public void teleportPlayerToSpectreCube(Level level, Player player) {
        // Save Old Position / Dimension
        CompoundTag compoundTag = player.getPersistentData();
        compoundTag.putDouble("spectrePosX", player.getX());
        compoundTag.putDouble("spectrePosY", player.getY());
        compoundTag.putDouble("spectrePosZ", player.getZ());
        compoundTag.putInt("spectreDimension", 1);

        UUID uuid = player.getGameProfile().getId();
        BlockPos teleportLoc;

        if (cubes.containsKey(uuid)) {
            teleportLoc = cubes.get(uuid);
        } else {
//            teleportLoc = generateSpectreCube(uuid);
        }

//        if (player.dimension != Internals.SPECTRE_ID) {
//            PlayerUtil.teleportPlayerToDimension(player, Internals.SPECTRE_ID);
//        }
//        player.connection.setPlayerLocation(spawn.getX() + 0.5, spawn.getY() + 1, spawn.getZ() + 0.5, 0, 0);
    }
}
