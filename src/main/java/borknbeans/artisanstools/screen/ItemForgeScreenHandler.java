package borknbeans.artisanstools.screen;

import borknbeans.artisanstools.block.ModBlocks;
import borknbeans.artisanstools.item.ModItems;
import borknbeans.artisanstools.item.ModularPickaxeItem;
import borknbeans.artisanstools.materials.Materials;
import borknbeans.artisanstools.util.ModDataComponentTypes;
import borknbeans.artisanstools.util.ModTags;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class ItemForgeScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;

    private final CraftingInventory inputInventory;
    private final SimpleInventory outputInventory;

    private final PlayerEntity player;

    public ItemForgeScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public ItemForgeScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.ITEM_FORGE_SCREEN_HANDLER, syncId);
        this.context = context;
        this.player = playerInventory.player;

        inputInventory = new CraftingInventory(this, 3, 3);
        outputInventory = new SimpleInventory(1);

        int m;
        int l;

        this.addSlot(new Slot(outputInventory, 0, 134, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false; // Output slot cannot accept items
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {

                for (int i = 0; i < inputInventory.size(); i ++) {
                    if (!inputInventory.getStack(i).isEmpty()) {
                        inputInventory.removeStack(i, 1);
                    }
                }

                super.onTakeItem(player, stack);
            }
        });

        // Our inventory
        for (m = 0; m < 3; ++m) {
            this.addSlot(new Slot(inputInventory, m, 26 + m * 19, 54 - m * 19));
        }

        // The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        // The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    private void updateResult(ScreenHandler handler, World world, PlayerEntity player) {
        if (world.isClient) {
            return;
        }

        // TODO: This all needs to be rewritten nicely
        // ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;

        ItemStack s0 = inputInventory.getStack(0);
        ItemStack s1 = inputInventory.getStack(1);
        ItemStack s2 = inputInventory.getStack(2);

        if (!s0.isEmpty() && !s1.isEmpty() && !s2.isEmpty() && s0.isIn(ModTags.Items.HANDLES) && s1.isIn(ModTags.Items.BINDINGS) && s2.isIn(ModTags.Items.PICKAXE_HEADS)) {


            NbtCompound c0 = s0.getComponents().get(ModDataComponentTypes.ARTISANS_TOOLS).copyNbt();
            NbtCompound c1 = s1.getComponents().get(ModDataComponentTypes.ARTISANS_TOOLS).copyNbt();
            NbtCompound c2 = s2.getComponents().get(ModDataComponentTypes.ARTISANS_TOOLS).copyNbt();

            String m0 = c0.getList("materials", 8).getString(0); // handle
            String m1 = c1.getList("materials", 8).getString(0); // binding
            String m2 = c2.getList("materials", 8).getString(0); // pick head

            ModularPickaxeItem pickaxe = (ModularPickaxeItem)(ModItems.PICKAXE);
            ItemStack recipeResult = pickaxe.getModularPickaxe(Materials.valueOf(m2.toString()), Materials.valueOf(m1.toString()), Materials.valueOf(m0.toString()));
            recipeResult.apply(ModDataComponentTypes.ARTISANS_TOOLS, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> {

                NbtList materialList = new NbtList();
                materialList.add(0, NbtString.of(m0)); // handle
                materialList.add(1, NbtString.of(m2)); // pickaxe head
                materialList.add(2, NbtString.of( m1)); // binding

                currentNbt.put("materials", materialList);
            }));

            outputInventory.setStack(0, recipeResult);
            // serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 0, recipeResult));
        } else if (!s0.isEmpty() && s0.isIn(ModTags.Items.MATERIALS)) {
            ItemStack recipeResult = ModItems.HANDLE.getDefaultStack();

            Materials material = getMaterial(s0);

            recipeResult.apply(ModDataComponentTypes.ARTISANS_TOOLS, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> {
                NbtList materialList = new NbtList();
                NbtString matNbt = NbtString.of(material.toString());
                materialList.add(0, matNbt);
                currentNbt.put("materials", materialList);
            }));

            outputInventory.setStack(0, recipeResult);
        } else if (!s1.isEmpty() && s1.isIn(ModTags.Items.MATERIALS)) {
            ItemStack recipeResult = ModItems.BINDING.getDefaultStack();

            Materials material = getMaterial(s1);

            recipeResult.apply(ModDataComponentTypes.ARTISANS_TOOLS, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> {
                NbtList materialList = new NbtList();
                NbtString matNbt = NbtString.of(material.toString());
                materialList.add(0, matNbt);
                currentNbt.put("materials", materialList);
            }));

            outputInventory.setStack(0, recipeResult);
        } else if (!s2.isEmpty() && s2.isIn(ModTags.Items.MATERIALS)) {
            ItemStack recipeResult = ModItems.PICKAXE_HEAD.getDefaultStack();

            Materials material = getMaterial(s2);

            recipeResult.apply(ModDataComponentTypes.ARTISANS_TOOLS, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> {
                NbtList materialList = new NbtList();
                NbtString matNbt = NbtString.of(material.toString());
                materialList.add(0, matNbt);
                currentNbt.put("materials", materialList);
            }));

            outputInventory.setStack(0, recipeResult);
        }
    }

    private Materials getMaterial(ItemStack stack) {
        if (stack.isIn(ModTags.Items.WOOD)) {
            return Materials.WOOD;
        } else if (stack.isIn(ModTags.Items.STONE)) {
            return Materials.STONE;
        } else if (stack.isIn(ModTags.Items.IRON)) {
            return Materials.IRON;
        } else if (stack.isIn(ModTags.Items.GOLD)) {
            return Materials.GOLD;
        }
        return null;
    }

    @Override
    public void onContentChanged(Inventory inventory) {

        super.onContentChanged(inventory);

        this.context.run((world, pos) -> updateResult(this, world, this.player));
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.inputInventory));
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
