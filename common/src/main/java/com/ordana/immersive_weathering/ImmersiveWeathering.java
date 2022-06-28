package com.ordana.immersive_weathering;

import com.ordana.immersive_weathering.block_growth.rute_test.BlockSetMatchTest;
import com.ordana.immersive_weathering.block_growth.rute_test.BurnableTest;
import com.ordana.immersive_weathering.block_growth.rute_test.FluidMatchTest;
import com.ordana.immersive_weathering.block_growth.rute_test.LogMatchTest;
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

    public static void init(){

        ModCompostable.setup();
        FluidMatchTest.setup();
        LogMatchTest.setup();
        BlockSetMatchTest.setup();
        BurnableTest.init();
    }

    //hanging roots item override (mixin for fabric override for forge)
    //RE add lightning strike growths
}
