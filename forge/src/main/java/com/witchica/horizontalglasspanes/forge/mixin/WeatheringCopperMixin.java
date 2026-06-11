package com.witchica.horizontalglasspanes.forge.mixin;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.block.WeatheringHorizontalCopperBarsBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(WeatheringCopper.class)
public interface WeatheringCopperMixin {

    @Inject(method = "getNext(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private static void getNext(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        if(block instanceof WeatheringHorizontalCopperBarsBlock copperBlock && !copperBlock.isWaxed()) {
            switch (copperBlock.getAge()) {
                case UNAFFECTED -> {
                    cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.EXPOSED).asBlock()));
                    return;
                }
                case EXPOSED -> {
                    cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.WEATHERED).asBlock()));
                    return;
                }
                case WEATHERED -> {
                    cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.OXIDIZED).asBlock()));
                    return;
                }
            }
        }
    }
    @Inject(method = "getPrevious(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private static void getPrevious(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        if(block instanceof WeatheringHorizontalCopperBarsBlock copperBlock && !copperBlock.isWaxed()) {
            switch (copperBlock.getAge()) {
                case OXIDIZED -> {
                    cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.WEATHERED).asBlock()));
                    return;
                }
                case WEATHERED -> {
                    cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.EXPOSED).asBlock()));
                    return;
                }
                case EXPOSED -> {
                    cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.UNAFFECTED).asBlock()));
                    return;
                }
            }
        }
    }

    @Inject(method = "getFirst(Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/Block;", at = @At("HEAD"), cancellable = true)
    private static void getFirst(Block block, CallbackInfoReturnable<Optional<Block>> cir) {
        if(block instanceof WeatheringHorizontalCopperBarsBlock copperBlock) {
            cir.setReturnValue(Optional.of(ModBlocks.copperBars.get(WeatheringCopper.WeatherState.UNAFFECTED).asBlock()));
        }
    }
}
