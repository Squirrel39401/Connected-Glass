package com.cypermethrin.connectedglass.mixin.client;

import com.cypermethrin.connectedglass.BlockBakedModel.Util;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelShaper.class)
public class BlockModelShaperMixin {
	@Inject(method = "getBlockModel", at = @At("HEAD"), cancellable = true)
	void injected(BlockState blockState, CallbackInfoReturnable<BlockStateModel> cir){
		if(Util.MAP.containsKey(blockState.getBlock())){
			cir.setReturnValue(Util.MAP.get(blockState.getBlock()));
		}
	}
}
