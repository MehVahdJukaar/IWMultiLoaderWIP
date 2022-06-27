package com.ordana.immersive_weathering.registry.entities;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModEntities {

    public static EntityType<FallingIcicleEntity> FALLING_ICICLE;
    public static EntityType<FallingAshEntity> FALLING_ASH;
    public static EntityType<FallingSandLayerEntity> FALLING_SAND_LAYER;
    public static EntityType<FallingLeafLayerEntity> FALLING_LEAF_LAYER;

    private static <T extends Entity> EntityType<T> registerEntity(String id, EntityType.Builder<T> type) {
        return (EntityType)Registry.register(Registry.ENTITY_TYPE, id, type.build(id));
    }

    public static final BlockEntityType<IcicleBlockEntity> ICICLE_TILE = registerBlockEntity(
            IcicleBlockEntity::new, ModBlocks.ICICLE
    );

    private static <T extends BlockEntity> BlockEntityType<T>
    registerBlockEntity(FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(ImmersiveWeathering1.MOD_ID, "icicle"), FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

    public static void registerEntities() {
        FALLING_ICICLE = registerEntity("falling_icicle",
                EntityType.Builder.<FallingIcicleEntity>of(FallingIcicleEntity::new, MobCategory.MISC)
                        .sized(0.98F, 0.98F)
                        .clientTrackingRange(10)
                        .updateInterval(20));

        FALLING_ASH = registerEntity("falling_ash",
                EntityType.Builder.<FallingAshEntity>of(FallingAshEntity::new, MobCategory.MISC)
                        .sized(0.98F, 0.98F)
                        .clientTrackingRange(10)
                        .updateInterval(20));

        FALLING_SAND_LAYER = registerEntity("falling_sand_layer",
                EntityType.Builder.<FallingSandLayerEntity>of(FallingSandLayerEntity::new, MobCategory.MISC)
                        .sized(0.98F, 0.98F)
                        .clientTrackingRange(10)
                        .updateInterval(20));

        FALLING_LEAF_LAYER = registerEntity("falling_leaf_layer",
                EntityType.Builder.<FallingLeafLayerEntity>of(FallingLeafLayerEntity::new, MobCategory.MISC)
                        .sized(0.98F, 0.98F)
                        .clientTrackingRange(10)
                        .updateInterval(20));
    }
}
