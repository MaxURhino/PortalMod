package net.maxrhino.portal_mod.registry.util;

import com.geckolib.constant.dataticket.DataTicket;
import net.maxrhino.portal_mod.util.PortalColor;

public class PortalModDataTickets extends ModAssetInitializer {
    public static final DataTicket<PortalColor> PORTAL_COLOR = DataTicket.create(
            "portal_color",
            PortalColor.class
    );

    public PortalModDataTickets() {
        super("data ticket");
    }

    public static PortalModDataTickets getInstance() {
        return new PortalModDataTickets();
    }
}
