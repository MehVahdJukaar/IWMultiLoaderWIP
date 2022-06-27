package com.ordana.immersive_weathering.platform;

import com.google.common.collect.ImmutableBiMap;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class CommonPlatform {

    @ExpectPlatform
    public static boolean isModLoaded(String name) {
        throw new AssertionError();
    }


    @ExpectPlatform
    public static void addExtraFloweryBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
        throw new AssertionError();
    }
}
