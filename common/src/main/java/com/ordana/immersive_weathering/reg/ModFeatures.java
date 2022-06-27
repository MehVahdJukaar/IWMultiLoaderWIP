package com.ordana.immersive_weathering.reg;

import com.ordana.immersive_weathering.features.IcicleClusterFeature;
import com.ordana.immersive_weathering.features.IcicleClusterFeatureConfig;
import com.ordana.immersive_weathering.features.IvyFeature;
import com.ordana.immersive_weathering.platform.RegistryPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GlowLichenConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

public class ModFeatures {

    public static final Supplier<Feature<IcicleClusterFeatureConfig>> ICICLE_FEATURE = RegistryPlatform.registerFeature("icicle_cluster", () ->
            new IcicleClusterFeature(IcicleClusterFeatureConfig.CODEC));

    public static final Supplier<Feature<GlowLichenConfiguration>> IVY_FEATURE = RegistryPlatform.registerFeature("ivy_feature", () ->
            new IvyFeature(GlowLichenConfiguration.CODEC));

    public static void registerFeatures() {

        ResourceKey<PlacedFeature> icicles = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "icicles"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.icicleFeature) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.ICY), GenerationStep.Decoration.TOP_LAYER_MODIFICATION, icicles);
        }

        ResourceKey<PlacedFeature> cryosol_patch = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "cryosol_patch"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.cryosolFeature) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.ICY), GenerationStep.Decoration.RAW_GENERATION, cryosol_patch);
        }

        ResourceKey<PlacedFeature> humus_patch = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "humus_patch"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.humusFeature) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DARK_FOREST), GenerationStep.Decoration.RAW_GENERATION, humus_patch);
        }

        ResourceKey<PlacedFeature> rooted_ceiling = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "rooted_ceiling"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.rootsFeature) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DARK_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, rooted_ceiling);
        }

        ResourceKey<PlacedFeature> fluvisol_patch_submerged = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "fluvisol_patch_submerged"));
        ResourceKey<PlacedFeature> fluvisol_patch_surface = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "fluvisol_patch_surface"));
        ResourceKey<PlacedFeature> fluvisol_patch_dry = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "fluvisol_patch_dry"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.fluvisolFeature) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER, Biomes.SWAMP), GenerationStep.Decoration.RAW_GENERATION, fluvisol_patch_dry);
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER, Biomes.SWAMP), GenerationStep.Decoration.RAW_GENERATION, fluvisol_patch_surface);
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER, Biomes.SWAMP), GenerationStep.Decoration.RAW_GENERATION, fluvisol_patch_submerged);
        }

        ResourceKey<PlacedFeature> silt_disk = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "silt_disk"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.siltFeature) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.RIVER, Biomes.SWAMP), GenerationStep.Decoration.RAW_GENERATION, silt_disk);
        }

        ResourceKey<PlacedFeature> dry_lakebed = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "dry_lakebed"));
        ResourceKey<PlacedFeature> dry_lakebed_large = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "dry_lakebed_large"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.lakebedFeature) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS, Biomes.DESERT), GenerationStep.Decoration.RAW_GENERATION, dry_lakebed_large);
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS, Biomes.DESERT), GenerationStep.Decoration.RAW_GENERATION, dry_lakebed);
        }

        ResourceKey<PlacedFeature> vertisol_patch = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "vertisol_patch"));
        if (ImmersiveWeathering1.getConfig().worldgenConfig.vertisolFeature) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS), GenerationStep.Decoration.RAW_GENERATION, vertisol_patch);
        }


        ResourceKey<PlacedFeature> ivy_patch = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY,
                new ResourceLocation("immersive_weathering", "ivy_patch"));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ivy_patch);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WINDSWEPT_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ivy_patch);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ivy_patch);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.DARK_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ivy_patch);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ivy_patch);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ivy_patch);


    }
}
