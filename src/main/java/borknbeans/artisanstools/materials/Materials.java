package borknbeans.artisanstools.materials;

import borknbeans.artisanstools.ArtisansTools;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public enum Materials implements ToolMaterial {
    WOOD(Identifier.of(ArtisansTools.MOD_ID, "wood"), 0xFF8c6931, BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0f, 0.0f),
    STONE(Identifier.of(ArtisansTools.MOD_ID, "stone"), 0xFF434343, BlockTags.INCORRECT_FOR_STONE_TOOL,131, 4.0f, 1.0f),
    IRON(Identifier.of(ArtisansTools.MOD_ID, "iron"), 0xFFffffff, BlockTags.INCORRECT_FOR_IRON_TOOL,250, 6.0f, 2.0f),
    GOLD(Identifier.of(ArtisansTools.MOD_ID, "gold"), 0xFFFFD700, BlockTags.INCORRECT_FOR_GOLD_TOOL,32, 12.0f, 0.0f);


    private final Identifier id;
    private final int color;
    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;

    private static final Random random = Random.create(System.currentTimeMillis());

    Materials(Identifier id, int color, TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage) {
        this.id = id;
        this.color = color;
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
    }

    public Identifier getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public static Materials getRandomMaterial() {
        Materials[] materials = values();

        return materials[random.nextInt(materials.length)];
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
