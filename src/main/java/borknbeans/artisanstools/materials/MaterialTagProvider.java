package borknbeans.artisanstools.materials;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public abstract class MaterialTagProvider extends FabricTagProvider<ArtisanMaterial> {

    public MaterialTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable FabricTagProvider.BlockTagProvider blockTagProvider) {
        super(output, MaterialRegistry.ARTISAN_MATERIAL, completableFuture);
    }

    public MaterialTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        this(output, completableFuture, null);
    }

    // TODO: Look at other methods from ItemTagProvider to see if they need to be implemented
}
