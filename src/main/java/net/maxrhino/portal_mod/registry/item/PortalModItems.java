package net.maxrhino.portal_mod.registry.item;

import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.portal_mod.registry.item.types.PortalGunItem;
import net.maxrhino.portal_mod.registry.util.ModAssetInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class PortalModItems extends ModAssetInitializer {
    public static final Item PORTAL_GUN = register(
            "portal_gun",
            PortalGunItem::new,
            new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
    );

    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, PortalMod.id(name));
        T item = itemFactory.apply(settings.setId(itemKey));
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
