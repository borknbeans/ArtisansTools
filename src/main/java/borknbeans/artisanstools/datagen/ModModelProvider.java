package borknbeans.artisanstools.datagen;

import borknbeans.artisanstools.ArtisansTools;
import borknbeans.artisanstools.item.ModItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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

        Supplier<JsonElement> pickaxeModelJson = () -> generateCustomItemModelJson(Arrays.asList(
                Identifier.of(ArtisansTools.MOD_ID, "item/handle_part"),
                Identifier.of(ArtisansTools.MOD_ID, "item/pickaxe_head_part"),
                Identifier.of(ArtisansTools.MOD_ID, "item/binding_part")
        ));
        itemModelGenerator.writer.accept(Identifier.of(ArtisansTools.MOD_ID, "item/pickaxe"), pickaxeModelJson);
    }

    private JsonElement generateCustomItemModelJson(List<Identifier> textures) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "item/generated");

        JsonObject displayJson = new JsonObject();

        JsonObject thirdPersonRightHand = new JsonObject();
        thirdPersonRightHand.add("rotation", createJsonArray(0, -90, 55));
        thirdPersonRightHand.add("translation", createJsonArray(0, 4.0, 0.5));
        thirdPersonRightHand.add("scale", createJsonArray(0.85, 0.85, 0.85));
        displayJson.add("thirdperson_righthand", thirdPersonRightHand);

        JsonObject thirdPersonLeftHand = new JsonObject();
        thirdPersonLeftHand.add("rotation", createJsonArray(0, 90, -55));
        thirdPersonLeftHand.add("translation", createJsonArray(0, 4.0, 0.5));
        thirdPersonLeftHand.add("scale", createJsonArray(0.85, 0.85, 0.85));
        displayJson.add("thirdperson_lefthand", thirdPersonLeftHand);

        JsonObject firstPersonRightHand = new JsonObject();
        firstPersonRightHand.add("rotation", createJsonArray(0, -90, 25));
        firstPersonRightHand.add("translation", createJsonArray(1.13, 3.2, 1.13));
        firstPersonRightHand.add("scale", createJsonArray(0.68, 0.68, 0.68));
        displayJson.add("firstperson_righthand", firstPersonRightHand);

        JsonObject firstPersonLeftHand = new JsonObject();
        firstPersonLeftHand.add("rotation", createJsonArray(0, 90, -25));
        firstPersonLeftHand.add("translation", createJsonArray(1.13, 3.2, 1.13));
        firstPersonLeftHand.add("scale", createJsonArray(0.68, 0.68, 0.68));
        displayJson.add("firstperson_lefthand", firstPersonLeftHand);

        json.add("display", displayJson);

        JsonObject texturesJson = new JsonObject();
        for (int i = 0; i < textures.size(); i++) {
            texturesJson.addProperty("layer" + i, textures.get(i).toString());
        }

        json.add("textures", texturesJson);

        return json;
    }

    private JsonArray createJsonArray(double... values) {
        JsonArray jsonArray = new JsonArray();
        for (double value : values) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

}
