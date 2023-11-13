package com.slick.randomthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SuperLubricentStone extends Block {
    public SuperLubricentStone() {
        // Friction value is to cancel out default friction applied to grounded player
        super(Properties.copy(Blocks.STONE).friction(1));
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player player && !player.isShiftKeyDown()) {
            player.setDiscardFriction(true);
        }
        super.stepOn(world, pos, state, entity);
    }
}
