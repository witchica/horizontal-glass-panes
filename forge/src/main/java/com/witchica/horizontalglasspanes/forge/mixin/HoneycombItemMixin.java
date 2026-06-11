package com.witchica.horizontalglasspanes.forge.mixin;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.block.WeatheringHorizontalCopperBarsBlock;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(HoneycombItem.class)
public class HoneycombItemMixin {
    @Inject(method = "getWaxed", at = @At("HEAD"), cancellable = true)
    private static void getWaxed(BlockState oldState, CallbackInfoReturnable<Optional<BlockState>> cir) {
        if(oldState.getBlock() instanceof WeatheringHorizontalCopperBarsBlock copperBarsBlock) {
            if(!copperBarsBlock.isWaxed()) {
                cir.setReturnValue(Optional.of(ModBlocks.waxedCopperBars.get(copperBarsBlock.getAge()).asBlock().withPropertiesOf(oldState)));
            }
        }
    }

}
