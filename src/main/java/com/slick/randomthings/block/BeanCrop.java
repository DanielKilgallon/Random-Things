package com.slick.randomthings.block;

import com.slick.randomthings.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;

public class BeanCrop extends CropBlock implements IPlantable {

    public BeanCrop(Properties properties) {
        super(properties);
    }

//    @Override
//    public BlockState getStateForAge(int age) {
//        return age > 1 ? ModBlocks.BEAN_CROP.sta:
//    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
//        Blocks.WHEAT
        return this.defaultBlockState();
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.BEAN;
    }
}
