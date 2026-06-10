package com.witchica.horizontalglasspanes.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ColorfulHorizontalPaneBlock extends HorizontalPaneBlock implements BeaconBeamBlock {
    private final DyeColor dyeColor;

    public ColorfulHorizontalPaneBlock(Properties properties, DyeColor dyeColor) {
        super(properties);
        this.dyeColor = dyeColor;
    }

    @Override
    public DyeColor getColor() {
        return this.dyeColor;
    }
}
