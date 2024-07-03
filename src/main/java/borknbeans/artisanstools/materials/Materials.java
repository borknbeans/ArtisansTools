package borknbeans.artisanstools.materials;

import borknbeans.artisanstools.ArtisansTools;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

public enum Materials {
    WOOD(Identifier.of(ArtisansTools.MOD_ID, "wood"), 0xFF6A501E),
    STONE(Identifier.of(ArtisansTools.MOD_ID, "stone"), 0xFF767676),
    IRON(Identifier.of(ArtisansTools.MOD_ID, "iron"), 0xFFD8D8D8),
    GOLD(Identifier.of(ArtisansTools.MOD_ID, "gold"), 0xFFFDF55F);


    private final Identifier id;
    private final int color;

    private static final Random random = Random.create(System.currentTimeMillis());

    Materials(Identifier id, int color) {
        this.id = id;
        this.color = color;
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
}
