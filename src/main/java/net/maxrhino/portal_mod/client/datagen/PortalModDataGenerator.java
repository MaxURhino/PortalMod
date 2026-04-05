package net.maxrhino.portal_mod.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.maxrhino.portal_mod.registry.block.PortalModBlocks;
import net.maxrhino.portal_mod.registry.item.PortalModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

public class PortalModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider((output, future) -> new FabricLanguageProvider(output, future) {
            @Override
            public void generateTranslations(HolderLookup.@NonNull Provider provider, @NonNull TranslationBuilder translationBuilder) {
                translationBuilder.add(PortalModBlocks.DARKER_CHECKERBOARD_TILE, "Darker Checkerboard Tile");
                translationBuilder.add(PortalModBlocks.LIGHTER_CHECKERBOARD_TILE, "Lighter Checkerboard Tile");
                translationBuilder.add(PortalModBlocks.CHECKERBOARD_TILES, "Checkerboard Tiles");
                translationBuilder.add(PortalModItems.PORTAL_GUN, "Portal Gun");
            }
        });
        pack.addProvider((output, future) -> new FabricLanguageProvider(output, "pl_pl", future) {
            @Override
            public void generateTranslations(HolderLookup.@NonNull Provider provider, @NonNull TranslationBuilder translationBuilder) {
                translationBuilder.add(PortalModBlocks.DARKER_CHECKERBOARD_TILE, "Ciemniejszy Kafel Podłogi Szachownicowej");
                translationBuilder.add(PortalModBlocks.LIGHTER_CHECKERBOARD_TILE, "Jaśniejszy Kafel Podłogi Szachownicowej");
                translationBuilder.add(PortalModBlocks.CHECKERBOARD_TILES, "Kafle Podłogi Szachownicowej");
                translationBuilder.add(PortalModItems.PORTAL_GUN, "Działo Portalowe");
            }
        });
        pack.addProvider((output, _) -> new FabricModelProvider(output) {
            @Override
            public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {
                blockModelGenerators.createTrivialCube(PortalModBlocks.DARKER_CHECKERBOARD_TILE);
                blockModelGenerators.createTrivialCube(PortalModBlocks.LIGHTER_CHECKERBOARD_TILE);
                blockModelGenerators.createTrivialCube(PortalModBlocks.CHECKERBOARD_TILES);
            }

            @Override
            public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {}
        });
    }
}
