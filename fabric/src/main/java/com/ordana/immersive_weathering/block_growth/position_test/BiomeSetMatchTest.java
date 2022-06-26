package com.ordana.immersive_weathering.block_growth.position_test;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

record BiomeSetMatchTest(RegistryEntryList<Biome> biomes) implements PositionRuleTest {

    public static final String NAME = "biome_match";
    public static final Codec<BiomeSetMatchTest> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.entryList(Registry.BIOME_KEY).fieldOf("biomes").forGetter(BiomeSetMatchTest::biomes)
    ).apply(instance, BiomeSetMatchTest::new));
    static final PositionRuleTestType<BiomeSetMatchTest> TYPE = new PositionRuleTestType<>(BiomeSetMatchTest.CODEC, BiomeSetMatchTest.NAME);

    @Override
    public PositionRuleTestType<BiomeSetMatchTest> getType() {
        return TYPE;
    }

    @Override
    public boolean test(RegistryEntry<Biome> biome, BlockPos pos, World world) {
        return biomes.contains(biome);
    }
}
