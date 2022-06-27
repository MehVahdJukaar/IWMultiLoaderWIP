package com.ordana.immersive_weathering.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

//this will be messy
public class ConfigPlatform {

    //moss

    @ExpectPlatform
    public static double mossInterestForFace() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static double mossPatchiness() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static double mossImmuneChance() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean mossNeedsAir() {
        throw new AssertionError();
    }

    //cracking

    @ExpectPlatform
    public static double crackInterestForFace() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static double crackPatchiness() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static double crackImmuneChance() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean crackNeedsAir() {
        throw new AssertionError();
    }

    //bark

    @ExpectPlatform
    public static boolean barkEnabled() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean genericBark() {
        throw new AssertionError();
    }

    //leaf piles

    @ExpectPlatform
    public static LeafPileMode fallenLeafPiles() {
        throw new AssertionError();
    }
    // todo: LEAF_PILES_BLACKLIST tag

    @ExpectPlatform
    public static boolean fallingIcicles() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int icicleRarity() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean blockGrowths() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int leafPileMaxHeight() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int leafPileReach() {
        throw new AssertionError();
    }

    public enum LeafPileMode{
        LEAF_LAYER, SIMPLE,OFF
    }

    @ExpectPlatform
    public static double humusSpawnBelowChance() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean leggingsPreventThornDamage() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean composterDropsDirt() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean featherFallingFarmers() {
        throw new AssertionError();
    }

    //TODO: fix campfire soot onto non burnable
    //fire stuff

    @ExpectPlatform
    public static boolean fireCharsWood() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean ashSpawns() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean sootSpawns() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean flammableCobwebs() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean vitrifiedSand() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean fulgurite() {
        throw new AssertionError();
    }

    //frost

    @ExpectPlatform
    public static boolean freezingWater() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int freezingWaterSeverity() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean permafrostFreezing() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int freezingPermafrostSeverity() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean icicleFreezing() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int freezingIcicleSeverity() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean thinIceFormation() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean thinIceMelting() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean glassFrosting() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean naturalIceMelt() { //?
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean iciclePlacement() { //?
        throw new AssertionError();
    }


    //food

    @ExpectPlatform
    public static boolean icicleFood() {
        throw new AssertionError();
    }


    public boolean lightningCreateMagma = true; //bg
    public boolean lightningCreateLava = true; //bg
    public boolean lightningCreateVitrifiedSand = true;


    //client configs

    @ExpectPlatform
    public static boolean fallingLeafParticles() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean leafPilesParticles() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean leafDecaySound() {
        throw new AssertionError();
    }



    //TODO: turn into datapack
@Config(name = "generators_config")
public static final class GeneratorsConfig implements ConfigData {

    public boolean allGenerators = true;
    public boolean mossBurning = true;
    public boolean basaltGenerator = true;
    public boolean deepslateGenerator = true;
    public boolean graniteGenerator = true;
    public boolean andesiteGenerator = true;
    public boolean dioriteGenerator = true;
    public boolean tuffGenerator = true;
    public boolean blackstoneGenerator = true;
    public boolean magmaGenerator = true;
    public boolean terracottaGenerator = true;
    public boolean vitrifiedSandGenerator = true;
    public boolean cryingObsidianGenerator = true;
    public boolean iceGenerator = true;

    private GeneratorsConfig() {
    }
}

@Config(name = "item_uses_config")
public static final class ItemUsesConfig implements ConfigData {

    public boolean cauldronWashing = false;
    public boolean pistonSliming = true;
    public boolean soilShearing = true;
    public boolean azaleaShearing = true;
    public boolean mossShearing = true;
    public boolean mossBurning = true;
    public boolean charredBlockIgniting = true;
    public boolean shovelExtinguishing = true;
    public boolean spongeRusting = true;
    public boolean pickaxeCracking = true;
    public boolean axeStripping = true;
    public boolean axeScraping = true;

    private ItemUsesConfig() {
    }
}

//not needed on forge which is data driven
@Config(name = "worldgen_config")
public static final class WorldgenConfig implements ConfigData {

    public boolean icicleFeature = true;
    public boolean cryosolFeature = true;
    public boolean humusFeature = true;
    public boolean rootsFeature = true;
    public boolean fluvisolFeature = true;
    public boolean siltFeature = true;
    public boolean lakebedFeature = true;
    public boolean vertisolFeature = true;
    public boolean oakLeavesFeature = true;
    public boolean darkLeavesFeature = true;
    public boolean birchLeavesFeature = true;
    public boolean spruceLeavesFeature = true;



    public static ForgeConfigSpec.DoubleValue MOSS_CLUMP_CHANCE;
    public static ForgeConfigSpec.BooleanValue ICICLE_FIRE_RESISTANCE;
    public static ForgeConfigSpec.BooleanValue ICICLE_FOOD;

    public static ForgeConfigSpec.BooleanValue CRACK_REQUIRES_SHIFTING;

    public static ForgeConfigSpec.DoubleValue HUMUS_SPAWN_BELOW_LEAVES;


    public static ForgeConfigSpec.BooleanValue VITRIFIED_LAVA;

    public static ForgeConfigSpec.BooleanValue HUMUS_PATCHES;
    public static ForgeConfigSpec.BooleanValue BLOCK_GROWTH;

}
