package org.bitwisemadness.infinitedungeon.end;

import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import java.util.Map;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.bitwisemadness.infinitedungeon.end.datagen.DimensionContent;
import org.bitwisemadness.infinitedungeon.end.datagen.JsonFilesProvider;
import org.slf4j.Logger;

/**
 * Infinite Dungeon: End - the End dungeon dimension addon (charter #1).
 *
 * <p>The addon ships one dungeon dimension with its own portal rules via an
 * {@code infinite_dungeon} dimension profile: the core suite resolves every
 * rule per dimension (profile first, config default behind), so this mod is
 * pure data plus wiring - absent, the dimension simply does not exist and the
 * core suite is untouched (the I32 platform posture: dependencies on the
 * infinite-dungeon-api platform, never on the core mod).</p>
 */
@Mod(InfiniteDungeonEnd.MOD_ID)
public final class InfiniteDungeonEnd {

    public static final String MOD_ID = "infinite_dungeon_end";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InfiniteDungeonEnd(IEventBus modBus) {
        modBus.addListener(this::gatherData);
        LOGGER.info("Infinite Dungeon: End initialized");
    }

    /**
     * Feeds the dimension scaffold (issue #4, step 2): the dimension, its
     * type, the biome, the mirror structure set, the Dungeon Crawl biome-gate
     * tag entry and the dimension profile - the datagen tables in
     * {@link DimensionContent}.
     */
    private void gatherData(GatherDataEvent event) {
        if (!event.includeServer()) {
            return;
        }
        event.addProvider(new JsonFilesProvider("end_dimension_scaffold",
                event.getGenerator().getPackOutput(), files()));
    }

    private static Map<String, JsonObject> files() {
        return DimensionContent.files();
    }
}
