package borknbeans.artisanstools.materials;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public interface IArtisanMaterial extends ToolMaterial {

    @Override
    int getDurability();

    @Override
    float getMiningSpeedMultiplier();

    @Override
    float getAttackDamage();

    @Override
    int getEnchantability();

    @Override
    Ingredient getRepairIngredient();


}
