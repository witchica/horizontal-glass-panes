package com.witchica.horizontalglasspanes.fabric.datagen.provider;

import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public class PaneBlockModelProvider {
    public static TextureSlot topSlot = TextureSlot.create("top");
    public static TextureSlot sideSlot = TextureSlot.create("side");

    public static ModelTemplate paneTemplate = block("horizontal_pane", topSlot, sideSlot);
    public static ModelTemplate barsTemplate = block("horizontal_bars", TextureSlot.TEXTURE);

    //helper method for creating Models
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(HorizontalGlassPanes.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    public static BlockModelDefinitionGenerator createPane(Block paneBlock, Identifier model) {
        MultiVariant variant = BlockModelGenerators.plainVariant(model);

        return MultiVariantGenerator.dispatch(paneBlock, variant);
    }

    public static void registerPane(BlockModelGenerators generators, Block paneBlock, TextureMapping textures) {
        Identifier model = paneTemplate.create(paneBlock, textures, generators.modelOutput);
        generators.blockStateOutput.accept(createPane(paneBlock, model));
        generators.registerSimpleItemModel(paneBlock, model);
    }

    public static void registerBars(BlockModelGenerators generators, Block barsBlock, TextureMapping textures) {
        Identifier model = barsTemplate.create(barsBlock, textures, generators.modelOutput);
        generators.blockStateOutput.accept(createPane(barsBlock, model));
        generators.registerSimpleItemModel(barsBlock, model);
    }
}
