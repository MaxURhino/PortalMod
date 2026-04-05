package net.maxrhino.portal_mod.registry.item.util.geckolib;

import com.geckolib.renderer.GeoItemRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import net.maxrhino.portal_mod.registry.item.types.PortalGunItem;
import net.maxrhino.portal_mod.registry.util.PortalModDataTickets;

public class PortalGunItemRenderer extends GeoItemRenderer<PortalGunItem> {
    public PortalGunItemRenderer() {
        super(new PortalGunModel());
    }

    @Override
    public void addRenderData(PortalGunItem animatable, GeoItemRenderer.RenderData relatedObject, GeoRenderState renderState, float partialTick) {
        renderState.addGeckolibData(PortalModDataTickets.PORTAL_COLOR, animatable.getPortalColor());
    }
}
