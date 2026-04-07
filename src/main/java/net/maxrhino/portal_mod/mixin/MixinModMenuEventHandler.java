package net.maxrhino.portal_mod.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import com.terraformersmc.modmenu.gui.widget.ModMenuButtonWidget;
import com.terraformersmc.modmenu.gui.widget.UpdateCheckerTexturedButtonWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;

@Mixin(ModMenuEventHandler.class)
public class MixinModMenuEventHandler {
    @WrapMethod(method = "afterTitleScreenInit")
    private static void portal_mod$removeModMenuButtonAddition(Screen screen, Operation<Void> original) {

    }

    /*
    @WrapOperation(
            method = "afterTitleScreenInit",
            at = @At(
                    value = "NEW",
                    target = "(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/screens/Screen;)Lcom/terraformersmc/modmenu/gui/widget/ModMenuButtonWidget;",
                    ordinal = 1
            )
    )
    private static ModMenuButtonWidget portal_mod$changeButtonPosition(int x, int y, int width, int height, Component text, Screen screen, Operation<ModMenuButtonWidget> original) {
        return original.call(40, y, width, height, text, screen);
    }

    @WrapOperation(
            method = "afterTitleScreenInit",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/terraformersmc/modmenu/event/ModMenuEventHandler;buttonHasText(Lnet/minecraft/client/gui/layouts/LayoutElement;[Ljava/lang/String;)Z"
            )
    )
    private static boolean portal_mod$fixRealmsThing(LayoutElement widget, String[] translationKeys, Operation<Boolean> original) {
        if (Arrays.stream(translationKeys).toList().contains("menu.online")) {
            translationKeys = new String[] { "portal.menu.leaderboards" };
        }
        return original.call(widget, translationKeys);
    }

    @WrapOperation(
            method = "afterTitleScreenInit",
            at = @At(
                    value = "NEW",
                    target = "(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/screens/Screen;)Lcom/terraformersmc/modmenu/gui/widget/ModMenuButtonWidget;",
                    ordinal = 2
            )
    )
    private static ModMenuButtonWidget portal_mod$changeButtonPositionOnShrink(int x, int y, int width, int height, Component text, Screen screen, Operation<ModMenuButtonWidget> original) {
        return original.call(142, y, width, height, text, screen);
    }

    @WrapOperation(
            method = "afterTitleScreenInit",
            at = @At(
                    value = "NEW",
                    target = "(IIIIIIILnet/minecraft/resources/Identifier;IILnet/minecraft/client/gui/components/Button$OnPress;Lnet/minecraft/network/chat/Component;)Lcom/terraformersmc/modmenu/gui/widget/UpdateCheckerTexturedButtonWidget;"
            )
    )
    private static UpdateCheckerTexturedButtonWidget portal_mod$changeIconButtonPosition(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction, Component message, Operation<UpdateCheckerTexturedButtonWidget> original) {
        return original.call(244, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, message);
    }
     */
}
