package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.block_growth.rute_test.*;
import com.ordana.immersive_weathering.configs.CommonConfigs;
import com.ordana.immersive_weathering.reg.*;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImmersiveWeathering {

    public static final String MOD_ID = "immersive_weathering";

    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    //called either on mod creation on fabric or mod setup on forge
    public static void commonInit(){
        CommonConfigs.init();

        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModParticles.init();
        ModFeatures.init();
    }

    public static void commonSetup() {
        ModCompostable.register();

        //rule tests
        FluidMatchTest.register();
        LogMatchTest.register();
        BlockSetMatchTest.register();
        BurnableTest.register();
        BlockPropertyTest.register();
    }

    //hanging roots item override (mixin for fabric override for forge)
    //RE add lightning strike growths
    //TODO: disabled conditional growth. add command system
    //fire mixin
}
