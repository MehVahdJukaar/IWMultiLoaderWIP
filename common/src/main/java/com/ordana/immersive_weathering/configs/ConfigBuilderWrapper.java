package com.ordana.immersive_weathering.configs;

import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class ConfigBuilderWrapper {

    private final String name;

    public ConfigBuilderWrapper(String name) {
        this.name = name;
    }

    ;

    public String getName() {
        return name;
    }

    public abstract ConfigBuilderWrapper push(String category);

    public abstract ConfigBuilderWrapper pop();

    public abstract Supplier<Boolean> define(String name, String tooltip, boolean defaultValue);

    public abstract Supplier<Double> define(String name, String tooltip, double defaultValue, double min, double max);

    public abstract Supplier<Integer> define(String name, String tooltip, int defaultValue, int min, int max);

    public abstract Supplier<String> define(String name, String tooltip, String defaultValue);

}
