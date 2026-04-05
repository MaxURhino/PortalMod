package net.maxrhino.portal_mod.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SplashRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SplashRenderer.class)
public class MixinSplashRenderer {
    @WrapMethod(method = "extractRenderState")
    private void portal_mod$removeSplashText(GuiGraphicsExtractor graphics, int screenWidth, Font font, float alpha, Operation<Void> original) {}
}
