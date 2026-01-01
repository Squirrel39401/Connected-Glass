package com.cypermethrin.connectedglass;

import com.cypermethrin.connectedglass.BlockBakedModel.Util;
import net.fabricmc.api.ClientModInitializer;

public class ConnectedGlassClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Util.init();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}