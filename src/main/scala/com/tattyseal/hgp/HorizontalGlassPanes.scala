package com.tattyseal.hgp

import cpw.mods.fml.common.{Loader, Mod}
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.block.Block
import com.tattyseal.hgp.block.BlockPane
import org.apache.logging.log4j.LogManager

@Mod(modid = "hgp", name = "Horizontal Glass Panes", version = "Release", modLanguage = "scala", dependencies = "after:glass_shards")
object HorizontalGlassPanes 
{
  val registry = HGPRegistry
  val logger = LogManager.getLogger("HGP");
  
  @Mod.EventHandler
  def preInit(event: FMLPreInitializationEvent)
  {
    registry.register

    if(Loader.isModLoaded("glass_shards"))
    {
      logger.info("Loaded compatibility for Glass Shards");
    }
  }
}