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
        DYE_TO_PANES.put(DyeColor.BLACK, Blocks.BLACK_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.BLUE, Blocks.BLUE_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.BROWN, Blocks.BROWN_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.CYAN, Blocks.CYAN_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.GRAY, Blocks.GRAY_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.GREEN, Blocks.GREEN_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.LIME, Blocks.LIME_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.ORANGE, Blocks.ORANGE_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.MAGENTA, Blocks.MAGENTA_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.PINK, Blocks.PINK_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.PURPLE, Blocks.PURPLE_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.RED, Blocks.RED_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.WHITE, Blocks.WHITE_STAINED_GLASS_PANE);
        DYE_TO_PANES.put(DyeColor.YELLOW, Blocks.YELLOW_STAINED_GLASS_PANE);
    }

    public static void initialize(BalmBlockRegistrar blocks) {
        colorfulPanes = blocks.registerDiscriminated(DyeColor.values(), dyeColor -> dyeColor.name().toLowerCase(Locale.ROOT) + "_horizontal_pane", (dyeColor, properties) -> {
            return new ColorfulHorizontalPaneBlock(properties, dyeColor);
        }, properties -> Blocks.GLASS_PANE.properties()).withDefaultItems().asDiscriminatedBlocks();


        ironBars = blocks.register("iron_horizontal_bars", properties -> {
            return new HorizontalPaneBlock(properties);
        }, properties -> BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)).withDefaultItem().asDeferredBlock();

        glassPane = blocks.register("glass_horizontal_pane", properties -> new HorizontalPaneBlock(properties), properties -> BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)).withDefaultItem().asDeferredBlock();
    }

    private static Block getWeatherStateOfBlock(WeatheringCopperBlocks weatheringCopperBlocks, WeatheringCopper.WeatherState weatherState, boolean waxed) {
        switch (weatherState) {
            case UNAFFECTED -> {
                return waxed ? weatheringCopperBlocks.waxed() : weatheringCopperBlocks.unaffected();
            }
            case EXPOSED -> {
                return waxed ? weatheringCopperBlocks.waxedExposed() : weatheringCopperBlocks.exposed();
            }
            case OXIDIZED -> {
                return waxed ? weatheringCopperBlocks.waxedOxidized() : weatheringCopperBlocks.oxidized();
            }
            case WEATHERED -> {
                return waxed ? weatheringCopperBlocks.waxedWeathered() : weatheringCopperBlocks.weathered();
            }
        }

        return null;
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
