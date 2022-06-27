package com.ordana.immersive_weathering.platform;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigPlatform {

    public static class Client {

    }

    public static class Server {
        public static ForgeConfigSpec SPEC;

        // "leaves_config"
        public static ForgeConfigSpec.BooleanValue fallingLeafParticles;
        public static ForgeConfigSpec.BooleanValue leafDecayPiles;
        public static ForgeConfigSpec.BooleanValue leafDecayParticles;
        public static ForgeConfigSpec.BooleanValue leafDecaySound;
        public static ForgeConfigSpec.BooleanValue leafPilesForm;
        public static ForgeConfigSpec.BooleanValue leafPilesConvertBlockBelow;
        public static ForgeConfigSpec.BooleanValue leggingsPreventThornDamage;
        public static ForgeConfigSpec.BooleanValue composterDropsDirt;
        public static ForgeConfigSpec.BooleanValue mulchGrowsCrops;
        public static ForgeConfigSpec.BooleanValue featherFallingFarmer;

        // "fire_and_ice_config"
        public static ForgeConfigSpec.BooleanValue fireCharsWood;
        public static ForgeConfigSpec.BooleanValue campfiresCreateSoot;
        public static ForgeConfigSpec.BooleanValue lightningCreateMagma;
        public static ForgeConfigSpec.BooleanValue lightningCreateLava;
        public static ForgeConfigSpec.BooleanValue lightningCreateVitrifiedSand;
        public static ForgeConfigSpec.BooleanValue flammableCobwebs;
        public static ForgeConfigSpec.BooleanValue freezingWater;
        public static ForgeConfigSpec.IntValue freezingWaterSeverity;
        public static ForgeConfigSpec.BooleanValue permafrostFreezing;
        public static ForgeConfigSpec.IntValue freezingPermafrostSeverity;
        public static ForgeConfigSpec.BooleanValue icicleFreezing;
        public static ForgeConfigSpec.IntValue freezingIcicleSeverity;
        public static ForgeConfigSpec.BooleanValue thinIceFormation;
        public static ForgeConfigSpec.BooleanValue thinIceMelting;
        public static ForgeConfigSpec.BooleanValue glassFrosting;
        public static ForgeConfigSpec.BooleanValue grassFrosting;
        public static ForgeConfigSpec.BooleanValue naturalIceMelt;
        public static ForgeConfigSpec.BooleanValue iciclePlacement;

        // "block_growth_config"
        public static ForgeConfigSpec.BooleanValue blockGrowth;
        public static ForgeConfigSpec.BooleanValue blockCracking;
        public static ForgeConfigSpec.BooleanValue blockMossing;
        public static ForgeConfigSpec.BooleanValue blockRusting;

        // "generators_config"
        public static ForgeConfigSpec.BooleanValue allGenerators;
        public static ForgeConfigSpec.BooleanValue basaltGenerator;
        public static ForgeConfigSpec.BooleanValue deepslateGenerator;
        public static ForgeConfigSpec.BooleanValue graniteGenerator;
        public static ForgeConfigSpec.BooleanValue andesiteGenerator;
        public static ForgeConfigSpec.BooleanValue dioriteGenerator;
        public static ForgeConfigSpec.BooleanValue tuffGenerator;
        public static ForgeConfigSpec.BooleanValue blackstoneGenerator;
        public static ForgeConfigSpec.BooleanValue magmaGenerator;
        public static ForgeConfigSpec.BooleanValue terracottaGenerator;
        public static ForgeConfigSpec.BooleanValue vitrifiedSandGenerator;
        public static ForgeConfigSpec.BooleanValue cryingObsidianGenerator;
        public static ForgeConfigSpec.BooleanValue iceGenerator;

        // "item_uses_config"
        public static ForgeConfigSpec.BooleanValue cauldronWashing;
        public static ForgeConfigSpec.BooleanValue pistonSliming;
        public static ForgeConfigSpec.BooleanValue soilShearing;
        public static ForgeConfigSpec.BooleanValue azaleaShearing;
        public static ForgeConfigSpec.BooleanValue mossShearing;
        public static ForgeConfigSpec.BooleanValue mossBurning;
        public static ForgeConfigSpec.BooleanValue charredBlockIgniting;
        public static ForgeConfigSpec.BooleanValue shovelExtinguishing;
        public static ForgeConfigSpec.BooleanValue spongeRusting;
        public static ForgeConfigSpec.BooleanValue pickaxeCracking;
        public static ForgeConfigSpec.BooleanValue axeStripping;
        public static ForgeConfigSpec.BooleanValue axeScraping;

        // "worldgen_config"
        public static ForgeConfigSpec.BooleanValue icicleFeature;
        public static ForgeConfigSpec.BooleanValue cryosolFeature;
        public static ForgeConfigSpec.BooleanValue humusFeature;
        public static ForgeConfigSpec.BooleanValue rootsFeature;
        public static ForgeConfigSpec.BooleanValue fluvisolFeature;
        public static ForgeConfigSpec.BooleanValue siltFeature;
        public static ForgeConfigSpec.BooleanValue lakebedFeature;
        public static ForgeConfigSpec.BooleanValue vertisolFeature;
        public static ForgeConfigSpec.BooleanValue oakLeavesFeature;
        public static ForgeConfigSpec.BooleanValue darkLeavesFeature;
        public static ForgeConfigSpec.BooleanValue birchLeavesFeature;
        public static ForgeConfigSpec.BooleanValue spruceLeavesFeature;

        static {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
            builder.push("leaves_config");
            fallingLeafParticles = builder.comment("Enable falling leaf particles").define("fallingLeafParticles", true);
            leafDecayPiles = builder.comment("Enable leaf pile decay").define("leafDecayPiles", true);
            leafDecayParticles = builder.comment("Enable leaf pile decay particles").define("leafDecayParticles", true);
            leafDecaySound = builder.comment("Enable leaf pile decay sound").define("leafDecaySound", true);
            leafPilesForm = builder.comment("Enable leaf pile formation").define("leafPilesForm", true);
            leafPilesConvertBlockBelow = builder.comment("Enable leaf pile conversion").define("leafPilesConvertBlockBelow", true);
            leggingsPreventThornDamage = builder.comment("Enable leggings prevent thorn damage").define("leggingsPreventThornDamage", true);
            composterDropsDirt = builder.comment("Enable composter drops dirt").define("composterDropsDirt", true);
            mulchGrowsCrops = builder.comment("Enable mulch grows crops").define("mulchGrowsCrops", true);
            featherFallingFarmer = builder.comment("Enable feather falling farmer").define("featherFallingFarmer", true);
            builder.pop();

            builder.push("fire_and_ice_config");
            fireCharsWood = builder.comment("Enable fire chars wood").define("fireCharsWood", true);
            campfiresCreateSoot = builder.comment("Enable campfires create soot").define("campfiresCreateSoot", true);
            lightningCreateMagma = builder.comment("Enable lightning create magma").define("lightningCreateMagma", true);
            lightningCreateLava = builder.comment("Enable lightning create lava").define("lightningCreateLava", true);
            lightningCreateVitrifiedSand = builder.comment("Enable lightning create vitrified sand").define("lightningCreateVitrifiedSand", true);
            flammableCobwebs = builder.comment("Enable flammable cobwebs").define("flammableCobwebs", true);
            freezingWater = builder.comment("Enable freezing water").define("freezingWater", true);
            freezingWaterSeverity = builder.comment("Severity of freezing water").defineInRange("freezingWaterSeverity", 200, 0, 1000);
            permafrostFreezing = builder.comment("Enable permafrost freezing").define("permafrostFreezing", true);
            freezingPermafrostSeverity = builder.comment("Severity of permafrost freezing").defineInRange("freezingPermafrostSeverity", 200, 0, 1000);
            icicleFreezing = builder.comment("Enable icicle freezing").define("icicleFreezing", true);
            freezingIcicleSeverity = builder.comment("Severity of icicle freezing").defineInRange("freezingIcicleSeverity", 300, 0, 1000);
            thinIceFormation = builder.comment("Enable thin ice formation").define("thinIceFormation", true);
            thinIceMelting = builder.comment("Enable thin ice melting").define("thinIceMelting", true);
            glassFrosting = builder.comment("Enable glass frosting").define("glassFrosting", true);
            grassFrosting = builder.comment("Enable grass frosting").define("grassFrosting", true);
            naturalIceMelt = builder.comment("Enable natural ice melt").define("naturalIceMelt", true);
            iciclePlacement = builder.comment("Enable icicle placement").define("iciclePlacement", true);
            builder.pop();

            builder.push("block_growth_config");
            blockGrowth = builder.comment("Enable block growth").define("blockGrowth", true);
            blockCracking = builder.comment("Enable block cracking").define("blockCracking", true);
            blockMossing = builder.comment("Enable block mossing").define("blockMossing", true);
            blockRusting = builder.comment("Enable block rusting").define("blockRusting", true);
            builder.pop();

            builder.push("generators_config");
            allGenerators = builder.comment("Enable all generators").define("allGenerators", true);
                    basaltGenerator = builder.comment("Enable basalt generator").define("basaltGenerator", true);
            deepslateGenerator = builder.comment("Enable deep slate generator").define("deepslateGenerator", true);
                    graniteGenerator = builder.comment("Enable granite generator").define("graniteGenerator", true);
            andesiteGenerator = builder.comment("Enable andesite generator").define("andesiteGenerator", true);
                    dioriteGenerator = builder.comment("Enable diorite generator").define("dioriteGenerator", true);
            tuffGenerator = builder.comment("Enable tuff generator").define("tuffGenerator", true);
                    blackstoneGenerator = builder.comment("Enable blackstone generator").define("blackstoneGenerator", true);
            magmaGenerator = builder.comment("Enable magma generator").define("magmaGenerator", true);
                    terracottaGenerator = builder.comment("Enable terracotta generator").define("terracottaGenerator", true);
            vitrifiedSandGenerator = builder.comment("Enable vitrified sand generator").define("vitrifiedSandGenerator", true);
                    cryingObsidianGenerator = builder.comment("Enable crying obsidian generator").define("cryingObsidianGenerator", true);
            iceGenerator = builder.comment("Enable ice generator").define("iceGenerator", true);
            builder.pop();

            builder.push("item_uses_config");
            cauldronWashing = builder.comment("Enable cauldron washing").define("cauldronWashing", true);
                    pistonSliming = builder.comment("Enable piston sliming").define("pistonSliming", true);
            soilShearing = builder.comment("Enable soil shearing").define("soilShearing", true);
                    azaleaShearing = builder.comment("Enable azalea shearing").define("azaleaShearing", true);
            mossShearing = builder.comment("Enable moss shearing").define("mossShearing", true);
                    mossBurning = builder.comment("Enable moss burning").define("mossBurning", true);
            charredBlockIgniting = builder.comment("Enable charred block igniting").define("charredBlockIgniting", true);
                    shovelExtinguishing = builder.comment("Enable shovel extinguishing").define("shovelExtinguishing", true);
            spongeRusting = builder.comment("Enable sponge rusting").define("spongeRusting", true);
                    pickaxeCracking = builder.comment("Enable pickaxe cracking").define("pickaxeCracking", true);
            axeStripping = builder.comment("Enable axe stripping").define("axeStripping", true);
                    axeScraping = builder.comment("Enable axe scraping").define("axeScraping", true);
            builder.pop();

            builder.push("worldgen_config");
            icicleFeature = builder.comment("Enable icicle feature").define("icicleFeature", true);
                    cryosolFeature = builder.comment("Enable cryosol feature").define("cryosolFeature", true);
            humusFeature = builder.comment("Enable humus feature").define("humusFeature", true);
                    rootsFeature = builder.comment("Enable roots feature").define("rootsFeature", true);
            fluvisolFeature = builder.comment("Enable fluvisol feature").define("fluvisolFeature", true);
                    siltFeature = builder.comment("Enable silt feature").define("siltFeature", true);
            lakebedFeature = builder.comment("Enable lakebed feature").define("lakebedFeature", true);
                    vertisolFeature = builder.comment("Enable vertisol feature").define("vertisolFeature", true);
            oakLeavesFeature = builder.comment("Enable oak leaves feature").define("oakLeavesFeature", true);
                    darkLeavesFeature = builder.comment("Enable dark leaves feature").define("darkLeavesFeature", true);
            birchLeavesFeature = builder.comment("Enable birch leaves feature").define("birchLeavesFeature", true);
                    spruceLeavesFeature = builder.comment("Enable spruce leaves feature").define("spruceLeavesFeature", true);
            builder.pop();

            SPEC = builder.build();
        }
    }
}
