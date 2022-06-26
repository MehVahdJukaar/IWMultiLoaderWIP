package com.ordana.immersive_weathering.registry.entities;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class RegisterHelper {

    public static <T extends Entity> EntityType<T> registerEntity(String registryName, EntityType<T> entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(ImmersiveWeathering.MOD_ID, registryName), entityType);
    }
}
