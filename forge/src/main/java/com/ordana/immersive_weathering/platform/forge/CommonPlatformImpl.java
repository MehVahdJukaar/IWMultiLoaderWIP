package com.ordana.immersive_weathering.platform.forge;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class CommonPlatformImpl {

    public static void registerCompostable(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }
}
