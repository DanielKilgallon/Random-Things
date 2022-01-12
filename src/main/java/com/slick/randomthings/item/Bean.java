package com.slick.randomthings.item;

import com.slick.randomthings.block.BeanCrop;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
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
}
