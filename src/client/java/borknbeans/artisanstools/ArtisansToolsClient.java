package borknbeans.artisanstools;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class ArtisansToolsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		Identifier firstTexture = Identifier.of("minecraft", "textures/item/diamond_sword.png");
		Identifier secondTexture = Identifier.of("minecraft", "textures/item/iron_pickaxe.png");

        // TextureCombiner.combineAndSaveTexture(firstTexture, secondTexture);
    }
}