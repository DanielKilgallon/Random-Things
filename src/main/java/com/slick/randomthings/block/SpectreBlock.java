package com.slick.randomthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class SpectreBlock extends GlassBlock {
    public SpectreBlock() {
        super(Properties.copy(Blocks.BEDROCK).isViewBlocking((BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_) -> {
            return false;
        }).color(MaterialColor.ICE).sound(SoundType.GLASS));
    }
}
