package net.maxrhino.portal_mod.mixin.screen;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.realmsclient.RealmsMainScreen;
import net.maxrhino.portal_mod.mixin.accessors.screen.ScreenAccessor;
import net.maxrhino.portal_mod.mixin.accessors.screen.TitleScreenAccessor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen {
    @WrapMethod(method = "createNormalMenuOptions")
    private int portal_mod$changeTheTitleScreenEntirely(int topPos, int spacing, Operation<Integer> original) {
        TitleScreen instance = (TitleScreen)(Object)this;
        ScreenAccessor acc = (ScreenAccessor)this;
        acc.portal_mod$addRenderableWidget(Button.builder(Component.translatable("menu.singleplayer"), (button) -> acc.portal_mod$minecraft().setScreen(new SelectWorldScreen(instance))).bounds(instance.width / 2 - 100, topPos, 200, 20).build());
        Component multiplayerDisabledReason = ((TitleScreenAccessor)this).portal_mod$getMultiplayerDisabledReason();
        boolean multiplayerAllowed = multiplayerDisabledReason == null;
        Tooltip tooltip = multiplayerDisabledReason != null ? Tooltip.create(multiplayerDisabledReason) : null;
        int var6;
        ((Button)acc.portal_mod$addRenderableWidget(Button.builder(Component.translatable("menu.multiplayer"), (button) -> {
            Screen screen = (Screen)(acc.portal_mod$minecraft().options.skipMultiplayerWarning ? new JoinMultiplayerScreen(instance) : new SafetyScreen(instance));
            acc.portal_mod$minecraft().setScreen(screen);
        }).bounds(instance.width / 2 - 100, var6 = topPos + spacing, 200, 20).tooltip(tooltip).build())).active = multiplayerAllowed;
        ((Button)acc.portal_mod$addRenderableWidget(Button.builder(Component.translatable("menu.online"), (button) -> acc.portal_mod$minecraft().setScreen(new RealmsMainScreen(instance))).bounds(instance.width / 2 - 100, topPos = var6 + spacing, 200, 20).tooltip(tooltip).build())).active = multiplayerAllowed;
        return topPos;
    }
}
