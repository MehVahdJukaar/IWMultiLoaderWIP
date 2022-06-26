package com.ordana.immersive_weathering.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.math.*;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.phys.Vec3;
import java.util.logging.Level;

public class ModParticles {

    public static final SimpleParticleType EMBER = FabricParticleTypes.simple();
    public static final SimpleParticleType SOOT = FabricParticleTypes.simple();
    public static final SimpleParticleType EMBERSPARK = FabricParticleTypes.simple();
    public static final SimpleParticleType MOSS = FabricParticleTypes.simple();

    public static final SimpleParticleType OAK_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType BIRCH_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType SPRUCE_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType JUNGLE_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType ACACIA_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType DARK_OAK_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType AZALEA_LEAF = FabricParticleTypes.simple();
    public static final SimpleParticleType AZALEA_FLOWER = FabricParticleTypes.simple();

    public static final SimpleParticleType OAK_BARK = FabricParticleTypes.simple();
    public static final SimpleParticleType BIRCH_BARK = FabricParticleTypes.simple();
    public static final SimpleParticleType SPRUCE_BARK = FabricParticleTypes.simple();
    public static final SimpleParticleType JUNGLE_BARK = FabricParticleTypes.simple();
    public static final SimpleParticleType ACACIA_BARK = FabricParticleTypes.simple();
    public static final SimpleParticleType DARK_OAK_BARK = FabricParticleTypes.simple();
    public static final SimpleParticleType NETHER_SCALE = FabricParticleTypes.simple();

    public static final SimpleParticleType SCRAPE_RUST = FabricParticleTypes.simple();

    //custom flower crowns
    public static final SimpleParticleType CROWN_BEE = FabricParticleTypes.simple();

    public static void spawnParticlesOnBlockFaces(net.minecraft.world.level.Level world, BlockPos pos, ParticleOptions particleEffect, UniformInt uniformInt) {
        for(Direction direction : Direction.values()) {
            int i = uniformInt.sample(world.random);

            for(int j = 0; j < i; ++j) {
                spawnParticleOnFace(world, pos, direction, particleEffect);
            }
        }

    }

    public static void spawnParticleOnFace(net.minecraft.world.level.Level world, BlockPos blockPos, Direction direction, ParticleOptions particleEffect) {
        Vec3 vec3 = Vec3.atCenterOf(blockPos);
        int i = direction.getStepX();
        int j = direction.getStepY();
        int k = direction.getStepZ();
        double d0 = vec3.x + (i == 0 ? Mth.nextDouble(world.random, -0.5D, 0.5D) : (double)i * 0.55D);
        double d1 = vec3.y + (j == 0 ? Mth.nextDouble(world.random, -0.5D, 0.5D) : (double)j * 0.55D);
        double d2 = vec3.z + (k == 0 ? Mth.nextDouble(world.random, -0.5D, 0.5D) : (double)k * 0.55D);
        world.addParticle(particleEffect, d0, d1, d2, 0, 0, 0);
    }

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "ember"), EMBER);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "soot"), SOOT);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "emberspark"), EMBERSPARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "moss"), MOSS);

        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "oak_leaf"), OAK_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "birch_leaf"), BIRCH_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "spruce_leaf"), SPRUCE_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "jungle_leaf"), JUNGLE_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "acacia_leaf"), ACACIA_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "dark_oak_leaf"), DARK_OAK_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "azalea_leaf"), AZALEA_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "azalea_flower"), AZALEA_FLOWER);

        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "oak_bark"), OAK_BARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "birch_bark"), BIRCH_BARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "spruce_bark"), SPRUCE_BARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "jungle_bark"), JUNGLE_BARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "acacia_bark"), ACACIA_BARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "dark_oak_bark"), DARK_OAK_BARK);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "nether_scale"), NETHER_SCALE);

        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "scrape_rust"), SCRAPE_RUST);

        //flower crowns
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation("immersive_weathering", "bee"), CROWN_BEE);
    }
}