package com.ordana.immersive_weathering.mixin;

import com.google.common.collect.ImmutableList;
import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.registry.ModEvents;
import com.ordana.immersive_weathering.registry.ModTags;
import com.ordana.immersive_weathering.registry.blocks.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LiquidBlock.class)
public abstract class FluidMixin extends Block implements BucketPickup {
    private static final IntegerProperty LEVEL;

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (world.getBiome(pos).is(ModTags.ICY) && this.fluid.is(FluidTags.WATER)) {
            if (!(entity instanceof LivingEntity) || EnchantmentHelper.getEnchantmentLevel(Enchantments.FROST_WALKER, (LivingEntity) entity) > 0 || ((LivingEntity) entity).hasEffect(MobEffects.CONDUIT_POWER) || entity.getType().is(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES)) {
                return;
            }
            else if (ImmersiveWeathering.getConfig().fireAndIceConfig.freezingWater && entity.isInWater()) {
                entity.setTicksFrozen(ImmersiveWeathering.getConfig().fireAndIceConfig.freezingWaterSeverity);
            }
        }
    }


    @Shadow
    @Final
    protected FlowingFluid fluid;

    @Shadow public abstract FluidState getFluidState(BlockState state);

    private static final ImmutableList<Direction> FLOW_DIRECTIONS;

    public FluidMixin(Properties settings, FlowingFluid fluid) {
        super(settings);
    }

    private void playExtinguishSound(LevelAccessor world, BlockPos pos) {
        world.levelEvent(1501, pos, 0);
    }

    @Inject(method = "receiveNeighborFluids", at = @At("HEAD"), cancellable = true)
    private void receiveNeighborFluids(Level world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(ImmersiveWeathering.getConfig().generatorsConfig.allGenerators) {
            if (this.fluid.is(FluidTags.LAVA)) {
                boolean hasWater = false;
                boolean blueIceDown = false;
                boolean blueIceUp = false;
                boolean basaltDown = false;
                boolean hasBlueIce = false;
                boolean hasQuartz = false;
                boolean hasDiorite = false;
                boolean hasAsh = false;
                boolean hasMagma = false;
                boolean hasBubbles = false;
                boolean hasSoulfire = false;
                boolean hasClay = false;
                boolean hasSand = false;
                boolean hasRedSand = false;
                boolean hasMossyBlock = false;
                for (Direction direction : UPDATE_SHAPE_ORDER) {
                    BlockPos blockPos = pos.relative(direction.getOpposite());
                    BlockState targetState = world.getBlockState(blockPos);
                    if (world.getFluidState(blockPos).is(FluidTags.WATER)) {
                        hasWater = true;
                    }
                    if (world.getBlockState(pos.below()).is(Blocks.BASALT)) {
                        basaltDown = true;
                    }
                    if (world.getBlockState(pos.below()).is(Blocks.BLUE_ICE)) {
                        blueIceDown = true;
                    }
                    if (world.getBlockState(pos.above()).is(Blocks.BLUE_ICE)) {
                        blueIceUp = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.BLUE_ICE)) {
                        hasBlueIce = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.MAGMA_BLOCK)) {
                        hasMagma = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.SMOOTH_QUARTZ)) {
                        hasQuartz = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.DIORITE)) {
                        hasDiorite = true;
                    }
                    if (world.getBlockState(blockPos).is(ModBlocks.ASH_BLOCK)) {
                        hasAsh = true;
                    }
                    if (world.getBlockState(pos.below()).is(Blocks.BUBBLE_COLUMN)) {
                        hasBubbles = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.SOUL_FIRE)) {
                        hasSoulfire = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.CLAY)) {
                        hasClay = true;
                    }
                    if (world.getBlockState(blockPos).is(ModTags.MOSSY)) {
                        hasMossyBlock = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.SAND)) {
                        hasSand = true;
                    }
                    if (world.getBlockState(blockPos).is(Blocks.RED_SAND)) {
                        hasRedSand = true;
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.basaltGenerator) {
                        if (!world.getFluidState(pos).isSource()) {
                            if (basaltDown) {
                                world.setBlockAndUpdate(pos, Blocks.BASALT.defaultBlockState());
                                this.playExtinguishSound(world, pos);
                                cir.setReturnValue(false);
                            }
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.deepslateGenerator) {
                        if (blueIceDown && blueIceUp) {
                            world.setBlockAndUpdate(pos, Blocks.DEEPSLATE.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.dioriteGenerator) {
                        if (hasWater && hasQuartz) {
                            world.setBlockAndUpdate(pos, Blocks.DIORITE.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.andesiteGenerator) {
                        if (hasWater && hasDiorite) {
                            world.setBlockAndUpdate(pos, Blocks.ANDESITE.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.graniteGenerator) {
                        if (hasWater && hasQuartz && hasDiorite) {
                            world.setBlockAndUpdate(pos, Blocks.GRANITE.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.tuffGenerator) {
                        if (hasWater && hasAsh) {
                            world.setBlockAndUpdate(pos, Blocks.TUFF.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.blackstoneGenerator) {
                        if (hasMagma && hasBlueIce) {
                            world.setBlockAndUpdate(pos, Blocks.BLACKSTONE.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.magmaGenerator) {
                        if (hasBubbles) {
                            world.setBlockAndUpdate(pos, Blocks.MAGMA_BLOCK.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.terracottaGenerator) {
                        if (hasClay) {
                            if (world.getBlockState(blockPos).is(Blocks.CLAY)) {
                                world.setBlockAndUpdate(blockPos, Blocks.TERRACOTTA.defaultBlockState());
                                this.playExtinguishSound(world, pos);
                                cir.setReturnValue(false);
                            }
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.mossBurning) {
                        if (hasMossyBlock) {
                            if (world.getBlockState(blockPos).is(ModTags.MOSSY)) {
                                world.setBlockAndUpdate(blockPos, ModEvents.CLEANED_BLOCKS.get(targetState.getBlock()).withPropertiesOf(targetState));
                                this.playExtinguishSound(world, pos);
                                cir.setReturnValue(false);
                            }
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.vitrifiedSandGenerator) {
                        if (hasSand || hasRedSand) {
                            if (world.getBlockState(blockPos).is(BlockTags.SAND)) {
                                world.setBlockAndUpdate(blockPos, ModBlocks.VITRIFIED_SAND.defaultBlockState());
                                this.playExtinguishSound(world, pos);
                                cir.setReturnValue(false);
                            }
                        }
                    }
                    if(ImmersiveWeathering.getConfig().generatorsConfig.cryingObsidianGenerator) {
                        if (world.getFluidState(blockPos).is(FluidTags.WATER) && hasSoulfire) {
                            world.setBlockAndUpdate(pos, Blocks.CRYING_OBSIDIAN.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                }
            } else if (this.fluid.is(FluidTags.WATER)) {
                boolean hasBlueIce = false;
                if(ImmersiveWeathering.getConfig().generatorsConfig.iceGenerator) {
                    for (Direction direction : UPDATE_SHAPE_ORDER) {
                        BlockPos blockPos = pos.relative(direction.getOpposite());
                        if (world.getBlockState(blockPos).is(Blocks.BLUE_ICE)) {
                            hasBlueIce = true;
                        }
                        if (hasBlueIce) {
                            world.setBlockAndUpdate(pos, Blocks.ICE.defaultBlockState());
                            this.playExtinguishSound(world, pos);
                            cir.setReturnValue(false);
                        }
                    }
                }
            }
        }
    }

    static {
        LEVEL = BlockStateProperties.LEVEL;
        FLOW_DIRECTIONS = ImmutableList.of(Direction.DOWN, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST);
    }

    @Override
    public ItemStack pickupBlock(LevelAccessor world, BlockPos pos, BlockState state) {
        if ((Integer)state.getValue(LEVEL) == 0) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            return new ItemStack(this.fluid.getBucket());
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return this.fluid.getPickupSound();
    }
}
