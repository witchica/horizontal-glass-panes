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
            valueLookupBuilder(ModItemTags.COLORFUL_HORIZONTAL_PANES).add(dyeColorDeferredBlockEntry.getValue().asItem());
        });
    }
}
