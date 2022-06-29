package com.ordana.immersive_weathering.forge;

import com.ordana.immersive_weathering.configs.ConfigBuilderWrapper;
import net.minecraftforge.common.ForgeConfigSpec;

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
    public Supplier<Boolean> define(String name, boolean defaultValue) {
        var value = builder.translation(tooltip(name).getKey()).define(name, defaultValue);
        return value::get;
    }

    @Override
    public Supplier<Double> define(String name, double defaultValue, double min, double max) {
        var value = builder.translation(tooltip(name).getKey()).defineInRange(name, defaultValue, min, max);
        return value::get;
    }

    @Override
    public Supplier<Integer> define(String name, int defaultValue, int min, int max) {
        var value = builder.translation(tooltip(name).getKey()).defineInRange(name, defaultValue, min, max);
        return value::get;
    }

    @Override
    public Supplier<String> define(String name, String defaultValue) {
        ForgeConfigSpec.ConfigValue<String> value = builder.translation(tooltip(name).getKey()).define(name, defaultValue);
        return value::get;
    }

    @Override
    public <V extends Enum<V>> Supplier<V> define(String name, V defaultValue) {
        var value = builder.translation(tooltip(name).getKey()).defineEnum(name, defaultValue);
        return value::get;
    }
}
