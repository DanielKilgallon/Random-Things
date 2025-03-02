package com.slick.randomthings.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class SimpleTeleporter implements ITeleporter {

    private BlockPos teleportPosition;

    public SimpleTeleporter(BlockPos teleportPosition) {
        this.teleportPosition = teleportPosition;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity newEntity = repositionEntity.apply(false);
        newEntity.teleportTo(teleportPosition.getX(), teleportPosition.getY(), teleportPosition.getZ());
        return newEntity;
    }

    @Override
    public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld,
            Function<ServerLevel, PortalInfo> defaultPortalInfo) {
        return new PortalInfo(entity.position(), Vec3.ZERO, entity.getYRot(), entity.getXRot());
    }

    @Override
    public boolean isVanilla() {
        return false;
    }

    @Override
    public boolean playTeleportSound(ServerPlayer player, ServerLevel sourceWorld, ServerLevel destWorld) {
        return true;
    }
}
