package com.tattyseal.hgp.proxy;

import com.tattyseal.hgp.HorizontalGlassPanes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class ClientProxy implements IProxy
{
	@Override
	public void init()
	{

	}

	@Override
	public String getHorizontal()
	{
		return I18n.format("misc.horizontal");
	}
}
