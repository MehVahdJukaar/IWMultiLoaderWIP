package com.ordana.immersive_weathering.registry.blocks.mossable;

import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.blocks.PatchSpreader;
import com.ordana.immersive_weathering.registry.blocks.crackable.Crackable;
import java.util.Optional;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface CrackableMossable extends Mossable, Crackable {

    @Override
    default <T extends Enum<?>> Optional<PatchSpreader<T>> getPatchSpreader(Class<T> weatheringClass) {
        if (weatheringClass == MossLevel.class) {
            return Optional.of((PatchSpreader<T>) getMossSpreader());
        } else if (weatheringClass == CrackLevel.class) {
            return Optional.of((PatchSpreader<T>) getCrackSpreader());
        }
        return Optional.empty();
    }

    @Override
    default boolean shouldWeather(BlockState state, BlockPos pos, Level world) {
        return Mossable.super.shouldWeather(state, pos, world) ||
                Crackable.super.shouldWeather(state, pos, world);
    }

    @Override
    default void tryWeather(BlockState state, ServerLevel serverLevel, BlockPos pos, Random random) {
        if(ImmersiveWeathering1.getConfig().blockGrowthConfig.blockCracking && ImmersiveWeathering1.getConfig().blockGrowthConfig.blockMossing) {
            if (random.nextFloat() < this.getWeatherChanceSpeed()) {
                boolean isMoss = this.getMossSpreader().getWantedWeatheringState(true, pos, serverLevel);
                Optional<BlockState> opt = Optional.empty();
                if (isMoss) {
                    opt = this.getNextMossy(state);
                } else if (this.getCrackSpreader().getWantedWeatheringState(true, pos, serverLevel)) {
                    opt = this.getNextCracked(state);
                }
                BlockState newState = opt.orElse(state.setValue(WEATHERABLE, WeatheringState.FALSE));
                if (newState != state) {
                    serverLevel.setBlock(pos, newState, 2);
                    //schedule block event in 1 tick
                    if (!newState.hasProperty(WEATHERABLE)) {
                        serverLevel.scheduleTick(pos, state.getBlock(), 1);
                    }
                }
            }
        }
    }
}
