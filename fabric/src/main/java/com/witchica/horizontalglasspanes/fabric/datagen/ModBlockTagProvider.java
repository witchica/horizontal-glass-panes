package com.witchica.horizontalglasspanes.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.tag.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        ModBlocks.colorfulPanes.sortedEntries().forEach(dyeColorDeferredBlockEntry -> {
            valueLookupBuilder(ModBlockTags.COLORFUL_HORIZONTAL_PANES).add(dyeColorDeferredBlockEntry.getValue().asBlock());
        });

        ModBlocks.copperBars.sortedEntries().forEach((weatherStateDeferredBlock) -> {
            valueLookupBuilder(ModBlockTags.COPPER_HORIZONTAL_BARS).add(weatherStateDeferredBlock.getValue().asBlock());
        });

        ModBlocks.waxedCopperBars.sortedEntries().forEach((weatherStateDeferredBlock) -> {
            valueLookupBuilder(ModBlockTags.WAXED_COPPER_HORIZONTAL_BARS).add(weatherStateDeferredBlock.getValue().asBlock());
        });
    }
}
