package com.witchica.horizontalglasspanes.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.tag.ModBlockTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    private static final TagKey<Block> MINEABLE_PICKAXE = TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace("mineable/pickaxe"));
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        ModBlocks.colorfulPanes.sortedEntries().forEach(dyeColorDeferredBlockEntry -> {
            tag(ModBlockTags.COLORFUL_HORIZONTAL_PANES).add(dyeColorDeferredBlockEntry.getValue().asResourceKey());
        });

        ModBlocks.copperBars.sortedEntries().forEach((weatherStateDeferredBlock) -> {
            tag(ModBlockTags.COPPER_HORIZONTAL_BARS).add(weatherStateDeferredBlock.getValue().asResourceKey());
            tag(MINEABLE_PICKAXE).add(weatherStateDeferredBlock.getValue().asResourceKey());
        });

        ModBlocks.waxedCopperBars.sortedEntries().forEach((weatherStateDeferredBlock) -> {
            tag(ModBlockTags.WAXED_COPPER_HORIZONTAL_BARS).add(weatherStateDeferredBlock.getValue().asResourceKey());
            tag(MINEABLE_PICKAXE).add(weatherStateDeferredBlock.getValue().asResourceKey());
        });

        tag(MINEABLE_PICKAXE).add(ModBlocks.ironBars.asResourceKey());
    }
}
