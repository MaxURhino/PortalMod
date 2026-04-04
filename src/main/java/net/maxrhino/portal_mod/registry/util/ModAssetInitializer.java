package net.maxrhino.portal_mod.registry.util;

import net.maxrhino.portal_mod.PortalMod;

public abstract class ModAssetInitializer {
    protected final String initializerName;

    public ModAssetInitializer(String initializerName) {
        this.initializerName = initializerName;
    }

    public ModAssetInitializer() {
        this("null");
    }

    public void bootstrap() {
        PortalMod.LOGGER.info("Registering the {}.", this);
    }

    @Override
    public String toString() {
        return PortalMod.MOD_NAME + " " + initializerName + " initializer";
    }
}
