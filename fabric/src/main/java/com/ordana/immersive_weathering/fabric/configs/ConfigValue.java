package com.ordana.immersive_weathering.fabric.configs;

import com.google.gson.JsonObject;

import java.util.function.Supplier;

public abstract class ConfigValue<T> extends ConfigEntry implements Supplier<T> {

    protected final T defaultValue;
    protected T value;

    public ConfigValue(String name, T defaultValue){
        super(name);
        this.defaultValue = defaultValue;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public abstract boolean isValid(T value);

    public void set(T newValue) {
        this.value = newValue;
    }

    @Override
    public T get() {
        return value;
    }
}
