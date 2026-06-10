package com.witchica.horizontalglasspanes.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static com.witchica.horizontalglasspanes.HorizontalGlassPanes.id;

public class ModBlockTags {
    public static final TagKey<Block> COLORFUL_HORIZONTAL_PANES = TagKey.create(Registries.BLOCK, id("colorful_horizontal_panes"));
}
