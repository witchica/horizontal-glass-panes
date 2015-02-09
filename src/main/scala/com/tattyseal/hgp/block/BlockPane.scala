package com.tattyseal.hgp.block

import net.minecraft.block.Block
import net.minecraft.block.Block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.util.IIcon

class BlockPane extends Block(Material.glass)
{
  setBlockName("hgp.pane");
  setBlockBounds(0, 0.4375f, 0, 1, 0.5625f, 1);
  setBlockTextureName("glass");
  setHardness(1f);
  setHarvestLevel("pickaxe", 1);
  setCreativeTab(CreativeTabs.tabDecorations);
  setStepSound(Block.soundTypeGlass);
  
  override def isOpaqueCube(): Boolean =
  {
     return false;
  }
  
  override def getRenderBlockPass(): Int =
  {
    return 1;
  }
  
  override def getIcon(meta: Int, side: Int): IIcon =
  {
    return net.minecraft.init.Blocks.glass_pane.getIcon(meta, side);
  }

  override def canSilkHarvest(): Boolean = true;

  override def quantityDropped(rand: java.util.Random): Int = 0;

  override def getUnlocalizedName(): String = Blocks.glass_pane.getUnlocalizedName();
}