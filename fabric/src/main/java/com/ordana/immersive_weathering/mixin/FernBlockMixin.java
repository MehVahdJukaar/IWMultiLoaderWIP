package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.registry.ModTags;
import com.ordana.immersive_weathering.registry.blocks.FrostyGrassBlock;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(TallGrassBlock.class)
public class FernBlockMixin extends BushBlock {
    public FernBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(BlockTags.DIRT) || floor.is(Blocks.FARMLAND) || floor.is(ModTags.CRACKED);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        if(ImmersiveWeathering.getConfig().fireAndIceConfig.grassFrosting) {
            if (state.is(Blocks.GRASS)) {
                if ((world.isRaining() || world.isNight()) && world.getBiome(pos).is(ModTags.ICY) && (world.getBrightness(LightLayer.BLOCK, pos) < 7 - state.getLightBlock(world, pos))) {
                    world.setBlockAndUpdate(pos, ModBlocks.FROSTY_GRASS.defaultBlockState().setValue(FrostyGrassBlock.NATURAL, true));
                }
            }
            else if (state.is(Blocks.FERN)) {
                if ((world.isRaining() || world.isNight()) && world.getBiome(pos).is(ModTags.ICY) && (world.getBrightness(LightLayer.BLOCK, pos) < 7 - state.getLightBlock(world, pos))) {
                    world.setBlockAndUpdate(pos, ModBlocks.FROSTY_FERN.defaultBlockState().setValue(FrostyGrassBlock.NATURAL, true));
                }
            }
        }
    }
}
