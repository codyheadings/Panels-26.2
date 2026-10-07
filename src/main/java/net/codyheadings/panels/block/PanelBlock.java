package net.codyheadings.panels.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PanelBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<AttachFace> FACE =
            BlockStateProperties.ATTACH_FACE;
    public static final EnumProperty<Direction> FACING =
            BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape FLOOR_SHAPE =
            Block.box(0, 0, 0, 16, 3, 16);

    private static final VoxelShape CEILING_SHAPE =
            Block.box(0, 13, 0, 16, 16, 16);

    private static final VoxelShape SOUTH_SHAPE =
            Block.box(0, 0, 13, 16, 16, 16);

    private static final VoxelShape NORTH_SHAPE =
            Block.box(0, 0, 0, 16, 16, 3);

    private static final VoxelShape EAST_SHAPE =
            Block.box(13, 0, 0, 16, 16, 16);

    private static final VoxelShape WEST_SHAPE =
            Block.box(0, 0, 0, 3, 16, 16);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PanelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACE, AttachFace.WALL)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        if (clickedFace == Direction.UP) {
            return this.defaultBlockState()
                    .setValue(FACE, AttachFace.FLOOR)
                    .setValue(FACING, context.getHorizontalDirection())
                    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }

        if (clickedFace == Direction.DOWN) {
            return this.defaultBlockState()
                    .setValue(FACE, AttachFace.CEILING)
                    .setValue(FACING, context.getHorizontalDirection())
                    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }

        return this.defaultBlockState()
                .setValue(FACE, AttachFace.WALL)
                .setValue(FACING, clickedFace.getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level,
                               BlockPos pos, CollisionContext context) {

        AttachFace face = state.getValue(FACE);
        Direction dir = state.getValue(FACING);

        if (face == AttachFace.FLOOR) return FLOOR_SHAPE;
        if (face == AttachFace.CEILING) return CEILING_SHAPE;

        return switch (dir) {
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    public FluidState getFluidState(BlockState context) {
        return context.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(context);
    }
}
