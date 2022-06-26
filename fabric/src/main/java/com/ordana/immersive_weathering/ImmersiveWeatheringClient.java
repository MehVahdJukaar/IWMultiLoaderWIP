package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.registry.ModParticles;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.particles.EmberParticle;
import com.ordana.immersive_weathering.registry.particles.LeafParticle;
import com.ordana.immersive_weathering.registry.entities.ModEntities;
import com.ordana.immersive_weathering.registry.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Blocks;

public class ImmersiveWeatheringClient implements ClientModInitializer {

    public static class ScrapeRustFactory extends GlowParticle.ScrapeProvider {

        public ScrapeRustFactory(SpriteSet spriteSet) {
            super(spriteSet);
        }

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double p_172207_, double p_172208_, double p_172209_, double p_172210_, double p_172211_, double p_172212_) {
            Particle p = super.createParticle(particleType, level, p_172207_, p_172208_, p_172209_, p_172210_, p_172211_, p_172212_);
            if(p!=null) {
                if (level.random.nextBoolean()) {
                    p.setColor(196/255f, 118/255f, 73/255f);
                } else {
                    p.setColor(176/255f, 63/255f, 40/255f);
                }
            }
            return p;
        }
    }

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.FALLING_ICICLE, FallingBlockRenderer::new);
        EntityRendererRegistry.register(ModEntities.FALLING_ASH, FallingBlockRenderer::new);
        EntityRendererRegistry.register(ModEntities.FALLING_SAND_LAYER, FallingBlockRenderer::new);
        EntityRendererRegistry.register(ModEntities.FALLING_LEAF_LAYER, FallingBlockRenderer::new);

        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register(((atlasTexture, registry) -> {
            registry.register(new ResourceLocation("immersive_weathering", "particle/ember_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/soot_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/ember_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/soot_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/emberspark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/emberspark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/moss_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/moss_1"));

            registry.register(new ResourceLocation("immersive_weathering", "particle/oak_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/birch_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/spruce_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/jungle_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/acacia_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/dark_oak_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/azalea_leaf_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/azalea_flower_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/oak_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/birch_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/spruce_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/jungle_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/acacia_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/dark_oak_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/azalea_leaf_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/azalea_flower_1"));


            registry.register(new ResourceLocation("immersive_weathering", "particle/oak_bark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/birch_bark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/spruce_bark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/jungle_bark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/acacia_bark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/dark_oak_bark_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/nether_scale_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/oak_bark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/birch_bark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/spruce_bark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/jungle_bark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/acacia_bark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/dark_oak_bark_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/nether_scale_1"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/oak_bark_2"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/birch_bark_2"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/spruce_bark_2"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/jungle_bark_2"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/acacia_bark_2"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/dark_oak_bark_2"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/nether_scale_2"));


            //flower crowns
            registry.register(new ResourceLocation("immersive_weathering", "particle/bee_0"));
            registry.register(new ResourceLocation("immersive_weathering", "particle/bee_1"));

        }));
        ParticleFactoryRegistry.getInstance().register(ModParticles.EMBER, EmberParticle.EmberFactory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SOOT, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.EMBERSPARK, EmberParticle.EmberFactory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.MOSS, LeafParticle.SimpleLeafParticle::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.OAK_LEAF, LeafParticle.ColoredLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPRUCE_LEAF, LeafParticle.SpruceLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BIRCH_LEAF, LeafParticle.BirchLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.JUNGLE_LEAF, LeafParticle.ColoredLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ACACIA_LEAF, LeafParticle.ColoredLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.DARK_OAK_LEAF, LeafParticle.ColoredLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.AZALEA_LEAF, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.AZALEA_FLOWER, LeafParticle.SimpleLeafParticle::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.OAK_BARK, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPRUCE_BARK, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BIRCH_BARK, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.JUNGLE_BARK, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ACACIA_BARK, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.DARK_OAK_BARK, LeafParticle.SimpleLeafParticle::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.NETHER_SCALE, LeafParticle.SimpleLeafParticle::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.SCRAPE_RUST, ScrapeRustFactory::new);

        //flower crowns
        ParticleFactoryRegistry.getInstance().register(ModParticles.CROWN_BEE, LeafParticle.SimpleLeafParticle::new);



        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), ModBlocks.OAK_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColor.getEvergreenColor(), ModBlocks.SPRUCE_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FoliageColor.getBirchColor(), ModBlocks.BIRCH_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), ModBlocks.JUNGLE_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), ModBlocks.ACACIA_LEAF_PILE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor(), ModBlocks.DARK_OAK_LEAF_PILE);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.FROSTY_GRASS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.FROSTY_FERN);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.ROOTED_GRASS_BLOCK);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_COBBLESTONE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_COBBLESTONE_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_COBBLESTONE_STAIRS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_COBBLESTONE_WALL);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.INFESTED_MOSSY_STONE_BRICKS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_STONE_BRICKS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_STONE_BRICK_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_STONE_BRICK_STAIRS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), Blocks.MOSSY_STONE_BRICK_WALL);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_BRICKS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_BRICK_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_BRICK_STAIRS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_BRICK_WALL);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_STONE);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_STONE_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_STONE_STAIRS);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0D, 0D), ModBlocks.MOSSY_STONE_WALL);


        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        }, ModItems.OAK_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return FoliageColor.getEvergreenColor();
        }, ModItems.SPRUCE_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return FoliageColor.getBirchColor();
        }, ModItems.BIRCH_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        }, ModItems.JUNGLE_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        }, ModItems.ACACIA_LEAF_PILE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return FoliageColor.getDefaultColor();
        }, ModItems.DARK_OAK_LEAF_PILE);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.ROOTED_GRASS_BLOCK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_COBBLESTONE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_COBBLESTONE_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_COBBLESTONE_STAIRS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_COBBLESTONE_WALL);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.INFESTED_MOSSY_STONE_BRICKS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_STONE_BRICKS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_STONE_BRICK_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_STONE_BRICK_STAIRS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), Items.MOSSY_STONE_BRICK_WALL);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_BRICKS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_BRICK_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_BRICK_STAIRS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_BRICK_WALL);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_STONE);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_STONE_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_STONE_STAIRS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(0.5D,0.5D), ModItems.MOSSY_STONE_WALL);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROOTED_GRASS_BLOCK, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_COBBLESTONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_COBBLESTONE_SLAB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_COBBLESTONE_STAIRS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_COBBLESTONE_WALL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.INFESTED_MOSSY_STONE_BRICKS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_STONE_BRICKS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_STONE_BRICK_SLAB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_STONE_BRICK_STAIRS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.MOSSY_STONE_BRICK_WALL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_BRICKS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_BRICK_SLAB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_BRICK_STAIRS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_BRICK_WALL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_STONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_STONE_SLAB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_STONE_STAIRS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOSSY_STONE_WALL, RenderType.cutout());


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VITRIFIED_SAND, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_IRON_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_IRON_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUSTED_IRON_DOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_IRON_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_IRON_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUSTED_IRON_TRAPDOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_IRON_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_IRON_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUSTED_IRON_BARS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_IRON_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_IRON_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_IRON_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_RUSTED_IRON_DOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_IRON_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_IRON_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_IRON_TRAPDOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_RUSTED_IRON_TRAPDOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_IRON_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_IRON_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_IRON_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_RUSTED_IRON_BARS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICICLE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FULGURITE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OAK_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPRUCE_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BIRCH_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JUNGLE_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ACACIA_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DARK_OAK_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLOWERING_AZALEA_LEAF_PILE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_FLOWER_PILE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEEDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.IVY, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOOT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOOT, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROST, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROST, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROSTY_GLASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROSTY_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROSTY_GLASS_PANE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROSTY_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROSTY_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FROSTY_FERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THIN_ICE, RenderType.translucent());

    }
}
