package borknbeans.artisanstools;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class CustomPickaxeRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private void renderBakedModelWithColor(BakedModel model, MatrixStack matrix, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue) {
        for (Direction direction : Direction.values()) {
            renderQuads(model.getQuads(null, direction, Random.create()), matrix, vertexConsumer, light, overlay, red, green, blue);
        }
        renderQuads(model.getQuads(null, null, Random.create()), matrix, vertexConsumer, light, overlay, red, green, blue);
    }

    private void renderQuads(List<BakedQuad> quads, MatrixStack matrix, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue) {
        for (BakedQuad quad : quads) {
            vertexConsumer.quad(matrix.peek(), quad, red, green, blue, 1.0f, light, overlay);
        }
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BakedModel model = MinecraftClient.getInstance().getItemRenderer().getModel(stack, null, null, 0);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getSolid());

        // Get color from NBT
        int color = 0xFF5555;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;

        matrices.push();
        renderBakedModelWithColor(model, matrices, vertexConsumer, light, overlay, red, green, blue);
        matrices.pop();
    }
}
