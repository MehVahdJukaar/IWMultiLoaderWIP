package com.ordana.immersive_weathering.client.forge;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.client.ImmersiveWeatheringClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = ImmersiveWeathering.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientInit {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        ImmersiveWeatheringClient.initClient();
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ImmersiveWeatheringClient.onRegisterEntityRenderTypes(event::registerEntityRenderer);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        ImmersiveWeatheringClient.onRegisterParticles(ClientInit::registerParticle);
    }

    private static <T extends ParticleOptions> void registerParticle(ParticleType<T> type, Function<SpriteSet,
            ParticleProvider<T>> registration) {
        ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
        particleEngine.register(type, registration::apply);
    }





}