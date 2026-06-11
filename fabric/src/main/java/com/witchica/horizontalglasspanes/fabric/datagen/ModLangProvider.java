package com.witchica.horizontalglasspanes.fabric.datagen;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.WeatheringCopper;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
    protected ModLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModBlocks.glassPane.asItem(), "Glass Pane");
        translationBuilder.add(ModBlocks.ironBars.asItem(), "Iron Bars");

        ModBlocks.colorfulPanes.sortedEntries().forEach(dyeColorDeferredBlockEntry -> {
            translationBuilder.add(dyeColorDeferredBlockEntry.getValue().asItem(), snakeCaseToName(dyeColorDeferredBlockEntry.getKey().getName()) + " Stained Glass Pane");
        });

        for(WeatheringCopper.WeatherState weatherState : WeatheringCopper.WeatherState.values()) {
            translationBuilder.add(ModBlocks.copperBars.get(weatherState).asItem(), weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "Copper Bars" : snakeCaseToName(weatherState.name()) + " Copper Bars");
            translationBuilder.add(ModBlocks.waxedCopperBars.get(weatherState).asItem(), weatherState == WeatheringCopper.WeatherState.UNAFFECTED ? "Waxed Copper Bars" : "Waxed " + snakeCaseToName(weatherState.name()) + " Copper Bars");
        }

        translationBuilder.add("itemGroup.horizontalglasspanes", "Horizontal Glass Panes");
    }

    private String snakeCaseToName(String s) {
        String[] bits = s.split("_");
        StringBuilder sb = new StringBuilder();

        for(String b : bits) {
            sb.append(b.substring(0,1).toUpperCase() + b.substring(1).toLowerCase());
            sb.append(" ");
        }

        return sb.toString().substring(0, sb.length()-1);
    }
}
