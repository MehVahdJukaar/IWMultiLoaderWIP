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

public class ImmersiveWeatheringClient2 implements ClientModInitializer {


    @Override
    public void onInitializeClient() {



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


    }
}
