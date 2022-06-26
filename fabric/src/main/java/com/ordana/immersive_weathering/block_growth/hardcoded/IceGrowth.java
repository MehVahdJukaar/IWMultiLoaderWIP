package com.ordana.immersive_weathering.block_growth.hardcoded;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.block_growth.IBlockGrowth;
import com.ordana.immersive_weathering.mixin.IceInvoker;

import java.util.List;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class IceGrowth implements IBlockGrowth {

    @Override
    public Iterable<Block> getOwners() {
        return List.of(Blocks.ICE, Blocks.BLUE_ICE, Blocks.PACKED_ICE);
    }

    @Override
    public void tryGrowing(BlockPos pos, BlockState state, ServerLevel world, Holder<Biome> b) {
        Biome biome = b.value();
        Random random = world.random;

        //melt ice
        if(ImmersiveWeathering.getConfig().fireAndIceConfig.naturalIceMelt) {
            if (state.getBlock() instanceof IceInvoker ice) {
                if (world.dimensionType().ultraWarm()) {
                    world.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.3F, 2.9F + (random.nextFloat() - random.nextFloat()) * 0.6F);

                    float i = pos.getX() + 0.5f;
                    float j = pos.getY() + 0.5f;
                    float k = pos.getZ() + 0.5f;
                    world.sendParticles(ParticleTypes.LARGE_SMOKE, i, j, k, 12, 0.2D, 0.2D, 0.2D, 0);
                    ice.invokeMelt(state, world, pos);
                } else if (biome.shouldSnowGolemBurn(pos) && world.isDay()) {
                    ice.invokeMelt(state, world, pos);
                }
            }
        }
    }
}
