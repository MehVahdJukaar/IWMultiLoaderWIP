package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.block_growth.BlockGrowthHandler;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReloadableServerResources.class)
public abstract class DataPackContentMixin {

    @Inject(method = "refresh", at = @At("TAIL"))
    private void onTagReload(RegistryAccess registryAccess, CallbackInfo ci){
        BlockGrowthHandler.rebuild(registryAccess);
    }
}
