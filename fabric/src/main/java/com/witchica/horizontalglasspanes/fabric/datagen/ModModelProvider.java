package com.witchica.horizontalglasspanes.fabric.datagen;

import com.witchica.horizontalglasspanes.fabric.datagen.provider.PaneBlockModelProvider;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.item.ModItems;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;

import java.util.Locale;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        ModBlocks.colorfulPanes.forEach((dyeColor, deferredBlock) -> {
            PaneBlockModelProvider.registerPane(blockStateModelGenerator, deferredBlock.asBlock(), TextureMapping.singleSlot(PaneBlockModelProvider.topSlot, new Material(Identifier.withDefaultNamespace("block/"+dyeColor.name().toLowerCase(Locale.ROOT)+"_stained_glass"))).put(PaneBlockModelProvider.sideSlot, new Material(Identifier.withDefaultNamespace("block/"+dyeColor.name().toLowerCase(Locale.ROOT)+"_stained_glass_pane_top"))));
        });

        PaneBlockModelProvider.registerPane(blockStateModelGenerator, ModBlocks.glassPane.asBlock(), TextureMapping.singleSlot(PaneBlockModelProvider.topSlot, new Material(Identifier.withDefaultNamespace("block/glass"))).put(PaneBlockModelProvider.sideSlot, new Material(Identifier.withDefaultNamespace("block/glass_pane_top"))));
        PaneBlockModelProvider.registerBars(blockStateModelGenerator, ModBlocks.ironBars.asBlock(), TextureMapping.defaultTexture(new Material(Identifier.withDefaultNamespace("block/iron_bars"))));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

}
