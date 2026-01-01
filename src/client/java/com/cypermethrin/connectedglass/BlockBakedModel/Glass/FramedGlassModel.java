//package com.cypermethrin.connectedglass.BlockBakedModel.Glass;
//
//import com.cypermethrin.connectedglass.BlockBakedModel.Util;
//import com.cypermethrin.connectedglass.ConnectedGlass;
//import com.google.common.collect.Maps;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.renderer.block.model.TextureSlots;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.Material;
//import net.minecraft.client.resources.model.ModelManager;
//import net.minecraft.client.resources.model.ResolvedModel;
//import net.minecraft.client.resources.model.UnbakedModel;
//import net.minecraft.resources.Identifier;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//@Deprecated
//@Environment(EnvType.CLIENT)
//public class FramedGlassModel implements UnbakedModel, ResolvedModel {
//    private static final Material[] SPRITE_IDENTIFIERS = new Material[]{
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_down")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_left")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_right")), // 5 index 4
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_down")),//6 index 5
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_left")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_right")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_down_left")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_down_right")), // 10
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_left_right")),// 11 index 10
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_down_left")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_down_right")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_left_right")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_down_left_right")),
//            new Material(Util.LOCATION_BLOCKS, of("glass/glass_up_down_left_right")),
//
//    };
//
//    private static final HashMap<String, Integer> map = new HashMap<String, Integer>(){{
//        put("", 0);
//        put("up", 1);
//        put("down", 2);
//        put("left", 3);
//        put("right", 4);
//        put("up_down", 5);
//        put("up_left", 6);
//        put("up_right", 7);
//        put("down_left", 8);
//        put("down_right", 9);
//        put("left_right", 10);
//        put("up_down_left", 11);
//        put("up_down_right", 12);
//        put("up_left_right", 13);
//        put("down_left_right", 14);
//        put("up_down_left_right", 15);
//    }};
//
//    private static final TextureSlots.Data textures = initTextures();
//
//    private TextureAtlasSprite[] SPRITES = new TextureAtlasSprite[SPRITE_IDENTIFIERS.length];
//
//
//    protected static Identifier of(String path){
//        return Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, path);
//    }
//
//    @Override
//    public @Nullable Boolean ambientOcclusion() {
//        return false;
//    }
//
//    @Override
//    public @Nullable GuiLight guiLight() {
//        return UnbakedModel.super.guiLight();
//    }
//
//    private TextureAtlasSprite getSprite(boolean up, boolean down, boolean left, boolean right){
//        String string = "";
//        if(up)string += "_up";
//        if(down)string += "_down";
//        if(left)string += "_left";
//        if(down)string += "_right";
//        Integer i = map.get(string.substring(1));
//        return SPRITES[i];
//    }
//
//    private static TextureSlots.Data initTextures(){
//	    TextureSlots.Data.Builder builder = new TextureSlots.Data.Builder();
//        for(Map.Entry<String, Integer> entry : map.entrySet()){
//            builder.addTexture(entry.getKey(), SPRITE_IDENTIFIERS[entry.getValue()]);
//        }
//        return builder.build();
//    }
//
//	@Override
//	public UnbakedModel wrapped() {
//		return null;
//	}
//
//	@Override
//	public String debugName() {
//		return "";
//	}
//}
