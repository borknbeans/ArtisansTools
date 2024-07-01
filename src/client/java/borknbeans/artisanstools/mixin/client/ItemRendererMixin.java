package borknbeans.artisanstools.mixin.client;

import borknbeans.artisanstools.tools.ModItems;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
        if (stack.isOf(ModItems.COMBINED_ITEM)) {
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

        MatrixStack.Entry entry = matrices.peek();
        for (BakedQuad bakedQuad : quads) {
            String layerName = bakedQuad.getSprite().getContents().getId().getPath();

            NbtComponent data = stack.get(DataComponentTypes.CUSTOM_DATA);

            // 0xAARRGGBB format
            int i = Colors.WHITE;
            if (data != null) {
                NbtCompound value = data.copyNbt();

                if (layerName.contains("handle")) {
                    i = value.getInt("handle");
                } else if (layerName.contains("head")) {
                    i = value.getInt("head");
                } else if (layerName.contains("binding")) {
                    i = value.getInt("binding");
                }
            }

            float f = (float) ColorHelper.Argb.getAlpha((int)i) / 255.0f;
            float g = (float)ColorHelper.Argb.getRed((int)i) / 255.0f;
            float h = (float)ColorHelper.Argb.getGreen((int)i) / 255.0f;
            float j = (float)ColorHelper.Argb.getBlue((int)i) / 255.0f;
            vertices.quad(entry, bakedQuad, g, h, j, f, light, overlay);
        }
    }

}
