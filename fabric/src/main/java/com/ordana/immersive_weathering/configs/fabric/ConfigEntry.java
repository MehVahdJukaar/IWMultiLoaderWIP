package com.ordana.immersive_weathering.configs.fabric;

import com.google.gson.JsonObject;

public abstract class ConfigEntry{

    protected final String name;

    public ConfigEntry(String name) {
        this.name = name;
    }

    public abstract void loadFromJson(JsonObject object);

    public abstract void saveToJson(JsonObject object);

    public String getName() {
        return name;
    }
}