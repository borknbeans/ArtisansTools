package borknbeans.artisanstools;

import borknbeans.artisanstools.screen.ItemContainerScreen;
import borknbeans.artisanstools.screen.ItemForgeScreen;
import borknbeans.artisanstools.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ArtisansToolsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		HandledScreens.register(ModScreenHandlers.ITEM_CONTAINER_SCREEN_HANDLER, ItemContainerScreen::new);
		HandledScreens.register(ModScreenHandlers.ITEM_FORGE_SCREEN_HANDLER, ItemForgeScreen::new);
    }
}