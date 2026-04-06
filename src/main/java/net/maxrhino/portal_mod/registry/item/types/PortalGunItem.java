package net.maxrhino.portal_mod.registry.item.types;

import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.RawAnimation;
import com.geckolib.animation.object.PlayState;
import com.geckolib.model.DefaultedItemGeoModel;
import com.geckolib.renderer.GeoItemRenderer;
import com.geckolib.util.GeckoLibUtil;
import com.google.common.base.Suppliers;
import net.maxrhino.portal_mod.registry.item.util.geckolib.PortalGunItemRenderer;
import net.maxrhino.portal_mod.util.PortalColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PortalGunItem extends Item implements GeoItem {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private PortalColor portalColor = PortalColor.BLUE;

    public PortalGunItem(Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("AnimationHandler", _->PlayState.STOP)
                .triggerableAnim("idle", RawAnimation.begin().thenLoop("animation.portal_gun.idle"))
                .triggerableAnim("shoot", RawAnimation.begin().thenLoop("animation.portal_gun.shoot"))
        );
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
