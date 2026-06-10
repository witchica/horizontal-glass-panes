package com.witchica.horizontalglasspanes;

import net.blay09.mods.balm.platform.event.callback.CreativeModeTabCallback;
import net.minecraft.resources.Identifier;
import net.blay09.mods.balm.core.BalmRegistrars;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopperBarsBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.item.ModItems;

public class HorizontalGlassPanes {

    public static final Logger logger = LoggerFactory.getLogger(HorizontalGlassPanes.class);
    public static final String MOD_ID = "horizontalglasspanes";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void initialize(BalmRegistrars registrars) {
        registrars.blocks(ModBlocks::initialize);
        registrars.items(ModItems::initialize);

        CreativeModeTabCallback.BuildContents.forTab(Identifier.withDefaultNamespace("colored_blocks")).register((tab, output) -> {
            ModBlocks.colorfulPanes.sortedEntries().forEach(dyeColorDeferredBlockEntry -> {
                output.accept(dyeColorDeferredBlockEntry.getValue().asBlock());
            });
        });



        CreativeModeTabCallback.BuildContents.forTab(Identifier.withDefaultNamespace("building_blocks")).register((tab, output) -> {
            output.accept(ModBlocks.glassPane);
            output.accept(ModBlocks.ironBars);

            ModBlocks.copperBars.sortedEntries().forEach(weatherStateDeferredBlockEntry -> {
                output.accept(weatherStateDeferredBlockEntry.getValue().asBlock());
            });

            ModBlocks.waxedCopperBars.sortedEntries().forEach(weatherStateDeferredBlockEntry -> {
                output.accept(weatherStateDeferredBlockEntry.getValue().asBlock());
            });
        });
    }

}
