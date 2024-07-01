package borknbeans.artisanstools;

import borknbeans.artisanstools.block.ModBlocks;
import borknbeans.artisanstools.item.ModItemGroups;
import borknbeans.artisanstools.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ArtisansTools implements ModInitializer {
	public static final String MOD_ID = "artisans-tools";
    public static final Logger LOGGER = LoggerFactory.getLogger("artisans-tools");

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}