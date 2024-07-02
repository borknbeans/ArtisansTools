package borknbeans.artisanstools.screen;

import borknbeans.artisanstools.ArtisansTools;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ItemContainerScreenHandler> ITEM_CONTAINER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(ArtisansTools.MOD_ID, "item_container"),
                    new ScreenHandlerType<>(ItemContainerScreenHandler::new,
                            FeatureFlags.DEFAULT_ENABLED_FEATURES)
            );

    public static final ScreenHandlerType<ItemForgeScreenHandler> ITEM_FORGE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(ArtisansTools.MOD_ID, "item_forge"),
                    new ScreenHandlerType<>(ItemForgeScreenHandler::new,
                            FeatureFlags.DEFAULT_ENABLED_FEATURES)
            );

    public static void registerScreenHandlers() {
        ArtisansTools.LOGGER.info("Registering Screen Handlers for " + ArtisansTools.MOD_ID);


    }
}
