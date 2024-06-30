package borknbeans.artisanstools;

import net.minecraft.block.Block;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public class ModularToolItem extends MiningToolItem {


    public ModularToolItem(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();

        NbtCompound nbt = new NbtCompound();
        nbt.putString("TEST", "TEST");
        NbtComponent component = NbtComponent.of(nbt);
        stack.set(DataComponentTypes.ENTITY_DATA, component);
        return stack;
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        boolean value = super.onStackClicked(stack, slot, clickType, player);

        System.out.println("Here!");

        stack.set(DataComponentTypes.DAMAGE, 1);

        NbtCompound nbt = new NbtCompound();
        nbt.putInt("Head", 0xFFAA00AA);

        NbtComponent component = NbtComponent.of(nbt);
        stack.set(DataComponentTypes.CUSTOM_DATA, component);

        return value;
    }
}
