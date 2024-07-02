package borknbeans.artisanstools.item;

import borknbeans.artisanstools.ArtisansTools;
import borknbeans.artisanstools.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TEST_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ArtisansTools.MOD_ID, "pickaxe_head"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.test"))
                    .icon(() -> new ItemStack(ModItems.PICKAXE_HEAD)).entries((displayContext, entries) -> {
                        entries.add(ModItems.PICKAXE_HEAD);
                        entries.add(ModItems.HANDLE);
                        entries.add(ModItems.BINDING);

                        entries.add(ModBlocks.ITEM_FORGE_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        ArtisansTools.LOGGER.info("Registering Item Groups for " + ArtisansTools.MOD_ID);
    }
}
