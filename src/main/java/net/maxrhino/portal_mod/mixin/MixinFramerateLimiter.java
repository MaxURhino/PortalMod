package net.maxrhino.portal_mod.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.FramerateLimiter;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FramerateLimiter.class)
public class MixinFramerateLimiter {
    @WrapMethod(method = "limitDisplayFPS")
    private static void portal_mod$removeFPSLimit(int framerateLimit, Operation<Void> original) {

    }
}
