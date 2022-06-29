package com.ordana.immersive_weathering.configs;

import com.ordana.immersive_weathering.platform.CommonPlatform;

import java.util.function.Supplier;

public class CommonConfigs {


    private static Supplier<Boolean> BLOCK_GROWTHS;


    public static void init(){
        ConfigBuilderWrapper builder = CommonPlatform.getConfigBuilder("server");

        builder.push("general");
        BLOCK_GROWTHS = builder.define("block_growths", "immersive_weathering.config.block_growths", true);
        builder.pop();
    }
}
