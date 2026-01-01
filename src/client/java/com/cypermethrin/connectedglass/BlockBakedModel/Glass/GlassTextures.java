package com.cypermethrin.connectedglass.BlockBakedModel.Glass;


import com.cypermethrin.connectedglass.BlockBakedModel.Util;
import com.cypermethrin.connectedglass.ConnectedGlass;
import net.minecraft.client.renderer.block.model.TextureSlots;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class GlassTextures {
	private static final Identifier LOCATION_BLOCKS = TextureAtlas.LOCATION_BLOCKS;
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, "glass/glass");
    public static final Material[] SPRITE_IDENTIFIERS = new Material[]{
            new Material(LOCATION_BLOCKS, Identifier.withDefaultNamespace("block/glass")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_down")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_right")), // 5 index 4
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_down")),//6 index 5
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_down_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_down_right")), // 10
            new Material(LOCATION_BLOCKS, of("block/glass/glass_left_right")),// 11 index 10
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_down_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_down_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_left_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_down_left_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/glass_up_down_left_right")),//16 index 15 block ends
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_down_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_down_right")),//i 19
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_up_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_down_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_down_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_right_down_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_right_down_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_down_left_down_right")),// i 25
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_up_right_down_left")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_up_right_down_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_down_left_down_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_right_down_left_down_right")),
            new Material(LOCATION_BLOCKS, of("block/glass/pixel_up_left_up_right_down_left_down_right"))

    };

    public static final HashMap<String, Integer> map = new HashMap<String, Integer>(){{
        put("", 0);
        put("up", 1);
        put("down", 2);
        put("left", 3);
        put("right", 4);
        put("up_down", 5);
        put("up_left", 6);
        put("up_right", 7);
        put("down_left", 8);
        put("down_right", 9);
        put("left_right", 10);
        put("up_down_left", 11);
        put("up_down_right", 12);
        put("up_left_right", 13);
        put("down_left_right", 14);
        put("up_down_left_right", 15);
        put("pixel_up_left", 16);
        put("pixel_up_right", 17);
        put("pixel_down_left", 18);
        put("pixel_down_right", 19);
        put("pixel_up_left_up_right", 20);
        put("pixel_up_left_down_left", 21);
        put("pixel_up_left_down_right", 22);
        put("pixel_up_right_down_left", 23);
        put("pixel_up_right_down_right", 24);
        put("pixel_down_left_down_right", 25);
        put("pixel_up_left_up_right_down_left", 26);
        put("pixel_up_left_up_right_down_right", 27);
        put("pixel_up_left_down_left_down_right", 28);
        put("pixel_up_right_down_left_down_right", 29);
        put("pixel_up_left_up_right_down_left_down_right", 30);
    }};

    public static final TextureSlots.Data textures = initTextures();

    public TextureAtlasSprite[] SPRITES = new TextureAtlasSprite[SPRITE_IDENTIFIERS.length];

    public static TextureAtlasSprite getSprite(boolean up, boolean down, boolean left, boolean right){
        String string = "";
        if(up)string += "_up";
        if(down)string += "_down";
        if(left)string += "_left";
        if(right)string += "_right";
        if(string.length() > 1)string = string.substring(1);
        Integer i = map.get(string);
	    return Util.getSprite(SPRITE_IDENTIFIERS[map.get(string)]);
    }

    @Nullable
    public static TextureAtlasSprite getPixelSprite(boolean up_left, boolean up_right, boolean down_left, boolean down_right){
        if(up_left || up_right || down_left || down_right){
            String string = "pixel";
            if(up_left)string += "_up_left";
            if(up_right)string += "_up_right";
            if(down_left)string += "_down_left";
            if(down_right)string += "_down_right";
			return Util.getSprite(SPRITE_IDENTIFIERS[map.get(string)]);
        }
        else return null;
    }

    public static TextureSlots.Data initTextures(){
	    TextureSlots.Data.Builder builder = new TextureSlots.Data.Builder();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            builder.addTexture(entry.getKey(), SPRITE_IDENTIFIERS[entry.getValue()]);
        }
        return builder.build();
    }
    public static Identifier of(String path){
        return Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, path);
    }
}
