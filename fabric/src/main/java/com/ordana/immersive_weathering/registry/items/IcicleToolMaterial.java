package com.ordana.immersive_weathering.registry.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class IcicleToolMaterial implements Tier {

    public static final IcicleToolMaterial INSTANCE = new IcicleToolMaterial();

    public int getUses() {
        return 3;
    }


    public float getSpeed() {
        return 8f;
    }


    public float getAttackDamageBonus() {
        return 1f;
    }


    public int getLevel() {
        return 1;
    }


    public int getEnchantmentValue() {
        return 10;
    }


    public Ingredient getRepairIngredient() {
        return Ingredient.of(ModItems.ICICLE);
    }
}
