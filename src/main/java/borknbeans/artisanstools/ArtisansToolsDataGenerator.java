package borknbeans.artisanstools;

import borknbeans.artisanstools.materials.MaterialRegistry;
import borknbeans.artisanstools.materials.MaterialTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ArtisansToolsDataGenerator implements DataGeneratorEntrypoint {
	/*
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(MaterialProvider::new);
	}

	 */

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(ArtisansMaterialsTagProvider::new);
	}

	public static class ArtisansMaterialsTagProvider extends MaterialTagProvider {
		public ArtisansMaterialsTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
			getOrCreateTagBuilder(TagKey.of(MaterialRegistry.ARTISAN_MATERIAL, Identifier.of("artisans-tools", "materials")))
					.add(Identifier.of("artisans-tools", "combined-item"));
					//.add(Identifier.of("artisans-tools", "gold"))
					//.add(Identifier.of("artisans-tools", "silver"))
					//.add(Identifier.of("artisans-tools", "copper"));
		}
	}
}
/*
class MaterialProvider extends MaterialTagProvider {

	private static final TagKey<ArtisanMaterial> MATERIALS = TagKey.of(MaterialRegistry.ARTISAN_MATERIAL, Identifier.of("artisans-tools", "materials"));

	public MaterialProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		getOrCreateTagBuilder(MATERIALS)
				.add(Identifier.of("artisans-tools", "combined_item"));
	}
}
 */
