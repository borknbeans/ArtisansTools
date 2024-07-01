package borknbeans.artisanstools.datagen;

import borknbeans.artisanstools.ArtisansTools;
import borknbeans.artisanstools.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PICKAXE_HEAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BINDING, Models.GENERATED);
        itemModelGenerator.register(ModItems.HANDLE, Models.GENERATED);

        Models.GENERATED_THREE_LAYERS.upload(
                Identifier.of(ArtisansTools.MOD_ID, "item/pickaxe"),
                TextureMap.layered(
                        Identifier.of(ArtisansTools.MOD_ID, "item/handle"),
                        Identifier.of(ArtisansTools.MOD_ID, "item/pickaxe_head"),
                        Identifier.of(ArtisansTools.MOD_ID, "item/binding")), itemModelGenerator.writer);
    }
}
