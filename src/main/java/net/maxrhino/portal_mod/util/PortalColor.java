package net.maxrhino.portal_mod.util;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum PortalColor implements StringRepresentable {
    BLUE("blue"),
    ORANGE("orange");

    private final String name;

    PortalColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public @NonNull String getSerializedName() {
        return name;
    }

    public static PortalColor fromString(String name) {
        for (PortalColor portalColor : values()) {
            if (portalColor.getName().equals(name)) {
                return portalColor;
            }
        }
        return null;
    }

    public static PortalColor invert(PortalColor portalColor) {
        if (portalColor == BLUE) {
            return ORANGE;
        } else if (portalColor == ORANGE) {
            return BLUE;
        }
        return null;
    }
}
