package com.witchica.horizontalglasspanes.fabric.datagen;

import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.tag.ModBlockTags;
import com.witchica.horizontalglasspanes.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
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

        addForTag(translationBuilder, ModBlockTags.COLORFUL_HORIZONTAL_PANES);
        addForTag(translationBuilder, ModBlockTags.COPPER_HORIZONTAL_BARS);
        addForTag(translationBuilder, ModBlockTags.WAXED_COPPER_HORIZONTAL_BARS);

        addForTag(translationBuilder, ModItemTags.COLORFUL_HORIZONTAL_PANES);
        addForTag(translationBuilder, ModItemTags.COPPER_HORIZONTAL_BARS);
        addForTag(translationBuilder, ModItemTags.WAXED_COPPER_HORIZONTAL_BARS);
    }

    private void addForTag(TranslationBuilder translationBuilder, TagKey tagKey) {
        translationBuilder.add(tagKey, snakeCaseToName(tagKey.location().getPath()));
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
