package com.ordana.immersive_weathering.client.fabric;

import com.ordana.immersive_weathering.client.ImmersiveWeatheringClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ImmersiveWeatheringClient.initClient();
        ImmersiveWeatheringClient.onRegisterEntityRenderTypes(EntityRendererRegistry::register);
    }
}
