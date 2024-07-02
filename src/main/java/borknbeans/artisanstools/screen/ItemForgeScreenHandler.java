package borknbeans.artisanstools.screen;

import borknbeans.artisanstools.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.*;

public class ItemForgeScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;

    public ItemForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public ItemForgeScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.ITEM_FORGE_SCREEN_HANDLER, syncId);
        this.context = context;
        // TODO: Slot placement
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return ItemForgeScreenHandler.canUse(this.context, player, ModBlocks.ITEM_FORGE_BLOCK);
    }
}
