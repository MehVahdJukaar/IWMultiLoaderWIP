package com.ordana.immersive_weathering.forge;


import com.ordana.immersive_weathering.block_growth.BlockGrowthHandler;

import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ImmersiveWeatheringForge.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {



    @SubscribeEvent
    public static void onTagUpdated(TagsUpdatedEvent event) {
        BlockGrowthHandler.getInstance().rebuild(event.getTagManager());
    }


    //hacky but that's the best I can do if I dont get given a registry access in that reload listener
    //Without it, it wont load tags
    //use this until forge approves that datapack registries PR (will take some time)
    @SubscribeEvent
    public static void onAddReloadListeners(final AddReloadListenerEvent event) {
        event.addListener(BlockGrowthHandler.getInstance());
    }
    /*

    //TODO: copy and merge fabic one from latest update

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        Item i = stack.getItem();
        BlockPos pos = event.getPos();
        Level level = event.getWorld();
        BlockState state = level.getBlockState(pos);
        Block b = state.getBlock();
        Player player = event.getPlayer();

        //shear azalea
        if (i instanceof ShearsItem) {
            var newState = WeatheringHelper.getAzaleaSheared(state).orElse(null);
            if (newState != null) {
                if (level.isClientSide) {
                    ParticleHelper.spawnParticlesOnBlockFaces(level, pos, ModParticles.AZALEA_FLOWER.get(), UniformInt.of(4, 6));
                } else {
                    Block.popResourceFromFace(level, pos, event.getFace(), new ItemStack(ModItems.AZALEA_FLOWERS.get()));
                }
                level.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0f, 1.0f);

            } else {
                BlockState s = Mossable.getUnaffectedMossBlock(state);
                if (s != state) {
                    newState = s;
                    if (IntegrationHandler.quark) newState = QuarkPlugin.fixVerticalSlab(newState, state);
                    if (!level.isClientSide) {
                        Block.popResourceFromFace(level, pos, event.getFace(), new ItemStack(ModItems.MOSS_CLUMP.get()));
                    }
                    level.playSound(player, pos, SoundEvents.GROWING_PLANT_CROP, SoundSource.BLOCKS, 1.0f, 1.0f);
                }
            }

            if (newState != null) {
                level.setBlockAndUpdate(pos, newState);

                if (player != null) {
                    stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(event.getHand()));
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    level.gameEvent(player, GameEvent.SHEAR, pos);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        } else if (i instanceof FlintAndSteelItem) {

            BlockState s = Mossable.getUnaffectedMossBlock(state);
            if (s != state) {
                s = Weatherable.setStable(s);
                if (IntegrationHandler.quark) s = QuarkPlugin.fixVerticalSlab(s, state);
                if (level.isClientSide) {
                    ModParticles.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.FLAME, UniformInt.of(3, 5));
                }
                //fixing stuff prevents them from weathering

                level.setBlockAndUpdate(pos, s);
                level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);

                if (player != null) {
                    stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(event.getHand()));
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }

        }
        //spawn ash
        else if (i instanceof ShovelItem) {
            if (b instanceof CampfireBlock && state.getValue(BlockStateProperties.LIT)) {
                Block.popResourceFromFace(level, pos, Direction.UP, new ItemStack(ModBlocks.SOOT.get()));
            }
            if (b instanceof FireBlock) {
                Block.popResource(level, pos, new ItemStack(ModBlocks.SOOT.get()));
                level.playSound(player, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0f, 1.0f);

                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());

                if (player != null) {
                    stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(event.getHand()));
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
        //break crackable stuff
        else if (i instanceof PickaxeItem &&
                (!ServerConfigs.CRACK_REQUIRES_SHIFTING.get() || player.isSecondaryUseActive())) {

            BlockState newBlock = Crackable.getCrackedBlock(state);
            if (newBlock != state) {
                if (IntegrationHandler.quark) newBlock = QuarkPlugin.fixVerticalSlab(newBlock, state);
                if (!player.isCreative()) {
                    if (newBlock instanceof Crackable crackable) {
                        Block.popResourceFromFace(level, pos, event.getFace(), crackable.getRepairItem(state).getDefaultInstance());
                    }
                }

                level.playSound(player, pos, newBlock.getSoundType().getHitSound(), SoundSource.BLOCKS, 1.0f, 1.0f);

                // level.setBlockAndUpdate(pos, Block.updateFromNeighbourShapes(newBlock, level, pos));
                level.setBlockAndUpdate(pos, newBlock);

                if (player != null) {
                    stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(event.getHand()));
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
        //spawn bark
        else if (i instanceof AxeItem) {
            if (ServerConfigs.BARK_ENABLED.get()) {
                var stripped = state.getToolModifiedState(level, pos, player, stack, ToolActions.AXE_STRIP);
                if (stripped != null) {
                    var bark = WeatheringHelper.getBarkForStrippedLog(stripped).orElse(null);
                    if (bark != null) {

                        Block.popResourceFromFace(level, pos, event.getFace(), bark.getFirst().getDefaultInstance());
                        level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);


                        if (player != null) {
                            stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(event.getHand()));
                        }

                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                            player.awardStat(Stats.ITEM_USED.get(i));
                        }
                        //not cancelling so the block can getMossSpreader
                        event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
                        return;
                    }
                }
            }

            if (state.getBlock() instanceof Rustable r && r.getAge() != Rustable.RustLevel.RUSTED) {
                var unRusted = r.getPrevious(state).orElse(null);
                if (unRusted != null) {

                    level.setBlockAndUpdate(pos, unRusted);
                    level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
                    level.blockEvent(pos, unRusted.getBlock(), 1, 0);
                    if (player != null) {
                        stack.hurtAndBreak(1, player, (l) -> l.broadcastBreakEvent(event.getHand()));
                    }

                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                        player.awardStat(Stats.ITEM_USED.get(i));
                    }
                    event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
                    event.setCanceled(true);
                    return;
                }
            }

        }
        //rust stuff
        else if (i == Items.WET_SPONGE && b instanceof Rustable rustable) {
            BlockState rusted = rustable.getNext(state).orElse(null);
            if (rusted != null) {
                level.playSound(player, pos, SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundSource.BLOCKS, 1.0f, 1.0f);

                level.setBlockAndUpdate(pos, rusted);

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
        //mossify stuff
        else if (i == ModItems.MOSS_CLUMP.get()) {
            BlockState mossy = Mossable.getMossyBlock(state);
            if (mossy != state) {
                level.playSound(player, pos, SoundEvents.MOSS_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
                if (IntegrationHandler.quark) mossy = QuarkPlugin.fixVerticalSlab(mossy, state);
                level.setBlockAndUpdate(pos, mossy);

                if (player != null) {
                    if (!player.isCreative()) stack.shrink(1);
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
        //waxing
        else if (i instanceof HoneycombItem) {
            var waxed = ModWaxables.getWaxedState(state).orElse(null);
            if (waxed != null) {

                level.levelEvent(player, 3003, pos, 0);

                level.setBlockAndUpdate(pos, waxed);

                if (player != null) {
                    if (!player.isCreative()) stack.shrink(1);
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }

        } else if (b instanceof Crackable crackable && crackable.getRepairItem(state) == i) {
            //Fix cracked stuff
            BlockState fixedBlock = crackable.getPreviousCracked(state).orElse(null);
            if (fixedBlock != null) {

                //fixing stuff prevents them from weathering
                fixedBlock = Weatherable.setStable(fixedBlock);
                if (IntegrationHandler.quark) fixedBlock = QuarkPlugin.fixVerticalSlab(fixedBlock, state);

                SoundEvent placeSound = fixedBlock.getSoundType().getPlaceSound();
                level.playSound(player, pos, placeSound, SoundSource.BLOCKS, 1.0f, 1.0f);

                level.setBlockAndUpdate(pos, fixedBlock);

                if (player != null) {
                    if (!player.isCreative()) stack.shrink(1);
                }

                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        } else {
            //fix logs
            Pair<Item, Block> fixedLog = WeatheringHelper.getBarkForStrippedLog(state).orElse(null);
            if (fixedLog != null && stack.getItem() == fixedLog.getFirst()) {
                BlockState fixedState = fixedLog.getSecond().withPropertiesOf(state);

                level.playSound(player, pos, fixedState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);

                if (player != null) {
                    if (!player.isCreative()) stack.shrink(1);
                }

                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
                    player.awardStat(Stats.ITEM_USED.get(i));
                }

                level.setBlockAndUpdate(pos, fixedState);

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
            }
        }
    }


    private static final Map<String, ResourceLocation> fullReMap = new HashMap<>() {{
        put("ash_block", ImmersiveWeatheringForge.res("soot_block"));
        put("nulch", ImmersiveWeatheringForge.res("nulch_block"));
        put("mulch", ImmersiveWeatheringForge.res("mulch_block"));
    }};

    @SubscribeEvent
    public static void onRemapBlocks(RegistryEvent.MissingMappings<Block> event) {
        for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getMappings(ImmersiveWeatheringForge.MOD_ID)) {
            String k = mapping.key.getPath();
            if (fullReMap.containsKey(k)) {
                var i = fullReMap.get(k);
                try {
                    ImmersiveWeatheringForge.LOGGER.warn("Remapping block '{}' to '{}'", mapping.key, i);
                    mapping.remap(ForgeRegistries.BLOCKS.getValue(i));
                } catch (Throwable t) {
                    ImmersiveWeatheringForge.LOGGER.warn("Remapping block '{}' to '{}' failed: {}", mapping.key, i, t);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRemapItems(RegistryEvent.MissingMappings<Item> event) {
        for (RegistryEvent.MissingMappings.Mapping<Item> mapping : event.getMappings(ImmersiveWeatheringForge.MOD_ID)) {
            String k = mapping.key.getPath();
            if (fullReMap.containsKey(k)) {
                var i = fullReMap.get(k);
                try {
                    ImmersiveWeatheringForge.LOGGER.warn("Remapping item '{}' to '{}'", mapping.key, i);
                    mapping.remap(ForgeRegistries.ITEMS.getValue(i));
                } catch (Throwable t) {
                    ImmersiveWeatheringForge.LOGGER.warn("Remapping item '{}' to '{}' failed: {}", mapping.key, i, t);
                }
            }
        }
    }

    @SubscribeEvent
    public static void addFeaturesToBiomes(BiomeLoadingEvent event) {

        ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES, event.getName());
        //Holder<Biome> holder = BuiltinRegistries.BIOME.getHolderOrThrow(key);

        Biome.BiomeCategory category = event.getCategory();

        if(ServerConfigs.ICICLES_PATCHES.get()) {
            if (BiomeDictionary.hasType(key, BiomeDictionary.Type.SNOWY) || category == Biome.BiomeCategory.ICY) {
                addFeature(event, ICICLES, GenerationStep.Decoration.TOP_LAYER_MODIFICATION);
                if (category == Biome.BiomeCategory.UNDERGROUND) {
                    addFeature(event, CAVE_ICICLES, GenerationStep.Decoration.UNDERGROUND_DECORATION);
                }
            }
        }

        if(ServerConfigs.HUMUS_PATCHES.get()) {
            if (key == Biomes.DARK_FOREST) {
                addFeature(event, "humus_patches", GenerationStep.Decoration.LOCAL_MODIFICATIONS);
            }
        }
    }
    */

}


