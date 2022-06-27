package com.ordana.immersive_weathering.reg;

import com.ordana.immersive_weathering.platform.RegistryPlatform;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModEntities {

    public static Supplier<EntityType<FallingIcicleEntity>> FALLING_ICICLE = RegistryPlatform.registerEntity("falling_icicle",
            EntityType.Builder.<FallingIcicleEntity>of(FallingIcicleEntity::new, MobCategory.MISC)
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(20));

    public static Supplier<EntityType<FallingLayerEntity>> FALLING_LAYER = RegistryPlatform.registerEntity("falling_layer",
            EntityType.Builder.<FallingLayerEntity>of(FallingLayerEntity::new, MobCategory.MISC)
                    .sized(0.98F, 0.98F)
                    .clientTrackingRange(10)
                    .updateInterval(20));


}
