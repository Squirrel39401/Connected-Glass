package com.cypermethrin.connectedglass.BlockBakedModel;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.mesh.ShadeMode;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public interface StainedGlassInterface extends BlockStateModel {
	@Override
	default void collectParts(RandomSource randomSource, List<BlockModelPart> list){};


	@Override
	default TextureAtlasSprite particleSprite(BlockAndTintGetter blockView, BlockPos pos, BlockState state) {
		return BlockStateModel.super.particleSprite(blockView, pos, state);
	}

	@Override
	default TextureAtlasSprite particleIcon(){
		return Util.getSprite(this.getSpriteIdentifiers()[0]);
	}


	@Override
    default void emitQuads(QuadEmitter emitter, BlockAndTintGetter blockView, BlockPos pos, BlockState state, RandomSource random, Predicate<@Nullable Direction> cullTest) {
        final TriState ao = TriState.DEFAULT;
        for (Direction cullFace : Direction.values()) {
            if(cullFace == null)continue;
            if (cullTest.test(cullFace) || Util.isGlass(blockView, pos.relative(cullFace))) {
                // Skip entire quad list if possible.
                continue;
            }
            boolean[] booleans = Util.isGlass(cullFace, blockView, pos);
            byte b = Util.getOrCreateCache(booleans);
            TextureAtlasSprite sprite = getSprite(b);
            emitter.square(cullFace, 0.0f, 0.0f, 1.0f, 1.0f, 0f);
            emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.ambientOcclusion(ao);
            emitter.shadeMode(ShadeMode.VANILLA);
            emitter.emit();

            TextureAtlasSprite pixels = getPixelSprite(b);
            if(pixels!=null){
                emitter.square(cullFace, 0.0f, 0.0f, 1.0f, 1.0f, 0f);
                emitter.spriteBake(pixels, MutableQuadView.BAKE_LOCK_UV);
                emitter.color(-1, -1, -1, -1);
                emitter.ambientOcclusion(ao);
                emitter.shadeMode(ShadeMode.VANILLA);
                emitter.emit();
            }

        }
    }

    default TextureAtlasSprite getSprite(boolean up, boolean down, boolean left, boolean right){
        return Util.getSprite(up, down, left, right, this.getIdentifierFactory());
    }

    default TextureAtlasSprite getSprite(byte b){
        return Util.getSprite(b, this.getIdentifierFactory());
    }
    @Nullable
    default TextureAtlasSprite getPixelSprite(byte b){
        return Util.getPixelSprite(b, this.getIdentifierFactory());
    }

    default TextureAtlasSprite getPixelSprite(boolean up, boolean down, boolean left, boolean right, boolean up_left, boolean up_right, boolean down_left, boolean down_right){
        return Util.getPixelSprite(up, down, left, right, up_left, up_right, down_left, down_right, this.getIdentifierFactory());
    }
    Util.Factory getIdentifierFactory();



	Material[] getSpriteIdentifiers();
}
