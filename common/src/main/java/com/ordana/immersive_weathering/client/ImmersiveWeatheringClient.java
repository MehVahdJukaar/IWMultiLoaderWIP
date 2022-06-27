package com.ordana.immersive_weathering.client;

import com.ordana.immersive_weathering.client.particles.EmberParticle;
import com.ordana.immersive_weathering.client.particles.LeafParticle;
import com.ordana.immersive_weathering.platform.ClientPlatform;
import com.ordana.immersive_weathering.reg.ModBlocks;
import com.ordana.immersive_weathering.reg.ModEntities;
import com.ordana.immersive_weathering.reg.ModParticles;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class ImmersiveWeatheringClient {


    public static void initClient() {

       // LeafPilesRegistry.LEAF_PILES.get().values().forEach(l ->
       //         ClientPlatform.registerRenderType(l, RenderType.cutout()));

        ClientPlatform.registerRenderType(ModBlocks.AZALEA_FLOWER_PILE.get(), RenderType.cutout());

        ClientPlatform.registerRenderType(ModBlocks.VITRIFIED_SAND.get(), RenderType.translucent());

        ClientPlatform.registerRenderType(ModBlocks.ICICLE.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WEEDS.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.SOOT.get(), RenderType.translucent());

        ClientPlatform.registerRenderType(ModBlocks.EXPOSED_IRON_DOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WEATHERED_IRON_DOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.RUSTED_IRON_DOOR.get(), RenderType.cutout());

        ClientPlatform.registerRenderType(ModBlocks.EXPOSED_IRON_TRAPDOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WEATHERED_IRON_TRAPDOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.RUSTED_IRON_TRAPDOOR.get(), RenderType.cutout());

        ClientPlatform.registerRenderType(ModBlocks.EXPOSED_IRON_BARS.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WEATHERED_IRON_BARS.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.RUSTED_IRON_BARS.get(), RenderType.cutout());


        ClientPlatform.registerRenderType(ModBlocks.WAXED_IRON_DOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_EXPOSED_IRON_DOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_WEATHERED_IRON_DOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_RUSTED_IRON_DOOR.get(), RenderType.cutout());

        ClientPlatform.registerRenderType(ModBlocks.WAXED_IRON_TRAPDOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_EXPOSED_IRON_TRAPDOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_WEATHERED_IRON_TRAPDOOR.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_RUSTED_IRON_TRAPDOOR.get(), RenderType.cutout());

        ClientPlatform.registerRenderType(ModBlocks.WAXED_IRON_BARS.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_EXPOSED_IRON_BARS.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_WEATHERED_IRON_BARS.get(), RenderType.cutout());
        ClientPlatform.registerRenderType(ModBlocks.WAXED_RUSTED_IRON_BARS.get(), RenderType.cutout());

        ClientPlatform.registerRenderType(ModBlocks.HANGING_ROOTS_WALL.get(), RenderType.cutout());
    }

    @FunctionalInterface
    public interface EntityRendererReg{
        <E extends Entity> void register(EntityType<? extends E> entity , EntityRendererProvider<E> renderer);
    }

    public static void onRegisterEntityRenderTypes(EntityRendererReg event) {
        event.register(ModEntities.FALLING_ICICLE.get(), FallingBlockRenderer::new);
        event.register(ModEntities.FALLING_LAYER.get(), FallingBlockRenderer::new);
    }

    @FunctionalInterface
    public interface ParticleRendererReg{
        <T extends ParticleOptions> void register(ParticleType<T> type, Function<SpriteSet, ParticleProvider<T>> particleFactory);
    }

    public static void onRegisterParticles(ParticleRendererReg event){
        event.register(ModParticles.EMBERSPARK.get(), EmberParticle.EmberFactory::new);
        event.register(ModParticles.EMBER.get(), EmberParticle.EmberFactory::new);

        event.register(ModParticles.SOOT.get(), LeafParticle.SimpleLeafParticle::new);

        event.register(ModParticles.OAK_LEAF.get(), LeafParticle.ColoredLeafParticle::new);
        event.register(ModParticles.SPRUCE_LEAF.get(), LeafParticle.ColoredLeafParticle::new);
        event.register(ModParticles.BIRCH_LEAF.get(), LeafParticle.ColoredLeafParticle::new);
        event.register(ModParticles.JUNGLE_LEAF.get(), LeafParticle.ColoredLeafParticle::new);
        event.register(ModParticles.ACACIA_LEAF.get(), LeafParticle.ColoredLeafParticle::new);
        event.register(ModParticles.DARK_OAK_LEAF.get(), LeafParticle.ColoredLeafParticle::new);
        event.register(ModParticles.AZALEA_LEAF.get(), LeafParticle.SimpleLeafParticle::new);
        event.register(ModParticles.AZALEA_FLOWER.get(), LeafParticle.SimpleLeafParticle::new);

        event.register(ModParticles.MULCH.get(), LeafParticle.SimpleLeafParticle::new);
        event.register(ModParticles.NULCH.get(), LeafParticle.SimpleLeafParticle::new);

        event.register(ModParticles.SCRAPE_RUST.get(), ScrapeRustFactory::new);
    }

}
