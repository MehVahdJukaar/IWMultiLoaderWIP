package com.ordana.immersive_weathering.registry.blocks.mossable;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ordana.immersive_weathering.ImmersiveWeathering1;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import com.ordana.immersive_weathering.registry.blocks.PatchSpreader;
import com.ordana.immersive_weathering.registry.blocks.Weatherable;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public interface Mossable extends Weatherable {

    Supplier<BiMap<Block, Block>> MOSS_LEVEL_INCREASES = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.STONE, ModBlocks.MOSSY_STONE)
            .put(Blocks.STONE_STAIRS, ModBlocks.MOSSY_STONE_STAIRS)
            .put(Blocks.STONE_SLAB, ModBlocks.MOSSY_STONE_SLAB)
            .put(ModBlocks.STONE_WALL, ModBlocks.MOSSY_STONE_WALL)

            .put(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE)
            .put(Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS)
            .put(Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB)
            .put(Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL)

            .put(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS)
            .put(Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS)
            .put(Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB)
            .put(Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL)

            .put(Blocks.BRICKS, ModBlocks.MOSSY_BRICKS)
            .put(Blocks.BRICK_STAIRS, ModBlocks.MOSSY_BRICK_STAIRS)
            .put(Blocks.BRICK_SLAB, ModBlocks.MOSSY_BRICK_SLAB)
            .put(Blocks.BRICK_WALL, ModBlocks.MOSSY_BRICK_WALL)
            .build());

    Supplier<BiMap<Block, Block>> MOSS_LEVEL_DECREASES = Suppliers.memoize(() -> MOSS_LEVEL_INCREASES.get().inverse());

    private static Block getUnaffectedMossBlock(Block block) {
        Block block2 = block;
        Block block3 = MOSS_LEVEL_DECREASES.get().get(block2);
        while (block3 != null) {
            block2 = block3;
            block3 = MOSS_LEVEL_DECREASES.get().get(block2);
        }
        return block2;
    }

    private static Optional<Block> getDecreasedMossBlock(Block block) {
        return Optional.ofNullable(MOSS_LEVEL_DECREASES.get().get(block));
    }

    private static Optional<BlockState> getDecreasedMossState(BlockState state) {
        return getDecreasedMossBlock(state.getBlock()).map(block -> block.withPropertiesOf(state));
    }

    private static Optional<Block> getIncreasedMossBlock(Block block) {
        return Optional.ofNullable(MOSS_LEVEL_INCREASES.get().get(block));
    }

    public static BlockState getUnaffectedMossState(BlockState state) {
        return getUnaffectedMossBlock(state.getBlock()).withPropertiesOf(state);
    }

    default Optional<BlockState> getNextMossy(BlockState state) {
        return getIncreasedMossBlock(state.getBlock()).map(block -> block.withPropertiesOf(state));
    }

    default Optional<BlockState> getPreviousMossy(BlockState state) {
        return getDecreasedMossState(state);
    }


    MossSpreader getMossSpreader();

    @Override
    default <T extends Enum<?>> Optional<PatchSpreader<T>> getPatchSpreader(Class<T> weatheringClass){
        if (weatheringClass == MossLevel.class){
            return Optional.of((PatchSpreader<T>) getMossSpreader());
        }
        return Optional.empty();
    }

    default boolean shouldWeather(BlockState state, BlockPos pos, Level level) {
        return this.getMossSpreader().getWantedWeatheringState(false, pos, level);
    }

    MossLevel getMossLevel();

    boolean isWeatherable(BlockState state);

    enum MossLevel {
        MOSSABLE,
        MOSSY;
    }

    @Override
    default void tryWeather(BlockState state, ServerLevel serverWorld, BlockPos pos, Random random) {
        if(ImmersiveWeathering1.getConfig().blockGrowthConfig.blockMossing) {
            if (random.nextFloat() < this.getWeatherChanceSpeed()) {
                Optional<BlockState> opt = Optional.empty();
                if (this.getMossSpreader().getWantedWeatheringState(true, pos, serverWorld)) {
                    opt = this.getNextMossy(state);
                }
                BlockState newState = opt.orElse(state.setValue(WEATHERABLE, WeatheringState.FALSE));
                if (newState != state) {
                    serverWorld.setBlock(pos, newState, 2);
                    //schedule block event in 1 tick
                    if (!newState.hasProperty(WEATHERABLE)) {
                        serverWorld.scheduleTick(pos, state.getBlock(), 1);
                    }
                }
            }
        }
    }
}
