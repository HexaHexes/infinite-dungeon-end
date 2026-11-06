package org.bitwisemadness.infinitedungeon.end.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The dimension scaffold (issue #4, step 2; charter #1): the End dungeon
 * dimension, its type, its biome, the mirror structure set, the Dungeon
 * Crawl biome-gate tag entry and the infinite-dungeon dimension profile.
 *
 * <p>Mirrors the core dimension's shape (flat air generator, End-style
 * dimension type, empty-spawner biome) with this addon's own ids and its own
 * fixed placement salt - the section 16 determinism contract: a fixed salt,
 * never derived, so pregenerated and organic worlds place the dimension's
 * dungeon identically from the first tick. The spawn pools and curve
 * overrides join the profile with the content port (step 3); the portal
 * wiring rides the profile-driven core (issue #163, merged).</p>
 */
public final class DimensionContent {

    /** The addon's dimension id, also the profile and gate-tag key. */
    public static final String DIMENSION_ID = "infinite_dungeon_end:end";

    /** The biome-gate tag entry DC's dungeon structure consults. */
    public static final String GATE_TAG_PATH =
            "data/dungeoncrawl/tags/worldgen/biome/has_structure/dungeon.json";

    /** The core dimension's lanes salt - the mirror set must never reuse it. */
    private static final int CORE_LANES_SALT = 10387313;

    /** The mirror set's own fixed salt (section 16: fixed, never derived). */
    private static final int SALT = 10488963;

    private static final int SPACING = 32;
    private static final int SEPARATION = 12;
    private static final int HEIGHT = 2048;
    private static final int MIN_Y = -64;

    private DimensionContent() {
    }

    /** Every datapack JSON, keyed by its path inside the jar. */
    public static Map<String, JsonObject> files() {
        Map<String, JsonObject> files = new LinkedHashMap<>();
        files.put("data/infinite_dungeon_end/dimension/end.json", dimension());
        files.put("data/infinite_dungeon_end/dimension_type/end.json", dimensionType());
        files.put("data/infinite_dungeon_end/worldgen/biome/end.json", biome());
        files.put("data/infinite_dungeon_end/worldgen/structure_set/end.json", structureSet());
        files.put(GATE_TAG_PATH, gateTag());
        files.put("data/infinite_dungeon_end/infinite_dungeon/dimension_profile/end.json",
                profile());
        validate(files);
        return files;
    }

    private static JsonObject dimension() {
        JsonObject settings = new JsonObject();
        settings.addProperty("biome", "infinite_dungeon_end:end");
        settings.addProperty("features", true);
        settings.addProperty("lakes", false);
        JsonArray layers = new JsonArray();
        JsonObject layer = new JsonObject();
        layer.addProperty("block", "minecraft:air");
        layer.addProperty("height", 1);
        layers.add(layer);
        settings.add("layers", layers);
        settings.addProperty("structure_overrides", "infinite_dungeon_end:end");
        JsonObject generator = new JsonObject();
        generator.addProperty("type", "minecraft:flat");
        generator.add("settings", settings);
        JsonObject json = new JsonObject();
        json.addProperty("type", "infinite_dungeon_end:end");
        json.add("generator", generator);
        return json;
    }

    private static JsonObject dimensionType() {
        JsonObject json = new JsonObject();
        json.addProperty("ambient_light", 0.0);
        json.addProperty("bed_works", true);
        json.addProperty("coordinate_scale", 1.0);
        json.addProperty("effects", "minecraft:the_end");
        json.addProperty("has_ceiling", false);
        json.addProperty("has_raids", false);
        json.addProperty("has_skylight", true);
        json.addProperty("height", HEIGHT);
        json.addProperty("infiniburn", "#minecraft:infiniburn_end");
        json.addProperty("logical_height", HEIGHT);
        json.addProperty("min_y", MIN_Y);
        json.addProperty("monster_spawn_block_light_limit", 0);
        JsonObject monsterLight = new JsonObject();
        monsterLight.addProperty("type", "minecraft:uniform");
        monsterLight.addProperty("max_inclusive", 7);
        monsterLight.addProperty("min_inclusive", 0);
        json.add("monster_spawn_light_level", monsterLight);
        json.addProperty("natural", false);
        json.addProperty("piglin_safe", false);
        json.addProperty("respawn_anchor_works", false);
        json.addProperty("ultrawarm", false);
        return json;
    }

    private static JsonObject biome() {
        JsonObject effects = new JsonObject();
        effects.addProperty("fog_color", 10518688);
        JsonObject mood = new JsonObject();
        mood.addProperty("block_search_extent", 8);
        mood.addProperty("offset", 2.0);
        mood.addProperty("sound", "minecraft:ambient.cave");
        mood.addProperty("tick_delay", 6000);
        effects.add("mood_sound", mood);
        effects.addProperty("sky_color", 0);
        effects.addProperty("water_color", 4159204);
        effects.addProperty("water_fog_color", 329011);
        JsonObject json = new JsonObject();
        json.add("carvers", new JsonObject());
        json.addProperty("downfall", 0.5);
        json.add("effects", effects);
        // Features join with the content port (step 3); spawners stay empty -
        // the profile's spawn pools are the only spawn source.
        json.add("features", emptyFeatureArray());
        json.addProperty("has_precipitation", false);
        json.add("spawn_costs", new JsonObject());
        json.add("spawners", emptySpawners());
        json.addProperty("temperature", 0.5);
        return json;
    }

    private static JsonArray emptyFeatureArray() {
        JsonArray features = new JsonArray();
        for (int i = 0; i < 11; i++) {
            features.add(new JsonArray());
        }
        return features;
    }

    private static JsonObject emptySpawners() {
        JsonObject spawners = new JsonObject();
        for (String group : List.of("ambient", "axolotls", "creature", "misc", "monster",
                "underground_water_creature", "water_ambient", "water_creature")) {
            spawners.add(group, new JsonArray());
        }
        return spawners;
    }

    /** The mirror structure set: the core lanes set's shape, its own salt. */
    private static JsonObject structureSet() {
        JsonObject placement = new JsonObject();
        placement.addProperty("type", "minecraft:random_spread");
        placement.addProperty("salt", SALT);
        placement.addProperty("separation", SEPARATION);
        placement.addProperty("spacing", SPACING);
        JsonObject element = new JsonObject();
        element.addProperty("structure", "dungeoncrawl:dungeon");
        element.addProperty("weight", 1);
        JsonArray structures = new JsonArray();
        structures.add(element);
        JsonObject json = new JsonObject();
        json.add("placement", placement);
        json.add("structures", structures);
        return json;
    }

    /** The additive biome-gate entry: this dimension's biome joins DC's gate. */
    private static JsonObject gateTag() {
        JsonObject json = new JsonObject();
        json.addProperty("replace", false);
        JsonArray values = new JsonArray();
        values.add(DIMENSION_ID.split(":", 2)[1].isEmpty() ? DIMENSION_ID
                : "infinite_dungeon_end:end");
        json.add("values", values);
        return json;
    }

    /** The minimal dimension profile: membership now, pools with step 3. */
    private static JsonObject profile() {
        JsonObject json = new JsonObject();
        json.addProperty("dimension", DIMENSION_ID);
        return json;
    }

    private static void validate(Map<String, JsonObject> files) {
        List<String> errors = new java.util.ArrayList<>();
        if (files.size() != 6) {
            errors.add("file count: " + files.size() + " expected 6");
        }
        int salt = files.get("data/infinite_dungeon_end/worldgen/structure_set/end.json")
                .getAsJsonObject("placement").get("salt").getAsInt();
        if (salt == CORE_LANES_SALT) {
            errors.add("the mirror set must not reuse the core lanes salt");
        }
        if (salt <= 0) {
            errors.add("the mirror set salt must be a fixed positive integer");
        }
        if (!files.get("data/infinite_dungeon_end/dimension/end.json")
                .getAsJsonObject("generator").getAsJsonObject("settings")
                .get("structure_overrides").getAsString().equals("infinite_dungeon_end:end")) {
            errors.add("the dimension's structure_overrides must point at the mirror set");
        }
        JsonObject tag = files.get(GATE_TAG_PATH);
        if (tag.get("replace").getAsBoolean()) {
            errors.add("the biome-gate tag entry must stay additive (replace false)");
        }
        if (!files.get("data/infinite_dungeon_end/infinite_dungeon/dimension_profile/end.json")
                .get("dimension").getAsString().equals(DIMENSION_ID)) {
            errors.add("the profile must declare this addon's dimension");
        }
        if (!errors.isEmpty()) {
            throw new IllegalStateException("End dimension scaffold drift: " + errors);
        }
    }
}
