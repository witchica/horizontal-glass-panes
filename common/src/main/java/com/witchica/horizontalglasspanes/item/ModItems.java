package com.witchica.horizontalglasspanes.item;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import net.blay09.mods.balm.platform.event.callback.CreativeModeTabCallback;
import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;

import static com.witchica.horizontalglasspanes.HorizontalGlassPanes.id;

public class ModItems {
    public static void initialize(BalmItemRegistrar items) {

    }

    public static void initializeCreativeTabs(BalmCreativeModeTabRegistrar registrar) {
        registrar.register("general", (identifier, builder) ->
                builder.icon(() -> ModBlocks.colorfulPanes.get(DyeColor.RED).createStack())
                .title(Component.translatable("itemGroup.horizontalglasspanes"))
                .displayItems((itemDisplayParameters, output) -> {
                   output.accept(ModBlocks.glassPane);
                   ModBlocks.colorfulPanes.sortedEntries().forEach(dyeColorDeferredBlockEntry -> {
                       output.accept(dyeColorDeferredBlockEntry.getValue());
                   });
                    output.accept(ModBlocks.ironBars);
                    ModBlocks.copperBars.sortedEntries().forEach(weatherStateDeferredBlockEntry -> {
                        output.accept(weatherStateDeferredBlockEntry.getValue());
                    });
                    ModBlocks.waxedCopperBars.sortedEntries().forEach(weatherStateDeferredBlockEntry -> {
                        output.accept(weatherStateDeferredBlockEntry.getValue());
                    });
                }));
    }
}
