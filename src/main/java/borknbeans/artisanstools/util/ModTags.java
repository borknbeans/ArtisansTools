package borknbeans.artisanstools.util;

import borknbeans.artisanstools.ArtisansTools;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ArtisansTools.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TOOL_PARTS = createTag("tool_parts");

        public static final TagKey<Item> PICKAXE_HEADS = createTag("pickaxe_heads");
        public static final TagKey<Item> HANDLES = createTag("handles");
        public static final TagKey<Item> BINDINGS = createTag("bindings");

        public static final TagKey<Item> MATERIALS = createTag("materials");

        public static final TagKey<Item> WOOD = createTag("wood");
        public static final TagKey<Item> STONE = createTag("stone");
        public static final TagKey<Item> IRON = createTag("iron");
        public static final TagKey<Item> GOLD = createTag("gold");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ArtisansTools.MOD_ID, name));
        }
    }
}
