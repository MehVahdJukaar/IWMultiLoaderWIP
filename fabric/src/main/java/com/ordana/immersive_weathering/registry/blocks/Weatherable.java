package com.ordana.immersive_weathering.registry.blocks;

import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

//a block that can weather
public interface Weatherable {

    EnumProperty<WeatheringState> WEATHERABLE = EnumProperty.create("weatherable", WeatheringState.class);

    enum WeatheringState implements StringRepresentable {
        FALSE,
        TRUE,
        STABLE;

        public boolean isWeatherable() {
            return this == TRUE;
        }

        public boolean isStable() {
            return this == STABLE;
        }

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    boolean shouldWeather(BlockState state, BlockPos pos, Level world);

    boolean isWeatherable(BlockState state);

    <T extends Enum<?>> Optional<PatchSpreader<T>> getPatchSpreader(Class<T> weatheringClass);

    default float getWeatherChanceSpeed(){
        return 0.1f;
    }

    //call this with random tick
    void tryWeather(BlockState state, ServerLevel serverWorld, BlockPos pos, Random random);

    //call on
    default BlockState getWeatheredStateForPlacement(BlockState state, BlockPos pos, Level world){
        if (state != null) {
            WeatheringState weathering = this.shouldWeather(state, pos, world) ? WeatheringState.TRUE : WeatheringState.FALSE;
            state = state.setValue(WEATHERABLE, weathering);
        }
        return state;
    }

    //call on neighbor changed
    default void updateWeatheredStateOnNeighborChanged(BlockState state, Level world, BlockPos pos) {
        if (world instanceof ServerLevel serverWorld) {
            WeatheringState current = state.getValue(WEATHERABLE);
            if(!current.isStable()) {
                var wantedState = this.shouldWeather(state, pos, serverWorld)
                        ? WeatheringState.TRUE : WeatheringState.FALSE;
                if (state.getValue(WEATHERABLE) != wantedState) {
                    //update weathering state
                    serverWorld.setBlock(pos, state.setValue(WEATHERABLE, wantedState),2);
                    //schedule block event in 1 tick
                    if(wantedState==WeatheringState.TRUE) {
                        world.scheduleTick(pos, state.getBlock(), 1);
                    }
                }
            }
        }
    }
}
