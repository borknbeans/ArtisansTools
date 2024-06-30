package borknbeans.artisanstools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

public class ArtisansToolsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BuiltinItemRendererRegistry.INSTANCE.register(ModItems.COMBINED_ITEM, new CustomPickaxeRenderer());

    }
}