package com.ordana.immersive_weathering.platform.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

import java.util.function.Supplier;

public class RegistryPlatformImpl {

    public static void registerCompostable(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void registerItemBurnTime(Item item, int burnTime) {}
}
