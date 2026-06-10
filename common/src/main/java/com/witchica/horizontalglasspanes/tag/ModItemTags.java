package com.witchica.horizontalglasspanes.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.witchica.horizontalglasspanes.HorizontalGlassPanes.id;

public class ModItemTags {
    public static final TagKey<Item> COLORFUL_HORIZONTAL_PANES = TagKey.create(Registries.ITEM, id("colorful_horizontal_panes"));
}
