package net.maxrhino.portal_mod.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.blaze3d.platform.FramerateLimitTracker;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FramerateLimitTracker.class)
public class MixinFramerateLimitTracker {
    @WrapMethod(method = "getFramerateLimit")
    private int portal_mod$getFramerateLimit(Operation<Integer> original) {
        return 260;
    }
}
