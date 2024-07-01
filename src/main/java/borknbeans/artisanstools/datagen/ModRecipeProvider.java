package borknbeans.artisanstools.datagen;

import borknbeans.artisanstools.item.ModItems;
import borknbeans.artisanstools.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    // private static List<ItemConvertible>

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PICKAXE, 1)
                .pattern(" P ")
                .pattern(" B ")
                .pattern(" H ")
                .input('P', ModTags.Items.PICKAXE_HEADS)
                .input('B', ModTags.Items.BINDINGS)
                .input('H', ModTags.Items.HANDLES)
                .criterion(hasItem(ModItems.PICKAXE), conditionsFromItem(ModItems.PICKAXE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PICKAXE)));
    }
}
