package net.maxrhino.portal_mod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.maxrhino.portal_mod.registry.block.PortalModBlocks;
import net.maxrhino.portal_mod.registry.block.entity.PortalModBlockEntities;
import net.maxrhino.portal_mod.registry.item.PortalModItems;
import net.maxrhino.portal_mod.registry.item.tabs.PortalModCreativeModeTabs;
import net.maxrhino.portal_mod.registry.item.types.PortalGunItem;
import net.maxrhino.portal_mod.registry.util.PortalModDataTickets;
import net.maxrhino.portal_mod.util.PortalColor;
import net.maxrhino.portal_mod.util.config.PortalModConfig;
import net.minecraft.SharedConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortalMod implements ModInitializer {
    public static final String MOD_ID = "portal_mod";
    public static final String MOD_NAME = "Portal Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Loading {}.", MOD_NAME);
        AutoConfig.register(PortalModConfig.class, JanksonConfigSerializer::new);
        ClientTickEvents.START_CLIENT_TICK.register((client) -> {
            if (client.player != null) {
                ItemStack stack = client.player.getMainHandItem();
                Item item = stack.getItem();
                if (item instanceof PortalGunItem portalGunItem) {
                    KeyMapping destroyKey = client.options.keyAttack;
                    while (destroyKey.consumeClick()) {
                        portalGunItem.setPortalColor(PortalColor.ORANGE);
                    }

                    KeyMapping useKey = client.options.keyUse;
                    while (useKey.consumeClick()) {
                        portalGunItem.setPortalColor(PortalColor.BLUE);
                    }
                }
            }
        });
        PortalModBlocks.getInstance().bootstrap();
        PortalModItems.getInstance().bootstrap();
        PortalModCreativeModeTabs.getInstance().bootstrap();
        PortalModDataTickets.getInstance().bootstrap();
        PortalModBlockEntities.getInstance().bootstrap();
        LOGGER.info("{} has been initialized.", MOD_NAME);
        LevelRenderEvents.BEFORE_BLOCK_OUTLINE.register((_, _) -> AutoConfig.getConfigHolder(PortalModConfig.class).get().isShouldRenderBlockOutline());
    }

    public static Identifier id(final String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
