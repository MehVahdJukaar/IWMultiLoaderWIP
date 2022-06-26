package com.ordana.immersive_weathering.items;

import com.ordana.immersive_weathering.platform.RegistryPlatform;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class BurnableBlockItem extends BlockItem {
    private final int burnTime;

    public BurnableBlockItem(Block block, Properties properties, int burnTime) {
        super(block, properties);
        this.burnTime = burnTime;
        RegistryPlatform.registerItemBurnTime(this, burnTime);
    }


    //forge override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }

}
