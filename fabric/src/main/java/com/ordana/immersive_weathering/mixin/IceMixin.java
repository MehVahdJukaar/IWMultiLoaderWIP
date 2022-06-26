package com.ordana.immersive_weathering.mixin;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.registry.ModTags;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.blocks.ThinIceBlock;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(IceBlock.class)
abstract public class IceMixin extends Block {

    @Shadow protected abstract void melt(BlockState state, Level world, BlockPos pos);

    public IceMixin(Properties settings) {
        super(settings);
    }

    @Inject(method = "randomTick", at = @At("HEAD"))
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random, CallbackInfo ci) {
        int rand = random.nextInt(4);
        Direction direction = Direction.from2DDataValue(rand);
        if(ImmersiveWeathering.getConfig().fireAndIceConfig.thinIceFormation) {
            if (world.getBlockState(pos.above()).is(Blocks.AIR) && (world.isRaining() || world.isNight()) && world.getBiome(pos).is(ModTags.ICY) && (world.getBrightness(LightLayer.BLOCK, pos) < 7 - state.getLightBlock(world, pos))) {
                if (world.getFluidState(pos.relative(direction)).is(Fluids.WATER)) {
                    world.setBlockAndUpdate(pos.relative(direction), ModBlocks.THIN_ICE.defaultBlockState().setValue(ThinIceBlock.WATERLOGGED, true));
                }
            }
        }
    }

    //TODO: is day is broken on client side
    private boolean canMelt(BlockState state, Level level, BlockPos pos) {
        if(ImmersiveWeathering.getConfig().fireAndIceConfig.naturalIceMelt) {
            return level.dimensionType().ultraWarm() || (level.getBiome(pos).value().shouldSnowGolemBurn(pos) && level.isDay()) ||
                    (level.getBrightness(LightLayer.BLOCK, pos) > 11 - state.getLightBlock(level, pos));

        }
        return false;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (random.nextInt(25) == 1) {
            if (this.canMelt(state, level, pos) || level.isDay()) {

                BlockPos blockpos = pos.below();
                BlockState blockstate = level.getBlockState(blockpos);
                if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, Direction.UP)) {
                    double d0 = (double) pos.getX() + random.nextDouble();
                    double d1 = (double) pos.getY() - 0.05D;
                    double d2 = (double) pos.getZ() + random.nextDouble();
                    level.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}
