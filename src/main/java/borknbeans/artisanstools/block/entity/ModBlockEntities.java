package borknbeans.artisanstools.block.entity;

import borknbeans.artisanstools.ArtisansTools;
import borknbeans.artisanstools.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<ItemForgeBlockEntity> ITEM_FORGE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ArtisansTools.MOD_ID, "item_forge_be"),
                    FabricBlockEntityTypeBuilder.create(ItemForgeBlockEntity::new,
                            ModBlocks.ITEM_FORGE_BLOCK).build());

    public static void registerBlockEntities() {
        ArtisansTools.LOGGER.info("Registering Block Entities for " + ArtisansTools.MOD_ID);
    }
}
