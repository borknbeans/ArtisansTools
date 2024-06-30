package borknbeans.artisanstools;

import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item COMBINED_ITEM = new MiningToolItem(ToolMaterials.DIAMOND, BlockTags.PICKAXE_MINEABLE, new Item.Settings());

    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of("artisans-tools", "combined_item"), COMBINED_ITEM);
    }
}
