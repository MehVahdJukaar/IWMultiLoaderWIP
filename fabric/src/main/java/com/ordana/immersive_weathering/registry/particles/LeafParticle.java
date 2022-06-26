package com.ordana.immersive_weathering.registry.particles;

import com.mojang.blaze3d.platform.NativeImage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;

@Environment(EnvType.CLIENT)
public class LeafParticle extends TextureSheetParticle {
    private final float rotationSpeed;

    LeafParticle(ClientLevel world, SpriteSet spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ,
                 int color) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.pickSprite(spriteProvider);
        this.getQuadSize(0.0F);
        this.quadSize *= this.random.nextFloat() * 0.2F + 1F;
        this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        this.hasPhysics = true;
        this.friction = 1.0F;
        this.gravity = 1.0F;
        this.rotationSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.setColor(NativeImage.getB((int) color) / 255f,
                NativeImage.getG((int) color) / 255f,
                NativeImage.getR((int) color) / 255f);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.oRoll = this.roll;
            this.roll += 3.1415927F * this.rotationSpeed * 2.0F;
            if (this.onGround) {
                this.oRoll = this.roll = 0.0F;
            }

            this.move(this.xd, this.yd, this.zd);
            this.yd -= 0.003000000026077032D;
            this.yd = Math.max(this.yd, -0.14000000059604645D);
        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public record ColoredLeafParticle(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld, double x, double y, double z, double dx, double dy, double dz) {
            return new LeafParticle(clientWorld, this.spriteProvider, x, y, z, 0.0D, -1D, 0.0D,
                    BiomeColors.getFoliageColor(clientWorld, new BlockPos(x,y,z)));
        }
    }

    public record SpruceLeafParticle(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld, double x, double y, double z, double g, double color, double i) {
            return new LeafParticle(clientWorld, this.spriteProvider, x, y, z, 0.0D, -1D, 0.0D,
                    FoliageColors.getSpruceColor());
        }
    }

    public record BirchLeafParticle(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {
        @Override
        public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld, double x, double y, double z, double g, double color, double i) {
            return new LeafParticle(clientWorld, this.spriteProvider, x, y, z, 0.0D, -1D, 0.0D,
                    FoliageColors.getBirchColor());
        }
    }

    public record SimpleLeafParticle(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {

        @Override
        public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld, double x, double y, double z, double g, double color, double i) {

            return new LeafParticle(clientWorld, this.spriteProvider, x, y, z, 0.0D, -1D, 0.0D,
                    -1);
        }
    }
}
