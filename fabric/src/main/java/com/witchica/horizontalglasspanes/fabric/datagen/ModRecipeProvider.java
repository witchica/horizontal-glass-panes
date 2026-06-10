package com.witchica.horizontalglasspanes.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.item.ModItems;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.glassPane.asBlock())
                        .pattern("PPP").define('P', Blocks.GLASS_PANE)
                        .unlockedBy("has_glass_pane", has(Items.GLASS_PANE)).save(exporter);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ironBars.asBlock())
                        .pattern("PPP").define('P', Blocks.IRON_BARS)
                        .unlockedBy("has_iron_bars", has(Items.IRON_BARS)).save(exporter);

                ModBlocks.colorfulPanes.forEach((dyeColor, deferredBlock) -> {
                    shaped(RecipeCategory.BUILDING_BLOCKS, deferredBlock.asBlock())
                            .pattern("PPP").define('P', ModBlocks.DYE_TO_PANES.get(dyeColor))
                            .unlockedBy("has_pane", has(ModBlocks.DYE_TO_PANES.get(dyeColor))).save(exporter);
                });
            }
        };
    }

    @Override
    public String getName() {
        return HorizontalGlassPanes.MOD_ID;
    }
}
