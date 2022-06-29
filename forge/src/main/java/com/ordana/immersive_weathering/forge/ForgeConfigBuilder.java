package com.ordana.immersive_weathering.forge;

import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ForgeConfigBuilder extends ConfigBuilderWrapper {


    private final ForgeConfigSpec.Builder builder;

    public ForgeConfigBuilder(String name) {
        super(name);
       this.builder = new ForgeConfigSpec.Builder();
    }

    @Override
    public ForgeConfigBuilder push(String category) {
        builder.push(category);
        return this;
    }

    @Override
    public ForgeConfigBuilder pop() {
        builder.pop();
        return this;
    }

    @Override
    public Supplier<Boolean> define(String name, String tooltip, boolean defaultValue) {
        var value = builder.translation(tooltip).define(name, defaultValue);
        return value::get;
    }

    @Override
    public Supplier<Double> define(String name, String tooltip, double defaultValue, double min, double max) {
        var value = builder.translation(tooltip).defineInRange(name, defaultValue, min, max);
        return value::get;
    }

    @Override
    public Supplier<Integer> define(String name, String tooltip, int defaultValue, int min, int max) {
        var value = builder.translation(tooltip).defineInRange(name, defaultValue, min, max);
        return value::get;
    }

    @Override
    public Supplier<String> define(String name, String tooltip, String defaultValue) {
        ForgeConfigSpec.ConfigValue<String> value = builder.translation(tooltip).define(name, defaultValue);
        return value::get;
    }
}
