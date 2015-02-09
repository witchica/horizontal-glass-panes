package com.tattyseal.hgp

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraft.block.Block
import com.tattyseal.hgp.block.BlockPane

@Mod(modid = "hgp", name = "Horizontal Glass Panes", version = "${version}", modLanguage = "scala")
object HorizontalGlassPanes 
{
  val registry = HGPRegistry
  
  @Mod.EventHandler
  def preInit(event: FMLPreInitializationEvent)
  {
    registry.register
  }
}