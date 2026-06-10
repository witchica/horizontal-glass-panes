package com.witchica.horizontalglasspanes.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import com.witchica.horizontalglasspanes.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    protected ModBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(dataOutput, provider);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.ironBars.asBlock());
        dropWhenSilkTouch(ModBlocks.glassPane.asBlock());

        ModBlocks.colorfulPanes.forEach((dyeColor, deferredBlock) -> {
            dropWhenSilkTouch(deferredBlock.asBlock());
        });
    }
}
