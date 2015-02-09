package com.tattyseal.hgp

import com.tattyseal.hgp.block.{ItemBlockStainedBlock, BlockStainedPane, BlockPane}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

object HGPRegistry 
{
  var blockPane = new BlockPane();
  var blockColoredPane = new BlockStainedPane();
  
  def register 
  {
    GameRegistry.registerBlock(blockPane, "hgpPane");
    GameRegistry.registerBlock(blockColoredPane, classOf[ItemBlockStainedBlock], "hgpColoredPane");

    for(i <- 0 until 16)
    {
      GameRegistry.addShapedRecipe(new ItemStack(blockColoredPane, 3, i), "PPP".asInstanceOf[Object], 'P'.asInstanceOf[Object], new ItemStack(Blocks.stained_glass_pane, 1, i).asInstanceOf[Object]);
    }

    GameRegistry.addShapedRecipe(new ItemStack(blockPane, 3), "PPP".asInstanceOf[Object], 'P'.asInstanceOf[Object], new ItemStack(Blocks.glass_pane, 1).asInstanceOf[Object]);
  }
}