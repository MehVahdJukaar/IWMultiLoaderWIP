package com.ordana.immersive_weathering.block_growth;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.immersive_weathering.block_growth.area_condition.AreaCondition;
import com.ordana.immersive_weathering.block_growth.position_test.PositionRuleTest;
import java.util.*;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry.Wrapper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

/**
 * Author: MehVahdJukaar
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class BlockGrowthConfiguration implements IBlockGrowth {

    public static final BlockGrowthConfiguration EMPTY = new BlockGrowthConfiguration(1,
            AlwaysTrueTest.INSTANCE, Optional.empty(), List.of(), HolderSet.direct(Holder.direct(Blocks.AIR)), Optional.empty(), Optional.empty(), Optional.empty());

    public static final Codec<BlockGrowthConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("growth_chance").forGetter(BlockGrowthConfiguration::getGrowthChance),
            RuleTest.CODEC.fieldOf("replacing_target").forGetter(BlockGrowthConfiguration::getTargetPredicate),
            AreaCondition.CODEC.optionalFieldOf("area_condition").forGetter(BlockGrowthConfiguration::getAreaCondition),
            DirectionalList.CODEC.listOf().fieldOf("growth_for_face").forGetter(BlockGrowthConfiguration::encodeRandomLists),
            RegistryCodecs.homogeneousList(Registry.BLOCK_REGISTRY).fieldOf("owners").forGetter(b->b.owners),
            PositionRuleTest.CODEC.listOf().optionalFieldOf("position_predicates").forGetter(BlockGrowthConfiguration::getBiomePredicates),
            Codec.BOOL.optionalFieldOf("target_self").forGetter(b->b.targetSelf() ? Optional.of(Boolean.TRUE) : Optional.empty()),
            Codec.BOOL.optionalFieldOf("destroy_target").forGetter(b -> b.destroyTarget() ? Optional.of(Boolean.TRUE) : Optional.empty())
    ).apply(instance, BlockGrowthConfiguration::new));


    private final float growthChance;
    private final HolderSet<Block> owners;
    private final RuleTest targetPredicate;
    private final SimpleWeightedRandomList<Direction> growthForDirection;
    private final Map<Direction, SimpleWeightedRandomList<BlockPair>> blockGrowths;
    private final Set<Block> possibleBlocks;
    private final List<PositionRuleTest> biomePredicates;
    private final boolean targetSelf;
    private final boolean destroyTarget;


    private final int maxRange;
    private final AreaCondition areaCondition;

    public BlockGrowthConfiguration(float growthChance, RuleTest targetPredicate, Optional<AreaCondition> areaCheck,
                                    List<DirectionalList> growthForDirection,
                                    HolderSet<Block> owners, Optional<List<PositionRuleTest>> biomePredicates,
                                    Optional<Boolean> targetSelf, Optional<Boolean> destroyTarget) {
        this.growthChance = growthChance;
        this.owners = owners;
        this.biomePredicates = biomePredicates.orElse(List.of());
        this.targetPredicate = targetPredicate;

        SimpleWeightedRandomList.Builder<Direction> dirBuilder = SimpleWeightedRandomList.builder();
        ImmutableMap.Builder<Direction, SimpleWeightedRandomList<BlockPair>> growthBuilder = new ImmutableMap.Builder<>();
        ImmutableSet.Builder<Block> blockBuilder = new ImmutableSet.Builder<>();
        for (var randomBlockList : growthForDirection) {
            //if direction is null it means if is for all directions
            if (randomBlockList.direction.isEmpty()){ //hackery
                //clearing builders since only 1 exist no
                dirBuilder = SimpleWeightedRandomList.builder();
                growthBuilder = new ImmutableMap.Builder<>();
                blockBuilder = new ImmutableSet.Builder<>();
                decodeRandomList(Direction.UP, dirBuilder, growthBuilder, blockBuilder, randomBlockList);
                for (Direction d : Direction.values()) {
                    if (d != Direction.UP) {
                        growthBuilder.put(d, randomBlockList.randomList);
                        dirBuilder.add(d, 1);
                    }
                }
                break;
            } else {
                decodeRandomList(randomBlockList.direction.get(), dirBuilder, growthBuilder, blockBuilder, randomBlockList);
            }

        }
        this.growthForDirection = dirBuilder.build();
        this.blockGrowths = growthBuilder.build();
        this.possibleBlocks = blockBuilder.build();

        this.areaCondition = areaCheck.orElse(AreaCondition.EMPTY);
        this.maxRange = areaCondition.getMaxRange();
        this.targetSelf = targetSelf.orElse(false);
        this.destroyTarget = destroyTarget.orElse(false);
    }

    private void decodeRandomList(Direction direction, SimpleWeightedRandomList.Builder<Direction> dirBuilder, ImmutableMap.Builder<Direction,
            SimpleWeightedRandomList<BlockPair>> growthBuilder, ImmutableSet.Builder<Block> blockBuilder, DirectionalList v) {
        int weight = v.weight.orElse(1);
        dirBuilder.add(direction, weight);
        growthBuilder.put(direction, v.randomList);
        v.randomList.unwrap().stream().map(Wrapper::getData).forEach(o -> {
            BlockState f = o.getFirst();
            if (f != null) blockBuilder.add(f.getBlock());
            BlockState s = o.getFirst();
            if (s != null) blockBuilder.add(s.getBlock());
        });
    }

    public List<DirectionalList> encodeRandomLists() {
        List<DirectionalList> list = new ArrayList<>();
        for (var e : growthForDirection.unwrap()){

            Optional<Direction> dir;
            Optional<Integer> weight;
            if (growthForDirection.unwrap().size() == 1 ){
                dir = Optional.empty();
                weight = Optional.empty();
            } else {
                dir = Optional.of(e.getData());
                weight = Optional.of(e.getWeight().asInt());
            }
            list.add(new DirectionalList(dir, weight, blockGrowths.get(e.getData())));

        }
        return list;
    }

    public RuleTest getTargetPredicate() {
        return targetPredicate;
    }

    public Optional<AreaCondition> getAreaCondition() {
        return areaCondition == AreaCondition.EMPTY ? Optional.empty() : Optional.of(areaCondition);
    }

    public Set<Block> getPossibleBlocks() {
        return this.possibleBlocks;
    }

    public boolean targetSelf() {
        return targetSelf;
    }

    public boolean destroyTarget() {
        return destroyTarget;
    }

    public Optional<List<PositionRuleTest>> getBiomePredicates() {
        return this.biomePredicates.isEmpty() ? Optional.empty() : Optional.of(this.biomePredicates);
    }

    public boolean isEmpty() {
        return this.possibleBlocks.isEmpty();
    }

    private boolean canGrow(BlockPos pos, Level world, Holder<Biome> biome) {
        if (this.growthChance == 0) return false;
        if(world.random.nextFloat() < this.growthChance) {
            for (PositionRuleTest biomeTest : this.biomePredicates) {
                if (!biomeTest.test(biome, pos, world)) return false;
            }
            return world.hasChunkAt(pos);
        }return false;
    }

    public float getGrowthChance() {
        return growthChance;
    }

    @Override
    public Iterable<Block> getOwners(){
        return this.owners.stream().map(Holder::value).collect(Collectors.toList());
    }

    @Override
    public void tryGrowing(BlockPos pos, BlockState self, ServerLevel world, Holder<Biome> biome) {

        if (this.canGrow(pos, world, biome)) {
            Direction dir = this.growthForDirection.getRandomValue(world.random).orElse(Direction.UP);

            Random seed = new Random(Mth.getSeed(pos));
            BlockPos targetPos = targetSelf ? pos : pos.relative(dir);
            BlockState target =  world.getBlockState(targetPos);

            if (targetSelf || targetPredicate.test(target, seed)) {

                var l = blockGrowths.get(dir);
                if (l != null) {
                    var toPlace = l.getRandomValue(world.random).orElse(null);
                    if (toPlace != null && toPlace.getFirst().canSurvive(world, targetPos)) {
                        BlockPos targetPos2 = null;
                        BlockState target2 = null;
                        boolean db = toPlace.isDouble();
                        if (db) {
                            targetPos2 = targetPos.relative(dir);
                            target2 = world.getBlockState(targetPos2);
                            if (!targetPredicate.test(target2, seed)) {
                                return;
                            }
                        }

                        if (areaCondition.test(pos, world, this)) {
                            if (destroyTarget) world.destroyBlock(targetPos, true);
                            world.setBlock(targetPos, setWaterIfNeeded(toPlace.getFirst(), target), 2);
                            if (db) {
                                if (destroyTarget) world.destroyBlock(targetPos2, true);
                                world.setBlock(targetPos2, setWaterIfNeeded(toPlace.getSecond(), target2), 2);
                            }
                            return;
                        }
                    }
                }
            }
        }
        return;
    }

    private BlockState setWaterIfNeeded(BlockState toPlace, BlockState target) {
        if (toPlace.hasProperty(BlockStateProperties.WATERLOGGED) && target.getFluidState().is(FluidTags.WATER)) {
            return toPlace.setValue(BlockStateProperties.WATERLOGGED, true);
        }
        return toPlace;
    }

    public record DirectionalList(Optional<Direction> direction, Optional<Integer> weight,
                                  DataPool<BlockPair> randomList) {

        public static final Codec<DirectionalList> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Direction.CODEC.optionalFieldOf("direction").forGetter(DirectionalList::direction),
                Codec.INT.optionalFieldOf("weight").forGetter(DirectionalList::weight),
                DataPool.createCodec(BlockPair.CODEC).fieldOf("growth").forGetter(DirectionalList::randomList)
        ).apply(instance, DirectionalList::new));
    }
}