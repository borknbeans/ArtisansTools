package borknbeans.artisanstools;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item COMBINED_ITEM = new Item(new Item.Settings());

    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of("artisans-tools", "combined_item"), COMBINED_ITEM);
    }
}
