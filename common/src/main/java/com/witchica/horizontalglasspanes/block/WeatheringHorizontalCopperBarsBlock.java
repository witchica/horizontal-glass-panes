package com.witchica.horizontalglasspanes.block;

import com.google.common.collect.BiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class WeatheringHorizontalCopperBarsBlock extends HorizontalPaneBlock implements WeatheringCopper {
    private final WeatherState weatherState;
    private final boolean waxed;

    protected WeatheringHorizontalCopperBarsBlock(Properties properties, WeatherState weatherState, boolean waxed) {
        super(properties);
        this.weatherState = weatherState;
        this.waxed = waxed;
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return getNext(state).isPresent();
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        if(waxed || ModBlocks.copperBars == null) {
            return Optional.empty();
        }

        switch (weatherState) {
            case UNAFFECTED -> {
                return Optional.of(ModBlocks.copperBars.get(WeatherState.EXPOSED).asBlock().withPropertiesOf(state));
            }
            case EXPOSED -> {
                return Optional.of(ModBlocks.copperBars.get(WeatherState.WEATHERED).asBlock().withPropertiesOf(state));
            }
            case WEATHERED -> {
                return Optional.of(ModBlocks.copperBars.get(WeatherState.OXIDIZED).asBlock().withPropertiesOf(state));
            }
        }

        return Optional.empty();
    }

    public Optional<BlockState> getPrevious(BlockState state) {
        if(ModBlocks.copperBars == null || waxed) {
            return Optional.empty();
        }

        switch (weatherState) {
            case EXPOSED -> {
                return Optional.of(ModBlocks.copperBars.get(WeatherState.UNAFFECTED).asBlock().withPropertiesOf(state));
            }
            case WEATHERED -> {
                return Optional.of(ModBlocks.copperBars.get(WeatherState.EXPOSED).asBlock().withPropertiesOf(state));
            }
            case OXIDIZED -> {
                return Optional.of(ModBlocks.copperBars.get(WeatherState.WEATHERED).asBlock().withPropertiesOf(state));
            }
        }

        return Optional.empty();
    }

    public Optional<BlockState> getWaxOff(BlockState state) {
        if(ModBlocks.copperBars == null || !waxed) {
            return Optional.empty();
        }

        return Optional.of(ModBlocks.copperBars.get(weatherState).asBlock().withPropertiesOf(state));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            ItemStack heldStack = player.getItemInHand(hand);
            Optional<BlockState> newBlock = evaluateBlockState(state, heldStack);

            if(newBlock.isPresent()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, heldStack);
                }

                if(heldStack.is(Items.HONEYCOMB)) {
                    heldStack.shrink(1);
                    spawnSoundAndParticle(level, pos, player, state, SoundEvents.HONEYCOMB_WAX_ON, 3003);
                } else if(heldStack.is(ItemTags.AXES)) {
                    spawnSoundAndParticle(level, pos, player, state, waxed ? SoundEvents.AXE_WAX_OFF : SoundEvents.AXE_SCRAPE, waxed ? 3004 : 3005);
                }

                level.setBlock(pos, newBlock.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newBlock.get()));

                return InteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    public Optional<BlockState> evaluateBlockState(BlockState currentState, ItemStack held) {
        if(held.getItem() == Items.HONEYCOMB && !waxed) {
            return Optional.of(ModBlocks.waxedCopperBars.get(weatherState).asBlock().withPropertiesOf(currentState));
        } else if(held.is(ItemTags.AXES)) {
            return waxed ? getWaxOff(currentState) : getPrevious(currentState);
        }

        return Optional.empty();
    }

    private static void spawnSoundAndParticle(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, SoundEvent soundEvent, int particle) {
        level.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.levelEvent(null, particle, pos, 0);
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        return neighborState.getBlock() instanceof WeatheringHorizontalCopperBarsBlock;
    }
}
