package net.maxrhino.portal_mod.mixin.accessors.screen;

import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(TitleScreen.class)
public interface TitleScreenAccessor {
    @Invoker("getMultiplayerDisabledReason")
    Component portal_mod$getMultiplayerDisabledReason();
}
