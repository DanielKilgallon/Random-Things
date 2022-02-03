package com.slick.randomthings.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class SpectreBlock extends GlassBlock {
    public SpectreBlock() {
        super(Properties.copy(Blocks.BEDROCK).noOcclusion()
                .isViewBlocking((BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_) -> {
                    return false;
                }).sound(SoundType.GLASS));
    }

    @Override
    public float getShadeBrightness(BlockState p_48731_, BlockGetter p_48732_, BlockPos p_48733_) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_48740_, BlockGetter p_48741_, BlockPos p_48742_) {
        return true;
    }
}
