package com.cypermethrin.connectedglass.BlockBakedModel.StainedGlasses;


import com.cypermethrin.connectedglass.BlockBakedModel.StainedGlassInterface;
import com.cypermethrin.connectedglass.BlockBakedModel.Util;
import net.minecraft.client.renderer.block.model.TextureSlots;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;

public class GrayGlassModel implements StainedGlassInterface {

	@Override
	public TextureAtlasSprite particleIcon() {
		return Util.getSprite(SPRITE_IDENTIFIERS[0]);
	}

	@Override
	public Util.Factory getIdentifierFactory() {
		return (index -> SPRITE_IDENTIFIERS[index]);
	}

	@Override
	public Material[] getSpriteIdentifiers() {
		return new Material[0];
	}

	public static final Material[] SPRITE_IDENTIFIERS;

	static {
		try {
			SPRITE_IDENTIFIERS = Util.getSpriteIdentifiers("gray_glass");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static TextureSlots.Data textures = Util.getTextures((index) -> SPRITE_IDENTIFIERS[index]);
}
