package org.bitwisemadness.infinitedungeon.end.gametest;

import java.util.Optional;
import net.minecraft.core.registries.Registries;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;
import org.bitwisemadness.infinitedungeon.end.InfiniteDungeonEnd;
import org.bitwisemadness.infinitedungeonapi.profile.DimensionProfiles;

/**
 * The dimension scaffold's acceptance (issue #4, step 2): the shipped
 * dimension loads server-side with its type, its biome exists, its mirror
 * structure set and the Dungeon Crawl biome-gate tag resolve, and the
 * infinite-dungeon profile registry carries the addon's profile - which is
 * what makes every profile-driven core consumer treat this dimension as a
 * dungeon dimension.
 */
@GameTestHolder(InfiniteDungeonEnd.MOD_ID)
@PrefixGameTestTemplate(false)
public final class InfiniteDungeonEndGameTests {

    private static final String DIMENSION_ID = "infinite_dungeon_end:end";

    private InfiniteDungeonEndGameTests() {
    }

    /**
     * The dimension's registrations resolve and the gate tag carries the
     * biome. The GameTestServer builds a minimal world stem (vanilla
     * dimensions only - no datapack level ever instantiates here), so the
     * dimension's live load is the in-game verification protocol's check
     * (charter step 5); what the harness CAN prove is that every registry
     * entry the level needs parses and registers, and that the profile
     * registry carries the dimension.
     */
    @GameTest(template = "empty")
    public static void dimension_profile_and_gate_resolve(GameTestHelper helper) {
        var server = helper.getLevel().getServer();

var biomes = server.registryAccess().registryOrThrow(Registries.BIOME);
        helper.assertTrue(biomes.containsKey(ResourceLocation.parse("infinite_dungeon_end:end")),
                "the dimension's biome must be registered");

        var structureSets = server.registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
        helper.assertTrue(structureSets.containsKey(
                        ResourceLocation.parse("infinite_dungeon_end:end")),
                "the mirror structure set must be registered");

        var dimensionTypes = server.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE);
        helper.assertTrue(dimensionTypes.containsKey(
                        ResourceLocation.parse("infinite_dungeon_end:end")),
                "the dimension type must be registered");

        // The gate tag is dungeoncrawl-namespaced; the addon's jar appends its
        // biome to it.
        var gateTag = TagKey.create(Registries.BIOME,
                ResourceLocation.parse("dungeoncrawl:has_structure/dungeon"));
        var held = biomes.getTag(gateTag);
        helper.assertTrue(held.isPresent() && held.get().size() > 0,
                "the Dungeon Crawl biome gate must resolve");
        boolean carriesOwn = held.orElseThrow().stream().anyMatch(h -> h.unwrapKey()
                .map(k -> k.location().toString().equals(DIMENSION_ID))
                .orElse(false));
        helper.assertTrue(carriesOwn,
                "the biome gate must carry this addon's biome");

        helper.succeed();
    }

    /** The profile registry carries the addon's dimension (the consumers' gate). */
    @GameTest(template = "empty")
    public static void profile_registry_carries_the_dimension(GameTestHelper helper) {
        Optional<?> profile = DimensionProfiles.get(DIMENSION_ID);
        helper.assertTrue(profile.isPresent(),
                "the profile registry must carry the addon's dimension - the core "
                        + "consumers key on it");
        helper.assertTrue(DimensionProfiles.isDungeonDimension(DIMENSION_ID),
                "the addon's dimension must count as a dungeon dimension");
        helper.succeed();
    }
}
