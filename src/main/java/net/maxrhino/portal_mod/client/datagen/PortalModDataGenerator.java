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
import net.minecraft.network.chat.Component;
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

                translationBuilder.add("portal.menu.start_game",     "Start Game"    );
                translationBuilder.add("portal.menu.challenge_maps", "Challenge Maps");
                translationBuilder.add("portal.menu.leaderboards",   "Leaderboards"  );
                translationBuilder.add("portal.menu.achievements",   "Achievements"  );
                translationBuilder.add("portal.menu.help_n_options", "Help & Options");
                translationBuilder.add("portal.menu.exit_game",      "Exit Game"     );



                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-1",
                        "<clr:219,112,147><I>Hello and, again, welcome to the Aperture Science computer-aided enrichment center."
                );
                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-2",
                        "<clr:219,112,147><I>We hope your brief detention in the relaxation vault has been a pleasant one."
                );
                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-3",
                        "<clr:219,112,147><I>Your specimen has been processed and we are now ready to begin the test proper."
                );
                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-4",
                        "<clr:219,112,147><I>Before we start, however, keep in mind that although fun and learning are the primary goals of all enrichment center activities, serious injuries may occur."
                );
                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-5",
                        "<clr:219,112,147><I>For your own safety and the safety of others, please refrain from touching [bzzzzzt]"
                );
                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-6",
                        "<clr:219,112,147><I>Por favor bordón de fallar Muchos gracias de fallar gracias"
                );
                translationBuilder.add(
                        "closedcaptions.portal.00_part1_entry-7",
                        "<clr:219,112,147><I>stand back. The portal will open in three. two. one."
                );
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
