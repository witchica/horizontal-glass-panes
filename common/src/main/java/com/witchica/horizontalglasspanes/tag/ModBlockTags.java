package com.witchica.horizontalglasspanes.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.witchica.horizontalglasspanes.HorizontalGlassPanes.id;

public class ModBlockTags {
    public static final TagKey<Block> COLORFUL_HORIZONTAL_PANES = TagKey.create(Registries.BLOCK, id("colorful_horizontal_panes"));
    public static final TagKey<Block> COPPER_HORIZONTAL_BARS = TagKey.create(Registries.BLOCK, id("copper_horizontal_bars"));
    public static final TagKey<Block> WAXED_COPPER_HORIZONTAL_BARS = TagKey.create(Registries.BLOCK, id("waxed_copper_horizontal_bars"));
}
