package borknbeans.artisanstools.tools;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class ModularToolItem extends MiningToolItem {
    public ModularToolItem(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    public ModularToolItem(ModularToolSettings settings) {

        // TODO: Get materials from the components and grab this stuff dynamically

        super(ToolMaterials.DIAMOND, BlockTags.PICKAXE_MINEABLE, settings);
    }
}
