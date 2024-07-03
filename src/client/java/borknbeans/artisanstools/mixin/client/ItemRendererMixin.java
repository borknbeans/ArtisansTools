package borknbeans.artisanstools.mixin.client;

import borknbeans.artisanstools.item.ModItems;
import borknbeans.artisanstools.materials.Materials;
import borknbeans.artisanstools.util.ModDataComponentTypes;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Inject(at = @At(value = "HEAD"), method = "renderBakedItemModel", cancellable = true)
    public void renderBakedItemModel(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices, CallbackInfo ci) {
        if (stack.isOf(ModItems.PICKAXE)) {
            renderBakedItemModelWithColor(model, stack, light, overlay, matrices, vertices, 0f, 0f, 0f);
            ci.cancel();
        }
    }

    private void renderBakedItemModelWithColor(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertices, float red, float green, float blue) {
        Random random = Random.create();
        for (Direction direction : Direction.values()) {
            random.setSeed(42L);
            renderBakedItemQuadsWithColor(matrices, vertices, model.getQuads(null, direction, random), stack, light, overlay);
        }
        random.setSeed(42L);
        renderBakedItemQuadsWithColor(matrices, vertices, model.getQuads(null, null, random), stack, light, overlay);
    }

    private void renderBakedItemQuadsWithColor(MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int light, int overlay) {
        NbtComponent artisansToolsData = stack.getComponents().get(ModDataComponentTypes.ARTISANS_TOOLS);
        NbtList materialsList = null;
        if (artisansToolsData != null) {
            NbtCompound data = artisansToolsData.copyNbt();

            materialsList = data.getList("materials", 8);
        }

        MatrixStack.Entry entry = matrices.peek();

        String curr = "";
        int layerIndex = -1;
        Materials material = Materials.WOOD; // Default
        for (BakedQuad bakedQuad : quads) {
            String layerName = bakedQuad.getSprite().getContents().getId().getPath();
            if (!curr.equals(layerName)) {
                curr = layerName;
                layerIndex++;
                if (materialsList != null && layerIndex < materialsList.size()) {
                    material = Materials.valueOf(materialsList.getString(layerIndex));
                }
            }

            int color = material.getColor();

            float f = (float) ColorHelper.Argb.getAlpha((int)color) / 255.0f;
            float g = (float)ColorHelper.Argb.getRed((int)color) / 255.0f;
            float h = (float)ColorHelper.Argb.getGreen((int)color) / 255.0f;
            float j = (float)ColorHelper.Argb.getBlue((int)color) / 255.0f;
            vertices.quad(entry, bakedQuad, g, h, j, f, light, overlay);
        }
    }

}
