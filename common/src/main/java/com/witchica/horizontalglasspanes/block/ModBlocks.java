package com.witchica.horizontalglasspanes.block;

import net.blay09.mods.balm.world.level.block.BalmBlockRegistrar;
import net.blay09.mods.balm.world.level.block.DeferredBlock;
import net.blay09.mods.balm.world.level.block.DiscriminatedBlocks;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ModBlocks {
    public static DiscriminatedBlocks<DyeColor> colorfulPanes;
    public static DeferredBlock ironBars;
    public static DeferredBlock glassPane;

    public static Map<DyeColor, Block> DYE_TO_PANES = new HashMap<>();

    static {
        DYE_TO_PANES.put(DyeColor.BLACK, Blocks.STAINED_GLASS.black());
        DYE_TO_PANES.put(DyeColor.BLUE, Blocks.STAINED_GLASS.blue());
        DYE_TO_PANES.put(DyeColor.BROWN, Blocks.STAINED_GLASS.brown());
        DYE_TO_PANES.put(DyeColor.CYAN, Blocks.STAINED_GLASS.cyan());
        DYE_TO_PANES.put(DyeColor.GRAY, Blocks.STAINED_GLASS.gray());
        DYE_TO_PANES.put(DyeColor.GREEN, Blocks.STAINED_GLASS.green());
        DYE_TO_PANES.put(DyeColor.LIGHT_BLUE, Blocks.STAINED_GLASS.lightBlue());
        DYE_TO_PANES.put(DyeColor.LIGHT_GRAY, Blocks.STAINED_GLASS.lightGray());
        DYE_TO_PANES.put(DyeColor.LIME, Blocks.STAINED_GLASS.lime());
        DYE_TO_PANES.put(DyeColor.ORANGE, Blocks.STAINED_GLASS.orange());
        DYE_TO_PANES.put(DyeColor.MAGENTA, Blocks.STAINED_GLASS.magenta());
        DYE_TO_PANES.put(DyeColor.PINK, Blocks.STAINED_GLASS.pink());
        DYE_TO_PANES.put(DyeColor.PURPLE, Blocks.STAINED_GLASS.purple());
        DYE_TO_PANES.put(DyeColor.RED, Blocks.STAINED_GLASS.red());
        DYE_TO_PANES.put(DyeColor.WHITE, Blocks.STAINED_GLASS.white());
        DYE_TO_PANES.put(DyeColor.YELLOW, Blocks.STAINED_GLASS.yellow());
    }

    public static DiscriminatedBlocks<WeatheringCopper.WeatherState> copperBars;
    public static DiscriminatedBlocks<WeatheringCopper.WeatherState> waxedCopperBars;

    public static void initialize(BalmBlockRegistrar blocks) {
        colorfulPanes = blocks.registerDiscriminated(DyeColor.values(), dyeColor -> dyeColor.name().toLowerCase(Locale.ROOT) + "_horizontal_pane", (dyeColor, properties) -> {
            return new ColorfulHorizontalPaneBlock(properties, dyeColor);
        }, properties -> Blocks.GLASS_PANE.properties()).withDefaultItems().asDiscriminatedBlocks();


        ironBars = blocks.register("iron_horizontal_bars", properties -> {
            return new HorizontalPaneBlock(properties);
        }, properties -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)).withDefaultItem().asDeferredBlock();

        glassPane = blocks.register("glass_horizontal_pane", properties -> new HorizontalPaneBlock(properties), properties -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)).withDefaultItem().asDeferredBlock();


        copperBars = blocks.registerDiscriminated(WeatheringCopper.WeatherState.values(), weatherState -> weatherToName(weatherState, false) + "horizontal_copper_bars", (weatherState, properties) -> new WeatheringHorizontalCopperBarsBlock(properties, weatherState, false), (weatherState, properties) -> BlockBehaviour.Properties.ofFullCopy(getWeatherStateOfBlock(Blocks.COPPER_BARS, weatherState, false))).withDefaultItems().asDiscriminatedBlocks();
        waxedCopperBars = blocks.registerDiscriminated(WeatheringCopper.WeatherState.values(), weatherState -> weatherToName(weatherState, true) + "horizontal_copper_bars", (weatherState, properties) -> new WeatheringHorizontalCopperBarsBlock(properties, weatherState, true), (weatherState, properties) -> BlockBehaviour.Properties.ofFullCopy(getWeatherStateOfBlock(Blocks.COPPER_BARS, weatherState, true))).withDefaultItems().asDiscriminatedBlocks();
    }

    public static Block getWeatherStateOfBlock(WeatheringCopperCollection<Block> weatheringCopperBlocks, WeatheringCopper.WeatherState weatherState, boolean waxed) {
        final WeatheringCopperCollection.ByState<Block> blockByState = waxed ? weatheringCopperBlocks.waxed() : weatheringCopperBlocks.weathering();

        switch (weatherState) {
            case UNAFFECTED -> {
                return blockByState.unaffected();
            }
            case EXPOSED -> {
                return blockByState.exposed();
            }
            case OXIDIZED -> {
                return blockByState.oxidized();
            }
            case WEATHERED -> {
                return blockByState.weathered();
            }
        }

        return null;
    }

    public static String weatherToName(WeatheringCopper.WeatherState state, boolean waxed) {
        return (waxed ? "waxed_" : "") + (state == WeatheringCopper.WeatherState.UNAFFECTED ? "" : state.name().toLowerCase(Locale.ROOT) + "_");
    }

    private static BlockBehaviour.Properties copyProperties(BlockBehaviour.Properties properties, Block toCopy) {
        return properties
                .strength(toCopy.defaultDestroyTime())
                .explosionResistance(toCopy.getExplosionResistance())
                .instrument(toCopy.defaultBlockState().instrument())
                .mapColor(toCopy.defaultMapColor())
                .jumpFactor(toCopy.getJumpFactor())
                .sound(toCopy.defaultBlockState().getSoundType())
                .noOcclusion();
    }
}
