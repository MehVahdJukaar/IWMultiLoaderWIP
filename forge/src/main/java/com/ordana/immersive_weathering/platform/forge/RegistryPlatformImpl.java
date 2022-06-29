package com.ordana.immersive_weathering.platform.forge;

import com.ordana.immersive_weathering.blocks.rustable.Rustable;
import com.ordana.immersive_weathering.common.blocks.rustable.*;
import com.ordana.immersive_weathering.forge.ModRegistry;
import com.ordana.immersive_weathering.forge.MulchBlock;
import com.ordana.immersive_weathering.platform.RegistryPlatform;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

public class RegistryPlatformImpl {

    public static void registerCompostable(ItemLike item, float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    public static void registerItemBurnTime(Item item, int burnTime) {
    }

    public static Supplier<SimpleParticleType> registerParticle(String name) {

    }

    public static Block createSpecialBlock(RegistryPlatform.BlockType type, BlockBehaviour.Properties properties, Object... extraParams) {
        return switch (type) {
            case MULCH -> new MulchBlock(properties);
            case RUSTABLE_BLOCK -> new RustableBlock((Rustable.RustLevel) extraParams[0], properties);
            case RUSTABLE_STAIRS ->
                    new RustableStairsBlock((Rustable.RustLevel) extraParams[0], (Supplier<Block>) extraParams[1], properties);
            case RUSTABLE_BARS -> new RustableBarsBlock((Rustable.RustLevel) extraParams[0], properties);
            case RUSTABLE_DOOR -> new RustableDoorBlock((Rustable.RustLevel) extraParams[0], properties);
            case RUSTABLE_SLAB -> new RustableSlabBlock((Rustable.RustLevel) extraParams[0], properties);
            case RUSTABLE_TRAPDOOR -> new RustableTrapdoorBlock((Rustable.RustLevel) extraParams[0], properties);
            case RUSTABLE_VERTICAL_SLAB -> new RustableVerticalSlabBlock((Rustable.RustLevel) extraParams[0], properties);
        };
    }

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        return ModRegistry.BLOCKS.register(name, block);
    }


}
