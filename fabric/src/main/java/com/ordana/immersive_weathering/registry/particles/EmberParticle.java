package com.ordana.immersive_weathering.registry.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class EmberParticle extends TextureSheetParticle {

    EmberParticle(ClientLevel world, SpriteSet spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.setSize(0.01F, 0.01F);
        this.pickSprite(spriteProvider);
        this.quadSize *= this.random.nextFloat() * 0.6F + 1F;
        this.lifetime = (int)(10.0D + (Mth.randomBetween(world.random, 0.1f, 0.2f)));
        this.hasPhysics = false;
        this.friction = 0.0F;
        this.gravity = 0.0F;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public record EmberFactory(
            SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            EmberParticle emberParticle = new EmberParticle(clientWorld, this.spriteProvider, d, e, f, 0.0D, 0.0D, 0.0D);
            emberParticle.setBoundingBoxSpacing(0.2F, 0.001F);
            return emberParticle;
        }
    }
}

