package net.maxrhino.portal_mod.registry.block.entity.type;

import net.maxrhino.portal_mod.registry.block.entity.PortalModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PortalBlockEntity extends BlockEntity {
    public PortalBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(PortalModBlockEntities.PORTAL_BLOCK_ENTITY, worldPosition, blockState);
    }
}
