package com.cypermethrin.connectedglass.BlockBakedModel.Glass;


import com.cypermethrin.connectedglass.ConnectedGlass;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.mesh.ShadeMode;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBlockModels;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvedModel;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@Environment(EnvType.CLIENT)
public class StatedGlassModel implements BlockStateModel{
    public BlockModelPart part;

    public StatedGlassModel(){}

    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter blockView, BlockPos pos, BlockState state, RandomSource random, Predicate<@Nullable Direction> cullTest) {
        final TriState ao = TriState.DEFAULT;
        for (Direction cullFace : Direction.values()) {
            if(cullFace == null)continue;
            if (cullTest.test(cullFace)) {
                // Skip entire quad list if possible.
                continue;
            }
            boolean[] booleans = isGlass(cullFace, blockView, pos);
            TextureAtlasSprite sprite = GlassTextures.getSprite(booleans[0], booleans[1], booleans[2], booleans[3]);
            emitter.square(cullFace, 0.0f, 0.0f, 1.0f, 1.0f, 0f);
            emitter.spriteBake(sprite, MutableQuadView.BAKE_LOCK_UV);
            emitter.color(-1, -1, -1, -1);
            emitter.ambientOcclusion(ao);
            emitter.shadeMode(ShadeMode.VANILLA);
            emitter.emit();

            TextureAtlasSprite pixels = GlassTextures.getPixelSprite(!booleans[4], !booleans[5], !booleans[6], !booleans[7]);
            if(pixels != null){
                emitter.square(cullFace, 0.0f, 0.0f, 1.0f, 1.0f, 0f);
                emitter.spriteBake(pixels, MutableQuadView.BAKE_LOCK_UV);
                emitter.color(-1, -1, -1, -1);
                emitter.ambientOcclusion(ao);
                emitter.shadeMode(ShadeMode.VANILLA);
                emitter.emit();
            }
        }
    }

	@Override
	public void collectParts(RandomSource randomSource, List<BlockModelPart> list) {
		this.part = list.getFirst();
	}

	@Override
	public TextureAtlasSprite particleIcon() {
		return null;
	}

	private record StatedGlassModelRecord(boolean up , boolean down, boolean north, boolean south, boolean west, boolean east){

    }

    private static StatedGlassModelRecord createRecord(boolean[] booleans){
        if(booleans.length != 6)return null;
        return new StatedGlassModelRecord(booleans[0], booleans[1], booleans[2], booleans[3], booleans[4], booleans[5]);
    }

    private static boolean[] isGlass(Direction direction, BlockAndTintGetter world, BlockPos pos){
        //up down left right
        boolean[] booleans = new boolean[8];
        if(direction == Direction.DOWN){
            booleans[0] = isGlass(world, pos.south());
            booleans[1] = isGlass(world, pos.north());
            booleans[2] = isGlass(world, pos.west());
            booleans[3] = isGlass(world, pos.east());


            booleans[4] = isGlass(world, pos.south().west());
            booleans[5] = isGlass(world, pos.south().east());
            booleans[6] = isGlass(world, pos.north().west());
            booleans[7] = isGlass(world, pos.north().east());
        }
        else if(direction.getAxis() == Direction.Axis.Y){
            booleans[0] = isGlass(world, pos.north());
            booleans[1] = isGlass(world, pos.south());
            booleans[2] = isGlass(world, pos.west());
            booleans[3] = isGlass(world, pos.east());


            booleans[4] = isGlass(world, pos.north().west());
            booleans[5] = isGlass(world, pos.north().east());
            booleans[6] = isGlass(world, pos.south().west());
            booleans[7] = isGlass(world, pos.south().east());
        }
        else if(direction.getAxis() == Direction.Axis.X){
            booleans[0] = isGlass(world, pos.above());
            booleans[1] = isGlass(world, pos.below());
            booleans[2] = direction == Direction.EAST ? isGlass(world, pos.south()) : isGlass(world, pos.north());
            booleans[3] = direction == Direction.EAST ? isGlass(world, pos.north()) : isGlass(world, pos.south());

            booleans[4] = isGlass(world, direction == Direction.EAST ? pos.above().south() : pos.above().north());
            booleans[5] = isGlass(world, direction == Direction.EAST ? pos.above().north() : pos.above().south());
            booleans[6] = isGlass(world, direction == Direction.EAST ? pos.below().south() : pos.below().north());
            booleans[7] = isGlass(world, direction == Direction.EAST ? pos.below().north() : pos.below().south());
        }
        else if(direction.getAxis() == Direction.Axis.Z){
            booleans[0] = isGlass(world, pos.above());
            booleans[1] = isGlass(world, pos.below());
            booleans[2] = direction == Direction.SOUTH ? isGlass(world, pos.west()) : isGlass(world, pos.east());
            booleans[3] = direction == Direction.SOUTH ? isGlass(world, pos.east()) : isGlass(world, pos.west());

            booleans[4] = isGlass(world, direction == Direction.SOUTH ? pos.above().west() : pos.above().east());
            booleans[5] = isGlass(world, direction == Direction.SOUTH ? pos.above().east() : pos.above().west());
            booleans[6] = isGlass(world, direction == Direction.SOUTH ? pos.below().west() : pos.below().east());
            booleans[7] = isGlass(world, direction == Direction.SOUTH ? pos.below().east() : pos.below().west());
        }
        return booleans;
    }

    private static boolean isGlass(BlockAndTintGetter view, BlockPos pos){
        return view.getBlockState(pos).is(BlockTags.IMPERMEABLE);
    }

    private static final HashMap<boolean[], StatedGlassModelRecord> recordHashMap = new HashMap<boolean[], StatedGlassModelRecord>(){{
        for(int i = 0;i < 64; i++){
            boolean[] booleans = new boolean[6];
            booleans[0] = (i << 6) != 0;
            booleans[1] = (i << 5) != 0;
            booleans[2] = (i << 4) != 0;
            booleans[3] = (i << 3) != 0;
            booleans[4] = (i << 2) != 0;
            booleans[5] = (i << 1) != 0;
            put(booleans, createRecord(booleans));
        }
    }};

/*
    public record Unbaked() implements BlockStateModel.Unbaked{
	    @Override
	    public BlockStateModel bake(ModelBaker modelBaker) {
		    return new StatedGlassModel();
	    }

	    @Override
	    public void resolveDependencies(Resolver resolver) {
		    resolver.markDependency(Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, "framed_glass"));
	    }
    }
*/
}
