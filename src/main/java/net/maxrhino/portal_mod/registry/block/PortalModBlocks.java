package net.maxrhino.portal_mod.registry.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.portal_mod.interfaces.ducks.FabricCreativeModeTabOutputDuck;
import net.maxrhino.portal_mod.registry.block.types.PortalBlock;
import net.maxrhino.portal_mod.registry.util.ModAssetInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class PortalModBlocks extends ModAssetInitializer {
    public static final Block DARKER_CHECKERBOARD_TILE = register("darker_checkerboard_tile");
    public static final Block LIGHTER_CHECKERBOARD_TILE = register("lighter_checkerboard_tile");
    public static final Block CHECKERBOARD_TILES = register("checkerboard_tiles");
    public static final Block PORTAL = register("portal", PortalBlock::new, BlockBehaviour.Properties.of().noOcclusion().noCollision(), true);

    public PortalModBlocks() {
        super("block");
    }

    private static Block register(String name) {
        return register(name, Block::new, BlockBehaviour.Properties.of(), true);
    }

    private static Block register(String name, BlockBehaviour.Properties settings) {
        return register(name, Block::new, settings, true);
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, PortalMod.id(name));

        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, PortalMod.id(name));
            BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
    public static PortalModBlocks getInstance() {
        return new PortalModBlocks();
    }

    @Override
    public void bootstrap() {
        super.bootstrap();
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((outputOg) -> {
            FabricCreativeModeTabOutputDuck output = (FabricCreativeModeTabOutputDuck) outputOg;
            output.portal_mod$accept(DARKER_CHECKERBOARD_TILE, LIGHTER_CHECKERBOARD_TILE, CHECKERBOARD_TILES);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.OP_BLOCKS).register((outputOg) -> {
            FabricCreativeModeTabOutputDuck output = (FabricCreativeModeTabOutputDuck) outputOg;
            output.portal_mod$accept(PORTAL);
        });
    }
}
