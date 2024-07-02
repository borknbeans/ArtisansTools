package borknbeans.artisanstools.block;

import borknbeans.artisanstools.ArtisansTools;
import borknbeans.artisanstools.block.custom.ItemForgeBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ITEM_FORGE_BLOCK = registerBlock("item_forge", new ItemForgeBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ArtisansTools.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(ArtisansTools.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));

    }

    public static void registerModBlocks() {
        ArtisansTools.LOGGER.info("Registering Mod Blocks for " + ArtisansTools.MOD_ID);
    }
}
