package borknbeans.artisanstools.datagen;

import borknbeans.artisanstools.item.ModItems;
import borknbeans.artisanstools.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.PICKAXE_HEADS)
                .add(ModItems.PICKAXE_HEAD);

        getOrCreateTagBuilder(ModTags.Items.BINDINGS)
                .add(ModItems.BINDING);

        getOrCreateTagBuilder(ModTags.Items.HANDLES)
                .add(ModItems.HANDLE);

        getOrCreateTagBuilder(ModTags.Items.TOOL_PARTS)
                .forceAddTag(ModTags.Items.PICKAXE_HEADS)
                .forceAddTag(ModTags.Items.BINDINGS)
                .forceAddTag(ModTags.Items.HANDLES);

        getOrCreateTagBuilder(ModTags.Items.WOOD)
                .forceAddTag(ItemTags.PLANKS);

        getOrCreateTagBuilder(ModTags.Items.STONE)
                .add(Items.STONE)
                .add(Items.COBBLESTONE);

        getOrCreateTagBuilder(ModTags.Items.IRON)
                .add(Items.IRON_INGOT);

        getOrCreateTagBuilder(ModTags.Items.GOLD)
                .add(Items.GOLD_INGOT);

        getOrCreateTagBuilder(ModTags.Items.MATERIALS)
                .forceAddTag(ModTags.Items.WOOD)
                .forceAddTag(ModTags.Items.STONE)
                .forceAddTag(ModTags.Items.IRON)
                .forceAddTag(ModTags.Items.GOLD);

        // TODO: Add pickaxe to pickaxe tag? https://fabricmc.net/wiki/tutorial:mining_levels#tool_tags
    }
}
