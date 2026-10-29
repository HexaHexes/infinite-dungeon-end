# Repo rules - infinite-dungeon-end

Repo-specific rules and notes. The org-wide `AGENTS.md` beside this file is
maintained in [
eoforge-mod-template](https://github.com/HexaHexes/neoforge-mod-template)
and pulled in verbatim - **never hand-edit it.** Everything in this file is this
repo's own and is edited freely; keep it aligned with the org rules rather than
restating them.

**Repo:** `HexaHexes/infinite-dungeon-end` - **Mod ID:** `infinite_dungeon_end` - **Branch:** `main`
**Platform:** Minecraft 1.21.1 / NeoForge 21.1.259 / JDK 21 - **Jar:** `build/libs/infinite_dungeon_end-<version>.jar`

Adds the End dimension - one of the seven sibling dimension addons ported from the
Dungeon Crawl - Dimensional Patch datapacks (project 1189484): this addon ships one
dimension + dimension_type JSON, one `infinite_dungeon` dimension profile (curves,
pools, PortalPolicy, announcements), the Dungeon Crawl gate tag and mirror structure set
with its own salt, and the ported content pack (types, biome mappings, staged loot and
treasure, theming).

**Dependencies**
- Required today: Minecraft, NeoForge (scaffold state).
- Planned for first implementation: `infinite-dungeon-api` (dimension profiles),
  Dungeon Crawl (content contract, CF 324973 file 7887320 / Modrinth q0rRg4lH),
  and the profile-driven core of HexaHexes/infinite-dungeon (issues #162, #163).

**Test:** `./gradlew runGameTestServer` - `InfiniteDungeonEndGameTests` (1 test).
`expectedTests = 1` in `build.gradle` must match the `@GameTest` count.

**Gotchas**
- The template's mixin config ships empty (no mixins planned); remove it in the first
  real PR if it stays unused.
- Porting rules from the blueprint: theme files must end `_0.json`..`_3.json`
  (`_no_pillars_` in the name disables pillars); the datapack's per-dimension monster
  staging is superseded by profile `ProfilePools`; the biome gate entry must name THIS
  dimension's own biome, not the source pack's.
- No dimension exists until the core gates (#162, #163) land; until then this
  addon compiles and does nothing.
- Release pipeline: the repository variable `ALPHA_RELEASES` is set to `false`
  for this addon until its first real release - release.yml otherwise publishes
  an -alpha on the first qualifying push, and its notes are still the template
  example. Before the first real release: edit the notes block in
  `.github/workflows/release.yml`, then flip the variable.
