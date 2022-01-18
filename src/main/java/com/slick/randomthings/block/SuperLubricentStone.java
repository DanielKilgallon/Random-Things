package com.slick.randomthings.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SuperLubricentStone extends Block {
    public SuperLubricentStone() {
        super(Properties.copy(Blocks.STONE).friction(1.099F));
    }
}
