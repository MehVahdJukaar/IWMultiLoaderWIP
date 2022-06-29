package com.ordana.immersive_weathering.block_growth.position_test;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.Random;

record BlockTest(Vec3i offset, RuleTest predicate) implements PositionRuleTest {

    public static final String NAME = "neighbor_match";

    public static final Codec<BlockTest> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Vec3i.offsetCodec(16).optionalFieldOf("offset", Vec3i.ZERO).forGetter(BlockTest::offset),
            RuleTest.CODEC.fieldOf("predicate").forGetter(BlockTest::predicate)
    ).apply(instance, BlockTest::new));

    static final PositionRuleTestType<BlockTest> TYPE =
            new PositionRuleTestType<>(BlockTest.CODEC, BlockTest.NAME);

    @Override
    public PositionRuleTestType<BlockTest> getType() {
        return TYPE;
    }

    @Override
    public boolean test(Holder<Biome> biome, BlockPos pos, Level level) {
        return predicate.test(level.getBlockState(pos.offset(offset)), new Random(Mth.getSeed(pos)));
    }
}
