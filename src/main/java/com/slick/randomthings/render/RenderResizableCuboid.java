package com.slick.randomthings.render;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class RenderResizableCuboid {

    public static final RenderResizableCuboid INSTANCE = new RenderResizableCuboid();

    //stolen from Cyclic mod
    public static List<BlockPos> cubeSquareBase(final BlockPos pos, int radius, int height) {
        List<BlockPos> shape = new ArrayList<>();
        // search in a cube
        int xMin = pos.getX() - radius;
        int xMax = pos.getX() + radius;
        int zMin = pos.getZ() - radius;
        int zMax = pos.getZ() + radius;
        for (int x = xMin; x <= xMax; x++) {
            for (int z = zMin; z <= zMax; z++) {
                for (int y = pos.getY(); y <= pos.getY() + height; y++) {
                    //now go max height on each pillar for sort order
                    shape.add(new BlockPos(x, y, z));
                }
            }
        }
        return shape;
    }

    public void renderCube(BlockPos blockPos) {
        List<BlockPos> cube = cubeSquareBase(blockPos, 10, 20);
        for (BlockPos posTarget : cube) {
            System.out.println(posTarget.toString());
//            BlockState blockHere = world.getBlockState(posTarget);
        }
    }
}
