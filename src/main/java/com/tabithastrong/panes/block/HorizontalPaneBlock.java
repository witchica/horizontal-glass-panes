package com.tabithastrong.panes.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class HorizontalPaneBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty WEST = BooleanProperty.of("west");

    public static final Map<BooleanProperty, BooleanProperty[]> canConnectMap = new HashMap<BooleanProperty, BooleanProperty[]>();

    static {
        canConnectMap.put(NORTH, new BooleanProperty[] {PaneBlock.EAST, PaneBlock.WEST});
        canConnectMap.put(SOUTH, new BooleanProperty[] {PaneBlock.EAST, PaneBlock.WEST});
        canConnectMap.put(EAST, new BooleanProperty[] {PaneBlock.NORTH, PaneBlock.SOUTH});
        canConnectMap.put(WEST, new BooleanProperty[] {PaneBlock.NORTH, PaneBlock.SOUTH});
    }

    public HorizontalPaneBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(WATERLOGGED, false)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED, NORTH, EAST, SOUTH, WEST);
    }

    public static boolean doesSideConnect(BlockState state, Direction dir) {
        if(state.getBlock() instanceof PaneBlock) {
//            if(dir == Direction.EAST || dir == Direction.WEST) {
//                return state.get(PaneBlock.NORTH) && state.get(PaneBlock.SOUTH);
//            } else {
//                return state.get(PaneBlock.EAST) && state.get(PaneBlock.WEST);
//            }

            return  true;
        }

        return false;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        FluidState fluidState = world.getFluidState(pos);
        boolean north = doesSideConnect(world.getBlockState(pos.north()), Direction.NORTH);
        boolean east = doesSideConnect(world.getBlockState(pos.east()), Direction.EAST);
        boolean south = doesSideConnect(world.getBlockState(pos.south()), Direction.SOUTH);
        boolean west = doesSideConnect(world.getBlockState(pos.west()), Direction.WEST);

        return getDefaultState()
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER)
                .with(NORTH, north)
                .with(EAST, east)
                .with(SOUTH, south)
                .with(WEST, west);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean north = doesSideConnect(ctx.getWorld().getBlockState(ctx.getBlockPos().north()), Direction.NORTH);
        boolean east = doesSideConnect(ctx.getWorld().getBlockState(ctx.getBlockPos().east()), Direction.EAST);
        boolean south = doesSideConnect(ctx.getWorld().getBlockState(ctx.getBlockPos().south()), Direction.SOUTH);
        boolean west = doesSideConnect(ctx.getWorld().getBlockState(ctx.getBlockPos().west()), Direction.WEST);

        return getDefaultState()
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER)
                .with(NORTH, north)
                .with(EAST, east)
                .with(SOUTH, south)
                .with(WEST, west);
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0, 0.4375f, 0, 1, 0.5625f, 1);
    }
}
