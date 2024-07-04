package borknbeans.artisanstools.item;

import borknbeans.artisanstools.materials.Materials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ModularPickaxeItem extends PickaxeItem {
    private Materials headMaterial, bindingMaterial, handleMaterial;

    public ModularPickaxeItem(Materials headMaterial, Settings settings) {
        super(headMaterial, settings);
        this.headMaterial = headMaterial;
    }

    public ItemStack getModularPickaxe(Materials headMaterial, Materials bindingMaterial, Materials handleMaterial) {
        this.headMaterial = headMaterial;
        this.bindingMaterial = bindingMaterial;
        this.handleMaterial = handleMaterial;
        return this.getDefaultStack();
    }

    @Override
    public Materials getMaterial() {
        return headMaterial;
    }

    private Text getCustomName() {
        String formattedMaterialName = headMaterial.toString().substring(0, 1).toUpperCase() + headMaterial.toString().substring(1).toLowerCase();
        return Text.of(formattedMaterialName + " " + getName().copy().getString());
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();

        stack.apply(DataComponentTypes.CUSTOM_NAME, getName(), name -> getCustomName()); // Update tool name

        ToolComponent toolComponent = headMaterial.createComponent(BlockTags.PICKAXE_MINEABLE);
        stack.apply(DataComponentTypes.TOOL, toolComponent, component -> toolComponent); // Update tool tier level

        // TODO: Modify durability based off all parts
        stack.apply(DataComponentTypes.MAX_DAMAGE, 1, durability -> headMaterial.getDurability()); // Update max durability

        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        int maxDurability = stack.getMaxDamage();
        int currentDurability = stack.getMaxDamage() - stack.getDamage();

        tooltip.add(Text.translatable("tooltip.artisans-tools.durability", currentDurability, maxDurability).formatted(Formatting.WHITE));
    }
}
