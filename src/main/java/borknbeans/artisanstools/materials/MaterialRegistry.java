package borknbeans.artisanstools.materials;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class MaterialRegistry {

    public static final RegistryKey<Registry<ArtisanMaterials>> ARTISAN_MATERIAL = of("artisan_material");
    public static final RegistryKey<ArtisanMaterials> ARTISAN_MATERIAL_KEY = RegistryKey.of(ARTISAN_MATERIAL, Identifier.of("artisans-tools", "artisan_material"));

    private static <T> RegistryKey<Registry<T>> of(String id) {
        return RegistryKey.ofRegistry(Identifier.of("artisan-tools", id));
    }
}