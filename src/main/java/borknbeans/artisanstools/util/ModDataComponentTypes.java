package borknbeans.artisanstools.util;

import borknbeans.artisanstools.ArtisansTools;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static ComponentType<NbtComponent> MATERIALS = registerDataComponentType("materials", builder -> builder.codec(NbtComponent.CODEC));

    private static <T> ComponentType<T> registerDataComponentType(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ArtisansTools.MOD_ID, name), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerModDataComponentTypes() {

    }
}
