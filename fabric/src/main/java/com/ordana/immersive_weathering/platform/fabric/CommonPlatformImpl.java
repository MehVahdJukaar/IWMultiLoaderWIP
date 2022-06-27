package com.ordana.immersive_weathering.platform.fabric;

import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;

public class CommonPlatformImpl {

    public static void registerCompostable(ItemLike item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item,chance);
    }

    public static boolean isModLoaded(String name) {

    }

    public static void addExtraFloweryBlocks(ImmutableBiMap.Builder<Block, Block> builder) {
    }
}
