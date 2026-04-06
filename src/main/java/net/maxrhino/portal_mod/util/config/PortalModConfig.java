package net.maxrhino.portal_mod.util.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "portal_mod")
public class PortalModConfig implements ConfigData {
    boolean shouldRenderBlockOutline = false;

    public boolean isShouldRenderBlockOutline() {
        return shouldRenderBlockOutline;
    }
}
