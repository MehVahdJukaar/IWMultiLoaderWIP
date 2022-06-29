package com.ordana.immersive_weathering.configs;

import net.minecraft.network.chat.TranslatableComponent;

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

    public abstract Supplier<Boolean> define(String name, boolean defaultValue);

    public abstract Supplier<Double> define(String name,  double defaultValue, double min, double max);

    public abstract Supplier<Integer> define(String name, int defaultValue, int min, int max);

    public abstract Supplier<String> define(String name, String defaultValue);

    public abstract <V extends Enum<V>> Supplier<V> define(String name, V defaultValue);



    protected TranslatableComponent description(String name){
        return new TranslatableComponent("text.immersive_weathering."+name);
    }
    protected TranslatableComponent tooltip(String name){
        return new TranslatableComponent("text.immersive_weathering."+name+".description");
    }
}
