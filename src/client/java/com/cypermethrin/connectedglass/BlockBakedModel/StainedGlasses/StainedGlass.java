//package com.cypermethrin.connectedglass.BlockBakedModel.StainedGlasses;
//
//import com.cypermethrin.connectedglass.BlockBakedModel.Util;
//import com.cypermethrin.connectedglass.ConnectedGlass;
//import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
//import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
//import net.fabricmc.fabric.api.renderer.v1.mesh.ShadeMode;
//import net.fabricmc.fabric.api.util.TriState;
//import net.minecraft.client.renderer.block.model.BlockModelPart;
//import net.minecraft.client.renderer.block.model.BlockStateModel;
//import net.minecraft.client.renderer.block.model.TextureSlots;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.client.resources.model.Material;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.resources.Identifier;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.BlockAndTintGetter;
//import net.minecraft.world.level.block.state.BlockState;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.function.Predicate;
//
////as an example
//@Deprecated
//public abstract class StainedGlass implements BlockStateModel {
//    static String getNamespace() throws Exception {
//        throw new Exception("You must override this function");
//    }
//
//
//    public BlockModelPart part;
//
//
//	@Override
//	public void collectParts(RandomSource randomSource, List<BlockModelPart> list) {
//		this.part = list.getFirst();
//	}
//
//	@Override
//    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter blockView, BlockPos pos, BlockState state, RandomSource random, Predicate<@org.jspecify.annotations.Nullable Direction> cullTest){
//        final TriState ao = TriState.DEFAULT;
//        for (Direction cullFace : Direction.values()) {
//            if(cullFace == null)continue;
//            if (cullTest.test(cullFace)) {
//                // Skip entire quad list if possible.
//                continue;
//            }
//            boolean[] booleans = Util.isGlass(cullFace, blockView, pos);
//            TextureAtlasSprite sprite = getSprite(booleans[0], booleans[1], booleans[2], booleans[3]);
//            emitter.square(cullFace, 0.0f, 0.0f, 1.0f, 1.0f, 0f);
//            emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
//            emitter.color(-1, -1, -1, -1);
//            emitter.ambientOcclusion(ao);
//            emitter.shadeMode(ShadeMode.VANILLA);
//            emitter.emit();
//
//            TextureAtlasSprite pixels = getPixelSprite(!booleans[4], !booleans[5], !booleans[6], !booleans[7]);
//            if(pixels != null){
//                emitter.square(cullFace, 0.0f, 0.0f, 1.0f, 1.0f, 0f);
//                emitter.spriteBake(pixels, MutableQuadView.BAKE_LOCK_UV);
//                emitter.color(-1, -1, -1, -1);
//                emitter.ambientOcclusion(ao);
//                emitter.shadeMode(ShadeMode.VANILLA);
//                emitter.emit();
//            }
//        }
//    }
//
//    public static Material[] SPRITE_IDENTIFIERS;
//
//
//    public static TextureSlots.Data textures;
//
//    public static TextureAtlasSprite getSprite(boolean up, boolean down, boolean left, boolean right){
//        String string = "";
//        if(up)string += "_up";
//        if(down)string += "_down";
//        if(left)string += "_left";
//        if(right)string += "_right";
//        if(string.length() > 1)string = string.substring(1);
//        Integer i = Util.map.get(string);
//        return Util.getSprite(SPRITE_IDENTIFIERS[i]);
//    }
//
//    @Nullable
//    public static TextureAtlasSprite getPixelSprite(boolean up_left, boolean up_right, boolean down_left, boolean down_right){
//        if(up_left || up_right || down_left || down_right){
//            String string = "pixel";
//            if(up_left)string += "_up_left";
//            if(up_right)string += "_up_right";
//            if(down_left)string += "_down_left";
//            if(down_right)string += "_down_right";
//            return Util.getSprite(SPRITE_IDENTIFIERS[Util.map.get(string)]);
//        }
//        else return null;
//    }
//
//    public static TextureSlots.Data initTextures(){
//	    TextureSlots.Data.Builder builder = new TextureSlots.Data.Builder();
//        for(Map.Entry<String, Integer> entry : Util.map.entrySet()){
//            builder.addTexture(entry.getKey(), SPRITE_IDENTIFIERS[entry.getValue()]);
//        }
//        return builder.build();
//    }
//    public static Identifier of(String path){
//        return Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, path);
//    }
//}
