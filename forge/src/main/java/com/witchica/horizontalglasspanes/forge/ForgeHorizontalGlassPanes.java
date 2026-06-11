package com.witchica.horizontalglasspanes.forge;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;
import com.witchica.horizontalglasspanes.block.ModBlocks;
import com.witchica.horizontalglasspanes.block.WeatheringHorizontalCopperBarsBlock;
import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.forge.platform.runtime.ForgeLoadContext;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import com.witchica.horizontalglasspanes.HorizontalGlassPanes;
import com.witchica.horizontalglasspanes.client.HorizontalGlassPanesClient;

import java.util.Map;

@Mod(HorizontalGlassPanes.MOD_ID)
public class ForgeHorizontalGlassPanes {

    public ForgeHorizontalGlassPanes(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModBusGroup());
        Balm.initializeMod(HorizontalGlassPanes.MOD_ID, loadContext, HorizontalGlassPanes::initialize);
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(HorizontalGlassPanes.MOD_ID, loadContext, HorizontalGlassPanesClient::initialize);
        }

        BlockEvent.BlockToolModificationEvent.BUS.addListener(this::toolModify);
    }

    @SubscribeEvent
    public void toolModify(BlockEvent.BlockToolModificationEvent event) {
        if(event.getToolAction() == ToolActions.AXE_WAX_OFF) {
            Block block = event.getState().getBlock();
            if(block instanceof WeatheringHorizontalCopperBarsBlock copperBarsBlock && copperBarsBlock.isWaxed()) {
                event.setFinalState(ModBlocks.copperBars.get(copperBarsBlock.getAge()).asBlock().withPropertiesOf(event.getState()));
            }
        }
    }
}
