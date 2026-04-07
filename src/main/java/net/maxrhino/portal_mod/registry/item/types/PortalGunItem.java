package net.maxrhino.portal_mod.registry.item.types;

import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.renderer.GeoItemRenderer;
import com.geckolib.util.GeckoLibUtil;
import com.google.common.base.Suppliers;
import net.maxrhino.portal_mod.registry.item.util.geckolib.PortalGunItemRenderer;
import net.maxrhino.portal_mod.util.PortalColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PortalGunItem extends Item implements GeoItem {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private PortalColor portalColor = PortalColor.BLUE;

    public PortalGunItem(Properties properties) {
        super(properties);

        GeoItem.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("AnimationHandler", _->PlayState.STOP)
                .triggerableAnim("idle", RawAnimation.begin().thenLoop("animation.portal_gun.idle"))
                .triggerableAnim("shoot", RawAnimation.begin().thenPlay("animation.portal_gun.shoot").thenLoop("animation.portal_gun.idle"))
        );
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        setPortalColor(PortalColor.BLUE);
        if (level instanceof ServerLevel serverLevel)
            triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), "AnimationHandler", "shoot");
        return super.use(level, player, hand);
    }

    public void setPortalColor(PortalColor portalColor) {
        this.portalColor = portalColor;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final Supplier<PortalGunItemRenderer> renderer = Suppliers.memoize(PortalGunItemRenderer::new);

            @Override
            public @Nullable GeoItemRenderer<?> getGeoItemRenderer() {
                return this.renderer.get();
            }
        });
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    public PortalColor getPortalColor() {
        return portalColor;
    }
}
