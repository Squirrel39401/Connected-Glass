//package com.cypermethrin.connectedglass.BlockBakedModel.Glass;
//
//
//import com.cypermethrin.connectedglass.ConnectedGlass;
//import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
//import net.minecraft.resources.Identifier;
//
//@Deprecated
//public class GlassModelLoadingPlugin implements ModelLoadingPlugin {
//    public static final Identifier FRAMED_GLASS_MODEL = Identifier.fromNamespaceAndPath(ConnectedGlass.MOD_ID, "framed_glass");
//
//    @Override
//    public void initialize(Context pluginContext) {
//        pluginContext.modifyModelOnLoad().register((model, context) -> {
//            if(context.id() != null & context.id().equals(FRAMED_GLASS_MODEL)){
//                ConnectedGlass.LOGGER.info("Success3 Textures:{}", GlassTextures.textures);
//                //return new StatedGlassModel();
//	            return null;
//            }
//            return model;
//        });
//    }
//}
