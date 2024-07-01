package borknbeans.artisanstools.item;

import borknbeans.artisanstools.ArtisansTools;
import borknbeans.artisanstools.tools.ModularToolItem;
import borknbeans.artisanstools.tools.ModularToolSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item PICKAXE_HEAD = registerItem("pickaxe_head", new Item(new Item.Settings()));
    public static final Item HANDLE = registerItem("handle", new Item(new Item.Settings()));
    public static final Item BINDING = registerItem("binding", new Item(new Item.Settings()));

    public static final Item PICKAXE = registerItem("pickaxe", new Item(new Item.Settings()));

    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(PICKAXE);
    }

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(PICKAXE_HEAD);
        entries.add(HANDLE);
        entries.add(BINDING);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ArtisansTools.MOD_ID, name),  item);
    }

    public static void registerModItems() {
        ArtisansTools.LOGGER.info("Registering Mod Items for " + ArtisansTools.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
