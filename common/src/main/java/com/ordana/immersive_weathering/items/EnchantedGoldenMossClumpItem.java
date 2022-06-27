package com.ordana.immersive_weathering.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantedGoldenMossClumpItem extends Item {
    public EnchantedGoldenMossClumpItem(Properties settings) {
        super(settings);
    }

    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
