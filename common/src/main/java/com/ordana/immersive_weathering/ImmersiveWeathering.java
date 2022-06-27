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
        ModCompostable.registerCompostable();
        ModFeatures.init();

        FluidMatchTest.init();
        LogMatchTest.init();
        BlockSetMatchTest.init();
        BurnableTest.init();
    }

    //hanging roots item override
    //TODO: re add frosty stuff as growths
    //RE add lightning strike growths
}
