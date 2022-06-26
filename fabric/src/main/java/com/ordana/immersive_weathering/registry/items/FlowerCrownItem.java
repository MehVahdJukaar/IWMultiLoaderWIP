package com.ordana.immersive_weathering.registry.items;

import com.ordana.immersive_weathering.registry.ModParticles;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class FlowerCrownItem extends ArmorItem {

    public FlowerCrownItem(ArmorMaterial material, EquipmentSlot slot, Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (world instanceof ServerLevel serverWorld) {
            if (world.random.nextFloat() < 0.2) {
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity.getItemBySlot(EquipmentSlot.HEAD).getItem() == this) {
                        Vec3 v = entity.getLookAngle().scale(entity.isSwimming() ? 1.5 : -0.125f);
                        serverWorld.sendParticles(ModParticles.AZALEA_FLOWER,
                                v.x + entity.getRandomX(0.675D),
                                v.y + entity.getY() + entity.getEyeHeight() + 0.15D,
                                v.z + entity.getRandomZ(0.675D),
                                1,
                                0, 0, 0,0);
                    }
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}