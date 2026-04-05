package net.maxrhino.portal_mod.registry.item.tabs;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.portal_mod.registry.block.PortalModBlocks;
import net.maxrhino.portal_mod.registry.item.PortalModItems;
import net.maxrhino.portal_mod.registry.util.ModAssetInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class PortalModCreativeModeTabs extends ModAssetInitializer {
    public static final ResourceKey<CreativeModeTab> PORTAL_1_TAB = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            PortalMod.id("portal_1_tab")
    );

    public PortalModCreativeModeTabs() {
        super("creative mode tab");
    }

    public static PortalModCreativeModeTabs getInstance() {
        return new PortalModCreativeModeTabs();
    };

    @Override
    public void bootstrap() {
        super.bootstrap();

        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                PORTAL_1_TAB,
                FabricCreativeModeTab.builder()
                        .icon(PortalModItems.PORTAL_GUN::getDefaultInstance)
                        .title(Component.translatable("itemGroup.portal_1_tab"))
                        .displayItems((_, output) -> {
                            output.accept(PortalModBlocks.CHECKERBOARD_TILES);
                            output.accept(PortalModBlocks.DARKER_CHECKERBOARD_TILE);
                            output.accept(PortalModBlocks.LIGHTER_CHECKERBOARD_TILE);
                            output.accept(PortalModItems.PORTAL_GUN);
                        }).build()
        );
    }
}
