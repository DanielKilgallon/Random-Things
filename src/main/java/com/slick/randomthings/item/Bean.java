package com.slick.randomthings.item;

import com.slick.randomthings.block.BeanCrop;
import com.slick.randomthings.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

public class Bean extends Item implements IPlantable {

    public Bean(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        System.out.println("tomato");
        return BeanCrop.stateById(1);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Direction side = context.getClickedFace();
        Level level = context.getLevel();
        BlockState blockState = level.getBlockState(context.getClickedPos());

        if (side == Direction.UP && level.getBlockState(context.getClickedPos().above()).is(Blocks.AIR) && blockState.is(BlockTags.DIRT)) {
            level.setBlock(context.getClickedPos().above(), ModBlocks.BEAN_CROP.defaultBlockState(), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
