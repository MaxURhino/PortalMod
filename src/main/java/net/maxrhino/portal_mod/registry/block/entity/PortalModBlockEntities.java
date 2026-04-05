package net.maxrhino.portal_mod.registry.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.portal_mod.registry.block.PortalModBlocks;
import net.maxrhino.portal_mod.registry.block.entity.type.PortalBlockEntity;
import net.maxrhino.portal_mod.registry.util.ModAssetInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PortalModBlockEntities extends ModAssetInitializer {
    public static final BlockEntityType<PortalBlockEntity> PORTAL_BLOCK_ENTITY =
            register("portal", PortalBlockEntity::new, PortalModBlocks.PORTAL);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(PortalMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public PortalModBlockEntities() {
        super("block entity");
    }

    public static PortalModBlockEntities getInstance() {
        return new PortalModBlockEntities();
    }
}
