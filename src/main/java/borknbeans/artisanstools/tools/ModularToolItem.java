package borknbeans.artisanstools.tools;

import borknbeans.artisanstools.ArtisansTools;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.world.World;

public class ModularToolItem extends MiningToolItem {
    public ModularToolItem(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    public ModularToolItem(ModularToolSettings settings) {

        // TODO: Get materials from the components and grab this stuff dynamically

        super(ToolMaterials.DIAMOND, BlockTags.PICKAXE_MINEABLE, settings);
    }
}
