package net.maxrhino.portal_mod;

import net.fabricmc.api.ModInitializer;
import net.maxrhino.portal_mod.registry.block.PortalModBlocks;
import net.maxrhino.portal_mod.registry.block.entity.PortalModBlockEntities;
import net.maxrhino.portal_mod.registry.item.PortalModItems;
import net.maxrhino.portal_mod.registry.item.tabs.PortalModCreativeModeTabs;
import net.maxrhino.portal_mod.registry.util.PortalModDataTickets;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortalMod implements ModInitializer {
    public static final String MOD_ID = "portal_mod";
    public static final String MOD_NAME = "Portal Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Loading {}.", MOD_NAME);
        PortalModBlocks.getInstance().bootstrap();
        PortalModItems.getInstance().bootstrap();
        PortalModCreativeModeTabs.getInstance().bootstrap();
        PortalModDataTickets.getInstance().bootstrap();
        PortalModBlockEntities.getInstance().bootstrap();
        LOGGER.info("{} has been initialized.", MOD_NAME);
    }

    public static Identifier id(final String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
