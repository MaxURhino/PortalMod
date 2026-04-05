package net.maxrhino.portal_mod.registry.block.types;

import com.mojang.serialization.MapCodec;
import net.maxrhino.portal_mod.registry.block.entity.type.PortalBlockEntity;
import net.maxrhino.portal_mod.util.PortalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PortalBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final MapCodec<PortalBlock> CODEC = simpleCodec(PortalBlock::new);
    public static final EnumProperty<PortalColor> COLOR = EnumProperty.create("color", PortalColor.class);

    public PortalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(COLOR, PortalColor.ORANGE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COLOR, FACING);
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
