package net.maxrhino.portal_mod.registry.item.util.geckolib;

import com.geckolib.model.GeoModel;
import com.geckolib.renderer.base.GeoRenderState;
import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.portal_mod.registry.item.types.PortalGunItem;
import net.maxrhino.portal_mod.registry.util.PortalModDataTickets;
import net.maxrhino.portal_mod.util.PortalColor;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PortalGunModel extends GeoModel<PortalGunItem> {
    // Looks for a model file at '/assets/portal_mod/geckolib/models/item/portal_gun.geo.json'
    private final Identifier modelPath = PortalMod.id("item/portal_gun");
    // Looks for an animations file at '/assets/portal_mod/geckolib/animations/item/portal_gun.animation.json'
    private final Identifier animationsPath = PortalMod.id("item/portal_gun");
    // Looks for a texture file at '/assets/portal_mod/textures/item/blue_portal_gun.png'
    private final Identifier texturePath = PortalMod.id("textures/item/blue_portal_gun.png");

    @Override
    public @NonNull Identifier getModelResource(@NonNull GeoRenderState renderState) {
        return this.modelPath;
    }

    @Override
    public @NonNull Identifier getTextureResource(@NonNull GeoRenderState renderState) {
        PortalColor portalColor = renderState.getGeckolibData(PortalModDataTickets.PORTAL_COLOR);
        if (portalColor != null) {
            return getTexturePathFromColor(portalColor);
        } else {
            return this.texturePath;
        }
    }

    @Override
    public @NonNull Identifier getAnimationResource(PortalGunItem animatable) {
        return this.animationsPath;
    }

    @Override
    public void addAdditionalStateData(PortalGunItem animatable, @Nullable Object relatedObject, GeoRenderState renderState) {
        renderState.addGeckolibData(PortalModDataTickets.PORTAL_COLOR, animatable.getPortalColor());
    }

    public static Identifier getTexturePathFromColor(PortalColor color) {
            return PortalMod.id("textures/item/" + color.getSerializedName() + "_portal_gun.png");
    }
}
