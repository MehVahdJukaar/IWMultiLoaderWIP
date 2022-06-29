package com.ordana.immersive_weathering.configs;

import com.ordana.immersive_weathering.platform.CommonPlatform;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CommonConfigs {


    private static Supplier<Boolean> BLOCK_GROWTHS;

    private static Supplier<Double> MOSS_INTERESTS_FOR_FACE;
    private static Supplier<Double> MOSS_PATCHINESS;
    private static Supplier<Double> MOSS_IMMUNE_CHANCE;
    private static Supplier<Boolean> MOSS_NEEDS_AIR;

    private static Supplier<Double> CRACK_INTERESTS_FOR_FACE;
    private static Supplier<Double> CRACK_PATCHINESS;
    private static Supplier<Double> CRACK_IMMUNE_CHANCE;
    private static Supplier<Boolean> CRACK_NEEDS_AIR;

    private static Supplier<Boolean> FALLING_ICICLES;
    private static Supplier<Boolean> ICICLE_RARITY;

    private static Supplier<Integer> FREEZING_WATER_SEVERITY;
    private static Supplier<Integer> FREEZING_ICICLE_SEVERITY;
    private static Supplier<Integer> FREEZING_PERMAFROST_SEVERITY;

    private static Supplier<Boolean> FIRE_CHARS_WOOD;
    private static Supplier<Boolean> ASH_SPAWNS;
    private static Supplier<Boolean> SOOT_SPAWN;
    private static Supplier<Boolean> FLAMMABLE_COBWEBS;

    public static void init() {
        ConfigBuilderWrapper builder = CommonPlatform.getConfigBuilder("server");

        builder.push("general");
        BLOCK_GROWTHS = builder.define("block_growths", true);
        builder.pop();

        builder.push("mossy_blocks");
        MOSS_INTERESTS_FOR_FACE = builder.define("interest_for_face", 0.3, 0, 1d);
        MOSS_PATCHINESS = builder.define("patchiness", 0.5, 0, 1);
        MOSS_IMMUNE_CHANCE = builder.define("immune_chance", 0.4, 0, 1);
        MOSS_NEEDS_AIR = builder.define("needs_air", true);
        builder.pop();

        builder.push("cracked_blocks");
        MOSS_INTERESTS_FOR_FACE = builder.define("interest_for_face", 0.6, 0, 1d);
        MOSS_PATCHINESS = builder.define("patchiness", 0.4, 0, 1);
        MOSS_IMMUNE_CHANCE = builder.define("immune_chance", 0.4, 0, 1);
        MOSS_NEEDS_AIR = builder.define("needs_air", false);
        builder.pop();

        builder.push("icicle");
        FALLING_ICICLES = builder.define("react_to_vibrations", true);
        ICICLE_RARITY = builder.define("spawn_rarity", true);
        builder.pop();

        builder.push("freezing");
        //all these are disabled when at 0 of course
        FREEZING_WATER_SEVERITY = builder.define("water_severity", 300, 0, 1000);
        FREEZING_ICICLE_SEVERITY = builder.define("icicle", 300, 0, 1000);
        FREEZING_PERMAFROST_SEVERITY = builder.define("permafrost", 300, 0, 1000);
        builder.pop();

        builder.push("charring");
        FIRE_CHARS_WOOD = builder.define("fire_chars_wood", true);
        ASH_SPAWNS = builder.define("ash_spawn", true);
        SOOT_SPAWN = builder.define("soot_spawn", true);
        FLAMMABLE_COBWEBS = builder.define("flammable_cobweb", true);
        builder.pop();



        //fabric specific
        CommonPlatform.getPlatform().ifFabric(() -> {


        });
    }


    //stuff belows represents the configs that need to be added


    //moss


    // todo: LEAF_PILES_BLACKLIST tag


    //leaf piles


    public static LeafPileMode fallenLeafPiles() {
        throw new AssertionError();
    }

    public static int leafPileMaxHeight() {
        throw new AssertionError();
    }


    public static int leafPileReach() {
        throw new AssertionError();
    }

    public enum LeafPileMode {
        LEAF_LAYER, SIMPLE, OFF
    }


    @Nullable
    public static boolean leggingsPreventThornDamage() {
        ;
        throw new AssertionError();
    }


    public static boolean composterDropsDirt() {
        throw new AssertionError();
    }


    public static boolean featherFallingFarmers() {
        throw new AssertionError();
    }

    //TODO: fix campfire soot onto non burnable
    //fire stuff


    public static boolean vitrifiedSand() {
        throw new AssertionError();
    }


    public static boolean fulgurite() {
        throw new AssertionError();
    }

    //frost


    public static boolean thinIceMelting() {
        throw new AssertionError();
    }

    public static boolean naturalIceMelt() { //?
        throw new AssertionError();
    }

    public static boolean iciclePlacement() { //?
        throw new AssertionError();
    }


    //food


    public static boolean icicleFood() {
        throw new AssertionError();
    }

    public static ForgeConfigSpec.DoubleValue MOSS_CLUMP_CHANCE;
    public static ForgeConfigSpec.BooleanValue ICICLE_FIRE_RESISTANCE;

    public static boolean pondWaterEnabled() {
        throw new AssertionError();
    }


    //these will be data driven
    public boolean lightningCreateMagma = true; //bg
    public boolean lightningCreateLava = true; //bg
    public boolean lightningCreateVitrifiedSand = true;


    public static boolean leafDecaySound() {
        throw new AssertionError();
    }


    //TODO: turn into datapack. dont bother adding
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

    //bark


    public static boolean barkEnabled() {
        throw new AssertionError();
    }


    public static boolean genericBark() {
        throw new AssertionError();
    }

    @Config(name = "item_uses_config")
    public static final class ItemUsesConfig implements ConfigData {
        //TODO: add these
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

    //not needed on forge which is data driven. for fanbric
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


        public static ForgeConfigSpec.BooleanValue CRACK_REQUIRES_SHIFTING;

        public static ForgeConfigSpec.BooleanValue VITRIFIED_LAVA;

        public static ForgeConfigSpec.BooleanValue BLOCK_GROWTH;

    }


    //here are forge old config values. only here for their old description


    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("mossy_blocks");
        MOSS_INTEREST_FOR_FACE = builder.comment("How likely each block face is to propagate its mossy state to its neighbor." +
                        "Set to 0 to completely disable mossification")
                .defineInRange("interest_for_face", 0.3, 0, 1);
        MOSS_DISJOINT_GROWTH = builder.comment("Determines how likely a moss patch is to spread in a non uniform manner allowing more distant blocks to be affected by eachother." +
                        "In more in depth terms this makes it so a block will be affected by neighbors with WEATHERING state set to true" +
                        "as opposed to only already mossy blocks or moss source blocks")
                .defineInRange("disjoint_growth_chance", 0.5, 0, 1);
        MOSS_UN_WEATHERABLE_CHANCE = builder.comment("Determines the percentage of blocks that will not be allowed to weather if not directly next to a moss source block." +
                        "The actual shape of a moss patch is influenced by all 3 of these values")
                .defineInRange("un_weatherable_chance", 0.4, 0, 1);
        MOSS_NEEDS_AIR = builder.comment("If a block needs to be exposed to air to be able to weather. " +
                        "Currently moss sources blocks ignore this check")
                .define("needs_air", true);
        builder.pop();
        builder.push("cracked_blocks");
        CRACK_INTEREST_FOR_FACE = builder.comment("How likely each block face is to propagate its cracked state to its neighbor." +
                        "Set to 0 to completely disable cracking")
                .defineInRange("interest_for_face", 0.6, 0, 1);
        CRACK_DISJOINT_GROWTH = builder.comment("Determines how likely a crack patch is to spread in a non uniform manner allowing more distant blocks to be affected by eachother." +
                        "In more in depth terms this makes it so a block will be affected by neighbors with WEATHERING state set to true" +
                        "as opposed to only already cracked blocks or crack source blocks")
                .defineInRange("disjoint_growth_chance", 0.4, 0, 1);
        CRACK_UN_WEATHERABLE_CHANCE = builder.comment("Determines the percentage of blocks that will not be allowed to weather if not directly next to a moss source block." +
                        "The actual shape of a crack patch is influenced by all 3 of these values")
                .defineInRange("unWeatherable_chance", 0.5, 0, 1);
        CRACK_NEEDS_AIR = builder.comment("If a block needs to be exposed to air to be able to weather. " +
                        "Currently crack sources blocks ignore this check")
                .define("needs_air", false);
        CRACK_REQUIRES_SHIFTING = builder.comment("If crating a cracked block by clicking on it with a pickaxe requires shifting or not")
                .define("pickaxe_cracking_requires_shifting", false);
        builder.pop();

        builder.push("bark");
        BARK_ENABLED = builder.comment("Allows bark to be dropped after scraping off log blocks")
                .define("enabled", true);
        GENERIC_BARK = builder.comment("If you dont like having a bark item for each wood type, write here a valid item id and it will be used as a generic bark instead. Note that existing bark items will not be hidden in creative inventory")
                .define("generic_bark_id", "");
        builder.pop();

        builder.push("icicles");
        ICICLES_PATCHES = builder.comment("Enables icicle patches features to spawn in icy biomes and caves")
                .define("icicle_patches", true);
        ICICLES_FALLING = builder.comment("Allows icicles to fall when a loud sound is player nearby")
                .define("fall_on_vibrations", true);
        ICICLES_GENERATION_RARITY = builder.comment("Allows icicles to naturally generate on the underside of blocks when it snows." +
                        "Determines the how many blocks on average an icicle can generate in. The higher the rarer. Set to 1001 to disable entirely")
                .defineInRange("icicle_formation", 12, 1, 1001);
        builder.pop();

        builder.push("food").comment("some of these arent working yet");
        MOSS_CLUMP_CHANCE = builder.comment("Chance that a moss clump will give regeneration effect")
                .defineInRange("moss_regeneration_chance", 0.3, 0, 1);
        ICICLE_FIRE_RESISTANCE = builder.comment("Eaten icicles will give a short fire resistance buff")
                .define("icicle_fire_resistance", true);
        ICICLE_FOOD = builder.comment("Allows icicles to be eaten")
                .define("icicle_food", true);
        builder.pop();

        builder.push("leaf_piles");
        LEAF_PILES_PATCHES = builder.comment("Enables leaf piles patches features to spawn in forests. Currently does nothing as they have been temporarily (or not) yeeted")
                .define("leaf_piles_patches", true);
        HUMUS_SPAWN_BELOW_LEAVES = builder.comment("Allows natural humus or podzol generation below leaf piles with more than 1 layer")
                .defineInRange("humus_and_podzol_spawn_chance", 0.02, 0, 1);
        FALLING_LEAVES = builder.comment("Chance for leaf piles to spawn below leaves")
                .defineInRange("fallen_leaves_chance", 0.005, 0, 1);
        MAX_LEAF_PILE_HEIGHT = builder.comment("Maximum height that leaf piles can naturally pile up to." +
                        "Refers to the previously defined falling leaves feature")
                .defineInRange("fallen_leaves_height", 3, 1, 8);
        LEAF_PILE_REACH = builder.comment("Maximum height at which a leaf block can generate a leaf pile below")
                .defineInRange("fallen_leaves_reach", 12, 1, 256);

        LEAF_PILES_BLACKLIST = builder.comment("Leaves Block ids that should not spawn from leaves (i.e: minecraftoak_leaves)")
                .defineList("mod_blacklist",
                        List.of(""), o -> o instanceof String);
        builder.pop();

        builder.push("vitrified_sand");
        VITRIFIED_LIGHTNING = builder.comment("Allows lightning to create vitrified sand")
                .define("from_lightning", true);
        FULGURITE = builder.comment("Fulgurite will spawn on top of vitrified sand created by a lightning bolt")
                .define("fulgurite", true);
        VITRIFIED_LAVA = builder.comment("Allows lava to create vitrified sand")
                .define("from_lava", true);
        builder.pop();

        builder.push("soil_blocks");
        HUMUS_PATCHES = builder.comment("Enables leaf piles patches features to spawn in dark oak forests")
                .define("humus_patches", true);
        builder.pop();

        BLOCK_GROWTH = builder.comment("Enable data driven block growths. This is just a global toggle, to have more control over each block growth you can use a datapack to override the existing jsons")
                .define("block_growths_enabled", true);

        SPEC = builder.build();
    }


}
