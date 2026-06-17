package com.witchica.horizontalglasspanes.fabric.datagen;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import com.witchica.horizontalglasspanes.item.ModItems;
import com.witchica.horizontalglasspanes.tag.ModItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        ModBlocks.colorfulPanes.sortedEntries().forEach(dyeColorDeferredBlockEntry -> {
            tag(ModItemTags.COLORFUL_HORIZONTAL_PANES).add(dyeColorDeferredBlockEntry.getValue().asBlockItemId().item());
        });

        ModBlocks.copperBars.sortedEntries().forEach((weatherStateDeferredBlock) -> {
            tag(ModItemTags.COPPER_HORIZONTAL_BARS).add(weatherStateDeferredBlock.getValue().asBlockItemId().item());
        });

        ModBlocks.waxedCopperBars.sortedEntries().forEach((weatherStateDeferredBlock) -> {
            tag(ModItemTags.WAXED_COPPER_HORIZONTAL_BARS).add(weatherStateDeferredBlock.getValue().asBlockItemId().item());
        });
    }
}
