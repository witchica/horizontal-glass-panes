package com.tabithastrong.panes.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HorizontalPaneBlock extends Block {
    public HorizontalPaneBlock(Settings settings) {
        super(settings);

    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0, 0.4375f, 0, 1, 0.5625f, 1);
    }
}
