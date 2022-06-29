package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.block_growth.rute_test.*;
import com.ordana.immersive_weathering.reg.ModCompostable;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImmersiveWeathering {

    public static final String MOD_ID = "immersive_weathering";

    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static void commonInit(){

        ModCompostable.setup();
        FluidMatchTest.setup();
        LogMatchTest.setup();
        BlockSetMatchTest.setup();
        BurnableTest.setup();
        BlockPropertyTest.setup();
    }

    //hanging roots item override (mixin for fabric override for forge)
    //RE add lightning strike growths
}
