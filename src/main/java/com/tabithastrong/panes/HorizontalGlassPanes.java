package com.tabithastrong.panes;

import com.mojang.logging.LogUtils;
import com.tabithastrong.panes.block.HorizontalPaneBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("horizontal_glass_panes")
public class HorizontalGlassPanes
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static Block PANE_GLASS;
    public static Block PANE_IRON;
    public static final Block[] COLORED_PANES = new Block[16];

    public static final Block[] VANILLA_COLORED_PANES = new Block[] {
            Blocks.WHITE_STAINED_GLASS_PANE,
            Blocks.ORANGE_STAINED_GLASS_PANE,
            Blocks.MAGENTA_STAINED_GLASS_PANE,
            Blocks.LIGHT_BLUE_STAINED_GLASS_PANE,
            Blocks.YELLOW_STAINED_GLASS_PANE,
            Blocks.LIME_STAINED_GLASS_PANE,
            Blocks.PINK_STAINED_GLASS_PANE,
            Blocks.GRAY_STAINED_GLASS_PANE,
            Blocks.LIGHT_GRAY_STAINED_GLASS_PANE,
            Blocks.CYAN_STAINED_GLASS_PANE,
            Blocks.PURPLE_STAINED_GLASS_PANE,
            Blocks.BLUE_STAINED_GLASS_PANE,
            Blocks.BROWN_STAINED_GLASS_PANE,
            Blocks.GREEN_STAINED_GLASS_PANE,
            Blocks.RED_STAINED_GLASS_PANE,
            Blocks.BLACK_STAINED_GLASS_PANE
    };


    public HorizontalGlassPanes()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(HorizontalGlassPanesClient::onClientSetupEvent));

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onRegister(RegisterEvent registerEvent)
        {
            registerEvent.register(ForgeRegistries.Keys.BLOCKS, helper -> {
                for(int i = 0; i < DyeColor.values().length; i++) {
                    COLORED_PANES[i] = new HorizontalPaneBlock(BlockBehaviour.Properties.copy(VANILLA_COLORED_PANES[i]));
                    helper.register(new ResourceLocation("horizontal_glass_panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), COLORED_PANES[i]);
                }

                PANE_GLASS = new HorizontalPaneBlock(BlockBehaviour.Properties.copy(Blocks.GLASS_PANE));
                PANE_IRON = new HorizontalPaneBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BARS));

                helper.register(new ResourceLocation("horizontal_glass_panes", "pane_glass"), PANE_GLASS);
                helper.register(new ResourceLocation("horizontal_glass_panes", "pane_iron"), PANE_IRON);
            });

            registerEvent.register(ForgeRegistries.Keys.ITEMS, helper -> {
                for(int i = 0; i < DyeColor.values().length; i++) {
                    helper.register(new ResourceLocation("horizontal_glass_panes", "pane_" + DyeColor.values()[i].name().toLowerCase()), new BlockItem(COLORED_PANES[i], new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
                }

                helper.register(new ResourceLocation("horizontal_glass_panes", "pane_glass"), new BlockItem(PANE_GLASS, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
                helper.register(new ResourceLocation("horizontal_glass_panes", "pane_iron"), new BlockItem(PANE_IRON, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
            });
        }
    }
}
