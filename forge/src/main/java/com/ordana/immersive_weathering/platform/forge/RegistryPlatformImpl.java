package com.ordana.immersive_weathering.platform.forge;

import com.ordana.immersive_weathering.blocks.mossable.Mossable;
import com.ordana.immersive_weathering.blocks.mossable.MossyStairsBlock;
import com.ordana.immersive_weathering.platform.RegistryPlatform;
import com.ordana.immersive_weathering.unique.ModStairBlock;
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

    public static void registerItemBurnTime(Item item, int burnTime) {}

    public static Supplier<SimpleParticleType> registerParticle(String name) {

    }

    public static Block createStairs(RegistryPlatform.StairType type, Supplier<Block> baseBlock, BlockBehaviour.Properties properties) {
        return switch (type){
            case NORMAL -> new ModStairBlock(baseBlock, properties);
            case MOSSY -> new MossyStairsBlock(Mossable.MossLevel.MOSSY, baseBlock, properties);
            case CRACKED -> new Crackable(Mossable.MossLevel.MOSSY, baseBlock, properties);
            case MOSSY -> new MossyStairsBlock(Mossable.MossLevel.MOSSY, baseBlock, properties);
            case MOSSY -> new MossyStairsBlock(Mossable.MossLevel.MOSSY, baseBlock, properties);
        }
        return new ModStairBlock(baseBlock)
    }
}
