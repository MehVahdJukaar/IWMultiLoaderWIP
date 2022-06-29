package com.ordana.immersive_weathering.configs;

import java.util.function.Supplier;

public abstract class ConfigBuilderWrapper {

    private final String name;

    public ConfigBuilderWrapper(String name){
        this.name = name;
    };

    public abstract ConfigBuilderWrapper push(String category);

    public abstract ConfigBuilderWrapper pop();

    public abstract Supplier<Boolean> define(String name, String translation, boolean defaultValue);


}
