package net.maxrhino.portal_mod.mixin;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.maxrhino.portal_mod.interfaces.ducks.FabricCreativeModeTabOutputDuck;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FabricCreativeModeTabOutput.class)
public class MixinFabricCreativeModeTabOutput implements FabricCreativeModeTabOutputDuck {
    @Override
    public void portal_mod$accept(ItemLike... items) {
        for (ItemLike item : items) {
            portal_mod$accept(item);
        }
    }

    @Override
    public void portal_mod$accept(ItemLike item) {
        FabricCreativeModeTabOutput instance =  (FabricCreativeModeTabOutput) (Object) this;
        instance.accept(item);
    }
}
