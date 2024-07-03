package borknbeans.artisanstools.tools;

import borknbeans.artisanstools.materials.Materials;
import borknbeans.artisanstools.util.ModDataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;

public class ModularToolSettings extends Item.Settings {
    public ModularToolSettings() {
        // TODO: This is only temporary

        NbtCompound nbt = new NbtCompound();
        NbtList materials = new NbtList();
        NbtString material = NbtString.of(Materials.getRandomMaterial().toString());
        materials.add(0, material);

        nbt.put("materials", materials);

        NbtComponent component = NbtComponent.of(nbt);
        this.component(ModDataComponentTypes.ARTISANS_TOOLS, component);
    }

    // TODO: Constructor that takes in materials

/*
    @Override
    public Item.Settings maxDamage(int maxDamage) {
        this.component(DataComponentTypes.MAX_DAMAGE, maxDamage);
        this.component(DataComponentTypes.MAX_STACK_SIZE, 1);
        this.component(DataComponentTypes.DAMAGE, 0);

        return this;
    }
 */
}
