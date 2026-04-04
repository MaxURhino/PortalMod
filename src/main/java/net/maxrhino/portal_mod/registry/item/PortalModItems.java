package net.maxrhino.portal_mod.registry.item;

import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.portal_mod.registry.util.ModAssetInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class PortalModItems extends ModAssetInitializer {
    // TODO: Register the portal gun.

    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, PortalMod.id(name));
        T item = itemFactory.apply(settings);
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public PortalModItems() {
        super("item");
    }

    public static PortalModItems getInstance() {
        return new PortalModItems();
    }

    @Override
    public void bootstrap() {
        super.bootstrap();
    }
}
