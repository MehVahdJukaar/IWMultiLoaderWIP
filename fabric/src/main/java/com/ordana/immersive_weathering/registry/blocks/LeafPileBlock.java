package com.ordana.immersive_weathering.registry.blocks;

import com.ordana.immersive_weathering.ImmersiveWeathering;
import com.ordana.immersive_weathering.registry.entities.FallingLeafLayerEntity;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class LeafPileBlock extends FallingBlock implements BonemealableBlock {

    public static final IntegerProperty LAYERS = IntegerProperty.create("layers", 0, 8);
    protected static final VoxelShape[] LAYERS_TO_SHAPE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    private static final float[] COLLISIONS = new float[]{0, 1.7f, 1.6f, 1.5f, 1.3f, 1.1f, 0.8f, 0.5f};
    private static final int MAX_LAYERS = 8;

    private final boolean hasFlowers; //if it can be boneMealed
    private final boolean hasThorns; //if it can hurt & make podzol
    private final boolean isLeafy; //if it can make humus
    private final List<SimpleParticleType> particles;

    protected LeafPileBlock(Properties settings, boolean hasFlowers, boolean hasThorns, boolean isLeafy,
                            List<SimpleParticleType> particles) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1));
        this.hasFlowers = hasFlowers;
        this.hasThorns = hasThorns;
        this.isLeafy = isLeafy;
        this.particles = particles;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 1;
    }

    protected int getLayers(BlockState state) {
        return state.getValue(LAYERS);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        int layers = this.getLayers(state);
        return layers > 1;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        BlockPos blockPos;
        BlockState downState = world.getBlockState(pos.below());
        if (random.nextInt(18) == 0 && FallingBlock.isFree(downState) && !downState.is(Blocks.WATER)) {
            if (!(world.getBlockState(pos.below()).getBlock() instanceof LeafPileBlock)) {
                double d = (double) pos.getX() + random.nextDouble();
                double e = (double) pos.getY() - 0.05;
                double f = (double) pos.getZ() + random.nextDouble();
                for (var p : particles) {
                    ParticleUtils.spawnParticlesOnBlockFaces(world, pos.below(), p, UniformInt.of(0, 2));
                }
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        int layers = this.getLayers(state);
        if(ImmersiveWeathering.getConfig().leavesConfig.leafPilesConvertBlockBelow) {
            if (layers > 1) {
                if (this.hasThorns) {
                    if (world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || world.getBlockState(pos.below()).is(Blocks.DIRT) || world.getBlockState(pos.below()).is(Blocks.COARSE_DIRT) || world.getBlockState(pos.below()).is(Blocks.ROOTED_DIRT)) {
                        world.setBlock(pos.below(), Blocks.PODZOL.defaultBlockState(), 3);
                    }
                } else if (this.isLeafy) {
                    if (world.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) || world.getBlockState(pos.below()).is(Blocks.DIRT) || world.getBlockState(pos.below()).is(Blocks.COARSE_DIRT) || world.getBlockState(pos.below()).is(Blocks.ROOTED_DIRT)) {
                        world.setBlock(pos.below(), ModBlocks.HUMUS.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        int layers = this.getLayers(state);

        if (layers > 0) {
            if (entity instanceof LivingEntity && !(entity instanceof Fox || entity instanceof Bee)) {
                float stuck = COLLISIONS[Math.max(0, layers - 1)];
                entity.makeStuckInBlock(state, new Vec3(stuck, stuck, stuck));

                if (layers >= 6 && this.hasThorns) {
                    if (!world.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                        if(entity instanceof Player player && !player.getItemBySlot(EquipmentSlot.LEGS).isEmpty() && ImmersiveWeathering.getConfig().leavesConfig.leggingsPreventThornDamage){
                            return;
                        }
                        else if (entity instanceof Player player) {
                            double d = Math.abs(entity.getX() - entity.xOld);
                            double e = Math.abs(entity.getZ() - entity.zOld);
                            if (d >= 0.003000000026077032D || e >= 0.003000000026077032D) {
                                entity.hurt(DamageSource.SWEET_BERRY_BUSH, 0.5F * (layers - 5));
                            }
                        }
                    }
                }
            }
        }

        //particles
        if (layers > 0 && world.isClientSide && (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this))) {

            Random random = world.getRandom();
            boolean bl = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
            if (bl && random.nextBoolean()) {
                //double yOff = (layers < 5) ? 0.5 : 1;
                double y = pos.getY() + LAYERS_TO_SHAPE[layers].max(Direction.Axis.Y) + 0.0625;
                int color = world.getBiome(pos).value().getFoliageColor();
                for (var p : particles) {
                    world.addParticle(p,
                            entity.getX() +Mth.randomBetween(random,-0.2f,0.2f),
                            y,
                            entity.getZ() +Mth.randomBetween(random,-0.2f,0.2f),
                            Mth.randomBetween(random,-0.75f,-1),
                            color,
                            0);
                    //Mth.randomBetween(random, -1.0F, 1.0F) * 0.001f)
                }
            }
        }
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return switch (type) {
            case LAND -> getLayers(state) < 5;
            case WATER -> getLayers(state) == 0;
            case AIR -> false;
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return LAYERS_TO_SHAPE[getLayers(state)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext c) {
            var e = c.getEntity();
            if (e instanceof FallingLeafLayerEntity) {
                return LAYERS_TO_SHAPE[state.getValue(LAYERS)];
            }
        }
        return Shapes.empty();
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) {
        return LAYERS_TO_SHAPE[getLayers(state)];
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return LAYERS_TO_SHAPE[getLayers(state)];
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos otherPos) {
        if (world instanceof ServerLevel serverLevel) {
            BlockPos pos = currentPos.above();
            BlockState state1 = world.getBlockState(pos);

            while (state1.is(this)) {
                serverLevel.scheduleTick(pos, this, this.getDelayAfterPlace());
                pos = pos.above();
                state1 = serverLevel.getBlockState(pos);
            }
        }
        return super.updateShape(state, direction, facingState, world, currentPos, otherPos);
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        int i = pState.getValue(LAYERS);
        if (pUseContext.getItemInHand().is(this.asItem()) && i < MAX_LAYERS) {
            return true;
        } else {
            return i == 1;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos());
        if (blockState.is(this)) {
            int i = blockState.getValue(LAYERS);
            return blockState.setValue(LAYERS, Math.min(8, i + 1));
        } else {
            if (blockState.getFluidState().is(Fluids.WATER)) return null;
            BlockState below = ctx.getLevel().getBlockState(ctx.getClickedPos().below());
            if (below.getFluidState().is(Fluids.WATER)) {
                if (!blockState.isAir()) return null;
                return this.defaultBlockState().setValue(LAYERS, 0);
            }
            return super.getStateForPlacement(ctx);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYERS);
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        return this.hasFlowers;
    }

    @Override
    public boolean isBonemealSuccess(Level world, Random random, BlockPos pos, BlockState state) {
        return this.hasFlowers;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
        for (var direction : Direction.values()) {
            if (random.nextFloat() > 0.5f) {
                var targetPos = pos.relative(direction);
                BlockState targetBlock = world.getBlockState(targetPos);
                WeatheringHelper.getAzaleaGrowth(targetBlock).ifPresent(s ->
                        world.setBlockAndUpdate(targetPos, s)
                );
            }
        }
    }

    @Override
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        int layers = this.getLayers(state);
        if (state.getBlock() != oldState.getBlock() && layers > 0)
            worldIn.scheduleTick(pos, this, this.getDelayAfterPlace());
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random pRand) {
        BlockState below = level.getBlockState(pos.below());
        if ((FallingLeafLayerEntity.isFree(below) || hasIncompleteLeafPileBelow(below)) && pos.getY() >= level.getMinBuildHeight()) {
            int layers = this.getLayers(state);
            while (state.is(this) && layers > 0) {
                FallingBlockEntity fallingblockentity = FallingLeafLayerEntity.fall(level, pos, state);
                this.falling(fallingblockentity);

                pos = pos.above();
                state = level.getBlockState(pos);
            }
        }
    }

    private boolean hasIncompleteLeafPileBelow(BlockState state) {
        return state.is(this) && state.getValue(LAYERS) != MAX_LAYERS;
    }
}
