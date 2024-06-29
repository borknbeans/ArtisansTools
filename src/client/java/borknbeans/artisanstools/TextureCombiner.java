package borknbeans.artisanstools;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;

public class TextureCombiner {

    public static void combineAndSaveTexture(Identifier firstTextureId, Identifier secondTextureId) {
        try (NativeImage combinedTexture = combineTextures(firstTextureId, secondTextureId)){
            saveCombinedTexture(combinedTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NativeImage combineTextures(Identifier firstTextureId, Identifier secondTextureId) throws IOException {
        NativeImage firstImage = loadImage(firstTextureId);
        NativeImage secondImage = loadImage(secondTextureId);

        int width = Math.max(firstImage.getWidth(), secondImage.getWidth());
        int height = Math.max(firstImage.getHeight(), secondImage.getHeight());

        NativeImage combinedImage = new NativeImage(width, height, true);

        // Combine pixels with the second image taking priority
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int combinedPixel = getPriorityPixel(firstImage, secondImage, x, y);
                combinedImage.setColor(x, y, combinedPixel);
            }
        }

        firstImage.close();
        secondImage.close();

        return combinedImage;
    }

    private static void saveCombinedTexture(NativeImage image) throws IOException {
        // Save the combined texture to the game's virtual file system
        String path = "src/main/resources/assets/artisans-tools/textures/item/combined_item.png";
        File file = new File(path);
        file.getParentFile().mkdirs();
        image.writeTo(file);
        image.close();
    }

    private static int getPriorityPixel(NativeImage firstImage, NativeImage secondImage, int x, int y) {
        // Check if pixel from the second image is valid and not fully transparent
        if (x < secondImage.getWidth() && y < secondImage.getHeight()) {
            int color = secondImage.getColor(x, y);
            if (color != 0) {
                return color;
            }
        }
        // Use pixel from the first image if second image pixel is fully transparent or out of bounds
        if (x < firstImage.getWidth() && y < firstImage.getHeight()) {
            return firstImage.getColor(x, y);
        }
        // Default to transparent if out of bounds
        return 0;
    }

    private static NativeImage loadImage(Identifier textureId) throws IOException {
        return NativeImage.read(MinecraftClient.getInstance().getResourceManager().getResource(textureId).get().getInputStream());
    }
}
