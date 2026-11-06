# Infinite Dungeon: End

A new dungeon dimension for [Infinite Dungeon](https://github.com/HexaHexes/infinite-dungeon),
with its own dungeon portal.

Its theme comes from the *Dungeon Crawl - Dimensional Patch* `dc_dp_the_end`
datapack (Curse project 1189484, file 6137048), which serves as the content
blueprint.

Scaffolded from [neoforge-mod-template](https://github.com/HexaHexes/neoforge-mod-template).
Implementation is gated on the profile-driven core of the main repo: issues
#162 and #163. The plan lives in `docs/DEVELOPMENT_PLAN.md` there (sections 3,
16, 19, 20).

Sibling addons: infinite-dungeon-nether, -better-nether, -end, -better-end,
-outer-end, -aether, -blue-skies, -undergarden.

Status: dependencies wired; the dimension, profile and content port follow
(issues #4, charter #1).

## Design note

The addon is a companion mod in the suite's module posture (plan section 19,
the I32 platform posture in the API's integration record): it depends on the
infinite-dungeon-api platform, never on the Infinite Dungeon core mod, and
degrades to a no-op by absence - without this mod the dimension simply does
not exist and the core suite is untouched.

- **Dimension and portal (plan section 3, applied to a second dimension):**
  the addon ships one dungeon dimension with its own portal rules via an
  `infinite_dungeon` dimension profile - layer/floor limits, curve overrides,
  spawn pools, portal policy and announcement keys. The core's portal paths
  resolve every rule per dimension (profile first, config default behind).
- **Deterministic placement (plan section 16):** the mirror structure set
  carries its own fixed salt, so pregenerated and organic worlds place the
  dimension's dungeon identically from the first tick - the Chunky/Distant
  Horizons compatibility contract.
- **The content blueprint:** the dc_dp_the_end datapack's structures, pools
  and theming port under the org's porting rules (theme naming, monster
  staging into ProfilePools, the biome gate swap). Dungeon Crawl's natural
  generation stays disabled on this dimension (the core ruling, plan section
  18): the addon's structure set is the sole placement authority.
- **Economy surface (plan section 20):** staged chest loot and treasure feed
  the guild's reward pipeline like the core dimension's do.

## AI disclosure

Parts of this project were written with the assistance of AI coding tools.
AI-assisted code, data and documentation ship only after human review by the
maintainers.
