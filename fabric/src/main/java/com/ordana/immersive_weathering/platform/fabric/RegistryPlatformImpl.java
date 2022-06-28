package com.ordana.immersive_weathering.platform.fabric;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.platform.RegistryPlatform;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class RegistryPlatformImpl {

    public static void registerCompostable(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void registerItemBurnTime(Item item, int burnTime) {
        FuelRegistry.INSTANCE.add(item, burnTime);
    }


    public static Supplier<SimpleParticleType> registerParticle(String name) {
        SimpleParticleType instance = Registry.register(Registry.PARTICLE_TYPE,
                ImmersiveWeathering.res(name), FabricParticleTypes.simple());
        return () -> instance;
    }

    public static Block createStairs(RegistryPlatform.BlockType type, Supplier<Block> baseBlock, BlockBehaviour.Properties properties) {
    }

    public static Block createSpecialBlock(RegistryPlatform.BlockType type, Supplier<Block> baseBlock, BlockBehaviour.Properties properties) {
    }

    public static Block createSpecialBlock(RegistryPlatform.BlockType type, BlockBehaviour.Properties properties) {
    }

    public static Block createSpecialBlock(RegistryPlatform.BlockType type, BlockBehaviour.Properties properties, Object ...extraParams) {
    }
}
