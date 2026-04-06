package net.maxrhino.portal_mod.mixin.screen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.realmsclient.RealmsMainScreen;
import net.maxrhino.portal_mod.mixin.accessors.screen.ScreenAccessor;
import net.maxrhino.portal_mod.mixin.accessors.screen.TitleScreenAccessor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen {
    @WrapMethod(method = "createNormalMenuOptions")
    private int portal_mod$changeTheTitleScreenEntirely(int topPos, int spacing, Operation<Integer> original) {
        TitleScreen instance = (TitleScreen)(Object)this;
        ScreenAccessor acc = (ScreenAccessor)this;
        acc.portal_mod$addRenderableWidget(Button.builder(Component.translatable("portal.menu.start_game"), (button) -> acc.portal_mod$minecraft().setScreen(new SelectWorldScreen(instance))).bounds(40, topPos, 200, 20).build());
        Component multiplayerDisabledReason = ((TitleScreenAccessor)this).portal_mod$getMultiplayerDisabledReason();
        boolean multiplayerAllowed = multiplayerDisabledReason == null;
        Tooltip tooltip = multiplayerDisabledReason != null ? Tooltip.create(multiplayerDisabledReason) : null;
        int var6;
        ((Button)acc.portal_mod$addRenderableWidget(Button.builder(Component.translatable("portal.menu.challenge_maps"), (button) -> {
            Screen screen = (Screen)(acc.portal_mod$minecraft().options.skipMultiplayerWarning ? new JoinMultiplayerScreen(instance) : new SafetyScreen(instance));
            acc.portal_mod$minecraft().setScreen(screen);
        }).bounds(40, var6 = topPos + spacing, 200, 20).tooltip(tooltip).build())).active = multiplayerAllowed;
        ((Button)acc.portal_mod$addRenderableWidget(Button.builder(Component.translatable("portal.menu.leaderboards"), (button) -> acc.portal_mod$minecraft().setScreen(new RealmsMainScreen(instance))).bounds(40, topPos = var6 + spacing, 200, 20).tooltip(tooltip).build())).active = multiplayerAllowed;
        return topPos;
    }

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/SpriteIconButton;setPosition(II)V", ordinal = 0))
    private void portal_mod$moveLanguageBhutan(SpriteIconButton instance, int x, int y, Operation<Void> original) {
        original.call(instance, 16, y);
    }

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button;builder(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 0))
    private Button.Builder portal_mod$changeOptionsButton(Component message, Button.OnPress onPress, Operation<Button.Builder> original) {
        return Button.builder(
                Component.translatable("portal.menu.help_n_options"),
                onPress
        );
    }

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;", ordinal = 0))
    private Button.Builder portal_mod$moveOptionsButton(Button.Builder instance, int x, int y, int width, int height, Operation<Button.Builder> original) {
        return instance.bounds(40, y, width, height);
    }
}
