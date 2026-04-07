package net.maxrhino.portal_mod.registry.block.types;

import com.mojang.serialization.MapCodec;
import net.maxrhino.portal_mod.registry.block.entity.type.PortalBlockEntity;
import net.maxrhino.portal_mod.util.PortalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.*;

import java.util.Map;

public class PortalBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final MapCodec<PortalBlock> CODEC = simpleCodec(PortalBlock::new);
    public static final EnumProperty<PortalColor> COLOR = EnumProperty.create("color", PortalColor.class);
    private static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(box(0, 0, 15, 16, 32, 16));

    public PortalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(COLOR, PortalColor.ORANGE).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLOR, FACING);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos relativePos = pos.relative(state.getValue(FACING).getOpposite());
        BlockPos upperRelativePos = pos.relative(state.getValue(FACING).getOpposite()).above();
        BlockState relativeState = level.getBlockState(relativePos);
        BlockState upperRelativeState = level.getBlockState(upperRelativePos);
        return relativeState.isFaceSturdy(level, relativePos, state.getValue(FACING)) && upperRelativeState.isFaceSturdy(level, upperRelativePos, state.getValue(FACING));
    }

    @Override
    public @NonNull MapCodec<PortalBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos worldPosition, @NonNull BlockState blockState) {
        return new PortalBlockEntity(worldPosition, blockState);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
