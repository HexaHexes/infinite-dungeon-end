package org.bitwisemadness.infinitedungeon.end.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Dungeon Crawl contract content ported from the dc_dp_the_end blueprint
 * (Curse project 1189484, file 6137048; issue #4, step 3): the End dungeon
 * type (five layers of inherit chains against DC's built-in rooms/nodes
 * keys, with the multipart corridor room additions), the primary theming
 * mapping and theme (the blueprint's only variant, the_end_no_pillars_0 -
 * the _0 suffix is the theming contract: theme files must end _0.._3 or
 * lower levels inherit catacombs/hell, and _no_pillars_ disables pillars),
 * the depth-staged chest loot and treasure tables, and the biome mappings -
 * the blueprint's real-End dispatches plus this addon's own biome, which is
 * the biome-gate swap: our dimension's single biome dispatches to the End
 * dungeon type.
 *
 * <p>The DC monster staging (per-stage common/rare entity pools, endermite
 * at stage 1 growing to pillagers/witches/evokers and a ravager rare at
 * stage 5) translates into the org's ProfilePools schema: the common union
 * is the fodder pool, the rare union the elite pool - depth staging in the
 * org model comes from the difficulty curve, not per-depth pools - and the
 * dimension profile references both. No boss entries exist in the blueprint,
 * so the profile's boss pool stays unset and the consumer's default applies.</p>
 *
 * <p>The files are the blueprint's JSON verbatim (the biome_mappings with the
 * addon's dispatch added), embedded as text blocks and parsed through Gson at
 * datagen time - parse failure fails the run, so a corrupt port can never
 * ship. Every file is additive DC contract data (replace: false everywhere
 * the field exists). The treasure tables are JSON arrays - hence the
 * JsonElement return, not JsonObject.</p>
 */
public final class DcContractContent {

    private DcContractContent() {
    }

    private static final String F0 = """
                {
                  "settings": {
                    "max_layers": 5
                  },
                  "entrances": {
                    "inherit": [
                      "entrances:default"
                    ]
                  },
                  "layers": [
                    {
                      "type": "default",
                      "models": {
                        "rooms": {
                          "inherit": [
                            "rooms:top"
                          ]
                        },
                        "full_nodes": {
                          "inherit": [
                            "nodes:full:top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "fork_nodes": {
                          "inherit": [
                            "nodes:fork:top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "straight_nodes": {
                          "inherit": [
                            "nodes:straight:top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "turn_nodes": {
                          "inherit": [
                            "nodes:turn:top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "dead_end_nodes": {
                          "inherit": [
                            "nodes:dead_end:top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "corridors": {
                          "inherit": [
                            "corridors:top"
                          ]
                        },
                        "corridor_linkers": {
                          "inherit": [
                            "corridor_linkers:top"
                          ]
                        },
                        "node_connectors": {
                          "inherit": [
                            "node_connectors:top"
                          ]
                        }
                      },
                      "settings": {
                        "min_rooms": 3,
                        "max_rooms": 5,
                        "min_nodes": 4,
                        "max_nodes": 6,
                        "min_room_depth": 1,
                        "max_room_depth": 5,
                        "min_node_depth": 1,
                        "max_node_depth": 5,
                        "min_stairs_depth": 2,
                        "max_depth": 7,
                        "min_distance": 2,
                        "max_distance": 4
                      },
                      "multipart": {
                        "dungeoncrawl:default/corridor/corridor": {
                          "action": "add",
                          "additions": {
                            "left": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            }
                          }
                        },
                        "dungeoncrawl:default/corridor/corridor_3": {
                          "action": "add",
                          "additions": {
                            "left_1": {
                              "models": [
                                {
                                  "offset": {
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "left_2": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "right_1": {
                              "models": [
                                {
                                  "offset": {
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "right_2": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            }
                          }
                        },
                        "dungeoncrawl:default/corridor/corridor_light": {
                          "action": "add",
                          "additions": {
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            }
                          }
                        }
                      }
                    },
                    {
                      "type": "default",
                      "models": {
                        "rooms": {
                          "inherit": [
                            "rooms:lower_top"
                          ]
                        },
                        "full_nodes": {
                          "inherit": [
                            "nodes:full:lower_top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "fork_nodes": {
                          "inherit": [
                            "nodes:fork:lower_top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "straight_nodes": {
                          "inherit": [
                            "nodes:straight:lower_top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "turn_nodes": {
                          "inherit": [
                            "nodes:turn:lower_top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "dead_end_nodes": {
                          "inherit": [
                            "nodes:dead_end:lower_top"
                          ],
                          "models": [
                            {
                              "key": "dungeoncrawl:default/node/room_2",
                              "weight": 1
                            }
                          ]
                        },
                        "corridors": {
                          "inherit": [
                            "corridors:lower_top"
                          ]
                        },
                        "corridor_linkers": {
                          "inherit": [
                            "corridor_linkers:lower_top"
                          ]
                        },
                        "node_connectors": {
                          "inherit": [
                            "node_connectors:lower_top"
                          ]
                        }
                      },
                      "settings": {
                        "min_rooms": 4,
                        "max_rooms": 6,
                        "min_nodes": 5,
                        "max_nodes": 7,
                        "min_room_depth": 1,
                        "max_room_depth": 8,
                        "min_node_depth": 1,
                        "max_node_depth": 8,
                        "min_stairs_depth": 2,
                        "max_depth": 7,
                        "min_distance": 2,
                        "max_distance": 4
                      },
                      "multipart": {
                        "dungeoncrawl:default/corridor/corridor": {
                          "action": "add",
                          "additions": {
                            "left": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 3,
                                    "z": -2
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 3,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            }
                          }
                        },
                        "dungeoncrawl:default/corridor/corridor_3": {
                          "action": "add",
                          "additions": {
                            "left_1": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 1,
                                    "z": -3
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "left_2": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 5,
                                    "z": -3
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": -5
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "right_1": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 1,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            },
                            "right_2": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 5,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 4,
                                    "z": 4
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            }
                          }
                        },
                        "dungeoncrawl:default/corridor/corridor_light": {
                          "action": "add",
                          "additions": {
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 3,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/fire",
                                  "weight": 10
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/empty_room_2"
                                },
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/chest_room",
                                  "weight": 2
                                }
                              ]
                            }
                          }
                        }
                      }
                    },
                    {
                      "type": "default",
                      "models": {
                        "rooms": {
                          "inherit": [
                            "rooms:catacombs"
                          ]
                        },
                        "full_nodes": {
                          "inherit": [
                            "nodes:full:catacombs"
                          ]
                        },
                        "fork_nodes": {
                          "inherit": [
                            "nodes:fork:catacombs"
                          ]
                        },
                        "straight_nodes": {
                          "inherit": [
                            "nodes:straight:catacombs"
                          ]
                        },
                        "turn_nodes": {
                          "inherit": [
                            "nodes:turn:catacombs"
                          ]
                        },
                        "dead_end_nodes": {
                          "inherit": [
                            "nodes:dead_end:catacombs"
                          ]
                        },
                        "corridors": {
                          "inherit": [
                            "corridors:catacombs"
                          ]
                        },
                        "corridor_linkers": {
                          "inherit": [
                            "corridor_linkers:catacombs"
                          ]
                        },
                        "node_connectors": {
                          "inherit": [
                            "node_connectors:catacombs"
                          ]
                        }
                      },
                      "settings": {
                        "min_rooms": 4,
                        "max_rooms": 6,
                        "min_nodes": 8,
                        "max_nodes": 12,
                        "min_room_depth": 1,
                        "max_room_depth": 8,
                        "min_node_depth": 1,
                        "max_node_depth": 8,
                        "min_stairs_depth": 2,
                        "max_depth": 8,
                        "min_distance": 1,
                        "max_distance": 3
                      },
                      "multipart": {
                        "dungeoncrawl:default/corridor/corridor": {
                          "action": "add",
                          "additions": {
                            "left": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/spawner_room",
                                  "weight": 12
                                }
                              ]
                            },
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/spawner_room",
                                  "weight": 12
                                }
                              ]
                            }
                          }
                        },
                        "dungeoncrawl:default/corridor/corridor_light": {
                          "action": "add",
                          "additions": {
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/spawner_room",
                                  "weight": 12
                                }
                              ]
                            }
                          }
                        }
                      }
                    },
                    {
                      "type": "default",
                      "models": {
                        "rooms": {
                          "inherit": [
                            "rooms:lower_catacombs"
                          ]
                        },
                        "full_nodes": {
                          "inherit": [
                            "nodes:full:lower_catacombs"
                          ]
                        },
                        "fork_nodes": {
                          "inherit": [
                            "nodes:fork:lower_catacombs"
                          ]
                        },
                        "straight_nodes": {
                          "inherit": [
                            "nodes:straight:lower_catacombs"
                          ]
                        },
                        "turn_nodes": {
                          "inherit": [
                            "nodes:turn:lower_catacombs"
                          ]
                        },
                        "dead_end_nodes": {
                          "inherit": [
                            "nodes:dead_end:lower_catacombs"
                          ]
                        },
                        "corridors": {
                          "inherit": [
                            "corridors:lower_catacombs"
                          ]
                        },
                        "corridor_linkers": {
                          "inherit": [
                            "corridor_linkers:lower_catacombs"
                          ]
                        },
                        "node_connectors": {
                          "inherit": [
                            "node_connectors:lower_catacombs"
                          ]
                        }
                      },
                      "settings": {
                        "min_rooms": 4,
                        "max_rooms": 6,
                        "min_nodes": 8,
                        "max_nodes": 12,
                        "min_room_depth": 1,
                        "max_room_depth": 8,
                        "min_node_depth": 1,
                        "max_node_depth": 8,
                        "min_stairs_depth": 2,
                        "max_depth": 8,
                        "min_distance": 1,
                        "max_distance": 3
                      },
                      "multipart": {
                        "dungeoncrawl:default/corridor/corridor": {
                          "action": "add",
                          "additions": {
                            "left": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": -4
                                  },
                                  "model": "dungeoncrawl:default/multipart/corridor/spawner_room",
                                  "weight": 12
                                }
                              ]
                            },
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/spawner_room",
                                  "weight": 12
                                }
                              ]
                            }
                          }
                        },
                        "dungeoncrawl:default/corridor/corridor_light": {
                          "action": "add",
                          "additions": {
                            "right": {
                              "models": [
                                {
                                  "offset": {
                                    "x": 2,
                                    "z": 5
                                  },
                                  "rotation": "clockwise_180",
                                  "model": "dungeoncrawl:default/multipart/corridor/spawner_room",
                                  "weight": 12
                                }
                              ]
                            }
                          }
                        }
                      }
                    },
                    {
                      "type": "default",
                      "models": {
                        "rooms": {
                          "inherit": [
                            "rooms:hell"
                          ]
                        },
                        "full_nodes": {
                          "inherit": [
                            "nodes:full:hell"
                          ]
                        },
                        "fork_nodes": {
                          "inherit": [
                            "nodes:fork:hell"
                          ]
                        },
                        "straight_nodes": {
                          "inherit": [
                            "nodes:straight:hell"
                          ]
                        },
                        "turn_nodes": {
                          "inherit": [
                            "nodes:turn:hell"
                          ]
                        },
                        "dead_end_nodes": {
                          "inherit": [
                            "nodes:dead_end:hell"
                          ]
                        },
                        "corridors": {
                          "inherit": [
                            "corridors:hell"
                          ]
                        },
                        "corridor_linkers": {
                          "inherit": [
                            "corridor_linkers:hell"
                          ]
                        },
                        "node_connectors": {
                          "inherit": [
                            "node_connectors:hell"
                          ]
                        }
                      },
                      "settings": {
                        "min_rooms": 4,
                        "max_rooms": 6,
                        "min_nodes": 8,
                        "max_nodes": 12,
                        "min_room_depth": 1,
                        "max_room_depth": 8,
                        "min_node_depth": 1,
                        "max_node_depth": 8,
                        "min_stairs_depth": 2,
                        "max_depth": 8,
                        "min_distance": 2,
                        "max_distance": 3
                      }
                    }
                  ]
                }
                """;

    private static final String F1 = """
                {
                  "default": [
                    {
                      "key": "dungeoncrawl:the_end_no_pillars_0",
                      "weight": 1
                    }
                  ],
                  "mapping": {
                    "minecraft:end_highlands": [
                      {
                        "key": "dungeoncrawl:the_end_no_pillars_0",
                        "weight": 1
                      }
                    ],
                    "minecraft:end_midlands": [
                      {
                        "key": "dungeoncrawl:the_end_no_pillars_0",
                        "weight": 1
                      }
                    ]
                  }
                }
                """;

    private static final String F2 = """
                {
                  "theme": {
                    "generic": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone"
                        },
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 10
                        },
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 10
                        },
                        {
                          "block": "minecraft:end_stone_bricks"
                        }
                      ]
                    },
                    "solid": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 10
                        },
                        {
                          "block": "minecraft:end_stone_bricks"
                        },
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 8
                        }
                      ]
                    },
                    "pillar": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 10
                        },
                        {
                          "block": "minecraft:end_stone_bricks"
                        },
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 8
                        }
                      ]
                    },
                    "floor": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 3
                        },
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 2
                        }
                      ]
                    },
                    "stairs": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_stairs",
                          "weight": 4
                        },
                        {
                          "block": "minecraft:end_stone_brick_stairs",
                          "weight": 4
                        }
                      ]
                    },
                    "solid_stairs": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_brick_stairs",
                          "weight": 3
                        },
                        {
                          "block": "minecraft:end_stone_stairs"
                        }
                      ]
                    },
                    "material": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_bricks"
                        },
                        {
                          "block": "minecraft:end_stone_bricks",
                          "weight": 3
                        }
                      ]
                    },
                    "slab": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_slab"
                        },
                        {
                          "block": "minecraft:end_stone_brick_slab",
                          "weight": 3
                        }
                      ]
                    },
                    "solid_slab": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_slab"
                        },
                        {
                          "block": "minecraft:end_stone_brick_slab",
                          "weight": 3
                        }
                      ]
                    },
                    "wall": {
                      "type": "random_block",
                      "blocks": [
                        {
                          "block": "minecraft:end_stone_wall"
                        },
                        {
                          "block": "minecraft:end_stone_brick_wall",
                          "weight": 3
                        }
                      ]
                    },
                    "fencing": {
                      "type": "block",
                      "block": "minecraft:iron_bars"
                    },
                    "fluid": {
                      "type": "block",
                      "block": "minecraft:water"
                    }
                  }
                }
                """;

    private static final String F3 = """
                {
                  "pools": [
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/simple_dungeon"
                        },
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/ruined_portal"
                        },
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/stronghold_corridor"
                        }
                      ]
                    }
                  ]
                }
                """;

    private static final String F4 = """
                []
                """;

    private static final String F5 = """
                {
                  "pools": [
                    {
                      "rolls": 2,
                      "entries": [
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/ancient_city"
                        },
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/burried_treasure"
                        }
                      ]
                    }
                  ]
                }
                """;

    private static final String F6 = """
                []
                """;

    private static final String F7 = """
                {
                  "pools": [
                    {
                      "rolls": 1,
                      "entries": [
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/end_city_treasure"
                        }
                      ]
                    }
                  ]
                }
                """;

    private static final String F8 = """
                []
                """;

    private static final String F9 = """
                {
                  "pools": [
                    {
                      "rolls": 2,
                      "entries": [
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/end_city_treasure"
                        },
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/bastion_treasure"
                        }
                      ]
                    }
                  ]
                }
                """;

    private static final String F10 = """
                []
                """;

    private static final String F11 = """
                {
                  "pools": [
                    {
                      "rolls": 3,
                      "entries": [
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/end_city_treasure"
                        },
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/bastion_treasure"
                        },
                        {
                          "type": "loot_table",
                          "name": "minecraft:chests/ancient_city_treasure"
                        }
                      ]
                    }
                  ]
                }
                """;

    private static final String F12 = """
                []
                """;

    private static final String BIOME_MAPPINGS = """
                {
                  "replace": false,
                  "default": [
                    {
                      "key": "dungeoncrawl:default"
                    }
                  ],
                  "mapping": {
                    "minecraft:end_highlands": [
                      {
                        "key": "dungeoncrawl:the_end/default"
                      }
                    ],
                    "minecraft:end_midlands": [
                      {
                        "key": "dungeoncrawl:the_end/default"
                      }
                    ],
                    "infinite_dungeon_end:end": [
                      {
                        "key": "dungeoncrawl:the_end/default"
                      }
                    ]
                  }
                }
                """;

    private static final String POOLS_FODDER = """
                {
                  "entries": [
                    {
                      "entity": "minecraft:endermite"
                    },
                    {
                      "entity": "minecraft:enderman"
                    },
                    {
                      "entity": "minecraft:shulker"
                    },
                    {
                      "entity": "minecraft:pillager"
                    },
                    {
                      "entity": "minecraft:witch"
                    },
                    {
                      "entity": "minecraft:illusioner"
                    },
                    {
                      "entity": "minecraft:vindicator"
                    },
                    {
                      "entity": "minecraft:evoker"
                    }
                  ]
                }
                """;

    private static final String POOLS_ELITE = """
                {
                  "entries": [
                    {
                      "entity": "minecraft:endermite"
                    },
                    {
                      "entity": "minecraft:enderman"
                    },
                    {
                      "entity": "minecraft:shulker"
                    },
                    {
                      "entity": "minecraft:evoker"
                    },
                    {
                      "entity": "minecraft:vindicator"
                    },
                    {
                      "entity": "minecraft:ravager"
                    }
                  ]
                }
                """;

    /** Every DC-contract file and org pool, keyed by its path inside the jar. */
    public static Map<String, JsonElement> files() {
        Map<String, JsonElement> files = new LinkedHashMap<>();
        files.put("data/dungeoncrawl/dungeon/types/the_end/default.json", parse(F0));
        files.put("data/dungeoncrawl/theming/mappings/primary/the_end.json", parse(F1));
        files.put("data/dungeoncrawl/theming/primary_themes/the_end_no_pillars_0.json", parse(F2));
        files.put("data/dungeoncrawl/loot_tables/chests/the_end/stage_1.json", parse(F3));
        files.put("data/dungeoncrawl/treasure/the_end/stage_1.json", parse(F4));
        files.put("data/dungeoncrawl/loot_tables/chests/the_end/stage_2.json", parse(F5));
        files.put("data/dungeoncrawl/treasure/the_end/stage_2.json", parse(F6));
        files.put("data/dungeoncrawl/loot_tables/chests/the_end/stage_3.json", parse(F7));
        files.put("data/dungeoncrawl/treasure/the_end/stage_3.json", parse(F8));
        files.put("data/dungeoncrawl/loot_tables/chests/the_end/stage_4.json", parse(F9));
        files.put("data/dungeoncrawl/treasure/the_end/stage_4.json", parse(F10));
        files.put("data/dungeoncrawl/loot_tables/chests/the_end/stage_5.json", parse(F11));
        files.put("data/dungeoncrawl/treasure/the_end/stage_5.json", parse(F12));
        files.put("data/dungeoncrawl/dungeon/biome_mappings/default.json", parse(BIOME_MAPPINGS));
        files.put("data/infinite_dungeon_end/infinite_dungeon/pools/fodder.json", parse(POOLS_FODDER));
        files.put("data/infinite_dungeon_end/infinite_dungeon/pools/elite.json", parse(POOLS_ELITE));
        return files;
    }

    /** Parses one embedded document; a parse failure fails datagen loudly. */
    private static JsonElement parse(String json) {
        return JsonParser.parseString(json);
    }
}
