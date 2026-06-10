package com.witchica.horizontalglasspanes.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.witchica.horizontalglasspanes.HorizontalGlassPanes.id;

public class ModItemTags {
    public static final TagKey<Item> COLORFUL_HORIZONTAL_PANES = TagKey.create(Registries.ITEM, id("colorful_horizontal_panes"));
    public static final TagKey<Item> COPPER_HORIZONTAL_BARS = TagKey.create(Registries.ITEM, id("copper_horizontal_bars"));
    public static final TagKey<Item> WAXED_COPPER_HORIZONTAL_BARS = TagKey.create(Registries.ITEM, id("waxed_copper_horizontal_bars"));
}
