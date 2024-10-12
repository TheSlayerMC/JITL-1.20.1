package net.jitl.core.helper.compat;

import jeresources.api.*;
import jeresources.compatibility.api.JERAPI;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JEntities;

public class JerCompat {

    public static void init() {
        IJERAPI jer = JERAPI.getInstance();

        IDungeonRegistry dungeon = jer.getDungeonRegistry();

        IWorldGenRegistry gen = jer.getWorldGenRegistry();

        IMobRegistry mob = jer.getMobRegistry();

        if(mob != null) {
            mob.register(JEntities.BROWN_HONGO_TYPE.get(), JITL.rl("brown_hongo"));
//            addMob(EnumKnowledge.OVERWORLD, "big_hongo", "Forest like", true, MobStats.BIG_HONGO_HEALTH, MobStats.BIG_HONGO_DAMAGE, "Hongoshrooms");
//            addMob(EnumKnowledge.OVERWORLD, "medium_hongo", "Forest like", true, MobStats.MEDIUM_HONGO_HEALTH, MobStats.MEDIUM_HONGO_DAMAGE, "Hongoshrooms");
//            addMob(EnumKnowledge.OVERWORLD, "small_hongo", "Forest like", true, MobStats.SMALL_HONGO_HEALTH, MobStats.SMALL_HONGO_DAMAGE, "Hongoshrooms");
//            addMob(EnumKnowledge.OVERWORLD, "blizzard", "Snow like", FIREBALLS,false, MobStats.BLIZZARD_HEALTH, 0,"Snowballs");
//            addMob(EnumKnowledge.OVERWORLD, "boom_boom", "Overworld", "Will create a medium explosion", false, MobStats.BOOM_HEALTH, 0, "Gunpowder and TNT blocks");
//            addMob(EnumKnowledge.OVERWORLD, "caveling", "Overworld Underground", false, MobStats.CAVELING_HEALTH, MobStats.CAVELING_DAMAGE, "Stone Clump, Cave Dust and Cave Crystal");
//            addMob(EnumKnowledge.OVERWORLD, "cavurn", "Overworld Underground", false, MobStats.CAVURN_HEALTH, MobStats.CAVELING_DAMAGE, "Cave Dust and Cave Crystal");
//            addMob(EnumKnowledge.OVERWORLD, "stonewalker", "Overworld Underground", false, MobStats.STONEWALKER_HEALTH, MobStats.STONEWALKER_DAMAGE, "Cave Dust, Stone Clump, Shadium Ingot, Lunium Ingot, Sapphire and Cave Crystal");
//            addMob(EnumKnowledge.OVERWORLD, "floro", "Forest like", "Once not hiding away it will shoot projectiles", false, MobStats.FLORO_HEALTH, 0, "Floro Pedal and Floro Seeds");
//            addMob(EnumKnowledge.OVERWORLD, "illager_mech", "Illager Bunker", true, 100, 8, "");
//            addMob(EnumKnowledge.OVERWORLD, "jungle_turtle", "Jungle like", true, MobStats.JUNGLE_TURTLE_HEALTH, MobStats.JUNGLE_TURTLE_DAMAGE, "");
//            addMob(EnumKnowledge.OVERWORLD, "sand_crawler", "Desert like", false, MobStats.SAND_CRAWLER_HEALTH, MobStats.SAND_CRAWLER_DAMAGE, "Sand Blocks");
//            addMob(EnumKnowledge.OVERWORLD, "spyclopse", "Desert like", false, MobStats.SPYCLOPS_HEALTH, MobStats.SPYCLOPS_DAMAGE, "Spyclopse Eye");
//            addMob(EnumKnowledge.OVERWORLD, "rockite_golem", "Overworld Underground", "NPC Mob, Will trade with you", MobStats.NPC_HEALTH, "");
//            addMob(EnumKnowledge.OVERWORLD, "neutral_sentry_stalker", "$(l:jitl:overworld/structures#ancient_structure)Ancient Structure$(/l)", "NPC Mob, Will trade with you once you reach 100 Overworld Knowledge", true, MobStats.NPC_HEALTH, 0, "");
//            addMob(EnumKnowledge.OVERWORLD, "mage", "$(l:jitl:overworld/structures#mage_hut)Mage Hut$(/l)", "NPC Mob, Will trade with you", true, MobStats.NPC_HEALTH, 0, "");
//            addMob(EnumKnowledge.OVERWORLD, "robot", "Any Overworld Biome", "Will attack on site", MobStats.ROBOT_HEALTH, "Iron Ingot and Redstone Dust");
//            addMob(EnumKnowledge.OVERWORLD, "pet_robot", true, "Obtained from the $(l:jitl:overworld/traders#mage)Mage$(/l)", "Already tamed when spawned, can be healed with Pet Food", MobStats.PET_ROBOT_HEALTH, MobStats.PET_ROBOT_DAMAGE,"");
//            addMob(EnumKnowledge.OVERWORLD, "ferret", false, "Forest like", "Pet Food", MobStats.FERRET_HEALTH, MobStats.FERRET_DAMAGE,"");
//
//            addMob(EnumKnowledge.NETHER, "hellbot", "$(l:jitl:nether/structures#hellbot)Hellbot Structure$(/l)", false, MobStats.HELLBOT_HEALTH, MobStats.HELLBOT_DAMAGE, "Flaming Sprocket, Hell Shards and Flaming Spring");
//            addMob(EnumKnowledge.NETHER, "hell_cow", "Nether", "Breedable with HellShards, Can be used to get Lava with a Bucket", MobStats.HELL_COW_HEALTH, "Blazing Fireball and Boil Powder");
//            addMob(EnumKnowledge.NETHER, "hell_serpent", "Nether", false, MobStats.LAVASNAKE_HEALTH, MobStats.LAVASNAKE_DAMAGE, "Snake Skin, Hell Shards, Snake Flesh and Blood");
//            addMob(EnumKnowledge.NETHER, "hell_turtle", "Nether", true, MobStats.HELL_TURTLE_HEALTH, MobStats.HELL_TURTLE_DAMAGE, "Hell Turtle Shell, Hell Shards and Blood");
//            addMob(EnumKnowledge.NETHER, "inferno_blaze", "Nether", FIREBALLS,false, MobStats.INFERNO_BLAZE_HEALTH, 0,"Blaze Powder, Hell Shards, Blaze Rod");
//            addMob(EnumKnowledge.NETHER, "mini_ghast", "$(l:jitl:nether/structures#nether_tower)Nether Tower$(/l)", FIREBALLS, false, MobStats.MINI_GHAST_HEALTH, 0, "Flaming Ghast Tentacle, Hell Shards and Balmy Teardrop");
//            addMob(EnumKnowledge.NETHER, "reaper", "Nether", false, MobStats.REAPER_HEALTH, MobStats.REAPER_DAMAGE, "Withic Dust, Hell Shards and Lost Soul");
//            addMob(EnumKnowledge.NETHER, "witherspine", "Nether", false, MobStats.WITHERSPINE_HEALTH, MobStats.WITHERSPINE_DAMAGE, "Withic Dust and Withicspine");
//
//            addMob(EnumKnowledge.BOIL, "burning_light", "Boiling Point", false, MobStats.BURNING_LIGHT_HEALTH, MobStats.BURNING_LIGHT_DAMAGE, "Blazing Fireball and Boil Powder");
//            addMob(EnumKnowledge.BOIL, "flame_lotus", "Boiling Point", "For aesthetics", MobStats.FLAME_LOTUS_HEALTH, "");
//            addMob(EnumKnowledge.BOIL, "frightener", "Boiling Point", false, MobStats.FRIGHTENER_HEALTH, MobStats.FRIGHTENER_DAMAGE, "Sand blocks");
//            addMob(EnumKnowledge.BOIL, "hellwing", "$(l:jitl:boil/structures#hellwing)Hellwing Tower$(/l)", "Shoots Fireballs", false, MobStats.HELLWING_HEALTH, 0, "Boiling Skull and a Iron Sword");
//            addMob(EnumKnowledge.BOIL, "magma_blaze", "Boiling Point", FIREBALLS, false, MobStats.MAGMA_BLAZE_HEALTH, 0, "Blaze Powder and Boil Powder");
//            addMob(EnumKnowledge.BOIL, "observer", "$(l:jitl:boil/structures#brison)Brison Network$(/l) and $(l:jitl:boil/structures#observer_hut)Observer Hut$(/l)", "Shoots Fireballs", false, MobStats.OBSERVER_HEALTH, 0, "Sizzling Eye and Boil Powder");
//            addMob(EnumKnowledge.BOIL, "screamer", "$(l:jitl:boil/structures#brison)Brison Network$(/l)", "Shoots Fireballs", false, MobStats.SCREAMER_HEALTH, 0, "Sizzling Eye and Boil Powder");
//
//            addMob(EnumKnowledge.FROZEN, "capybara", "Frozen", true, MobStats.CAPYBARA_HEALTH, MobStats.CAPYBARA_DAMAGE, "");
//            addMob(EnumKnowledge.FROZEN, "crystal_cluster", "Frozen", "Fly's around", MobStats.CRYSTAL_CLUSTER_HEALTH, "");
//            addMob(EnumKnowledge.FROZEN, "frozen_frostbiter", "$(l:jitl:frozen/structures#frozen_dungeon)Frozen Dungeon$(/l)", FIREBALLS, false, MobStats.FROZEN_FROSTBITER_HEALTH, 0, "Frost Flakes");
//            addMob(EnumKnowledge.FROZEN, "frozen_troll", "Frozen", "It has a hard straight hit", true, MobStats.FROZEN_TROLL_HEALTH, MobStats.FROZEN_TROLL_DAMAGE, "");
//            addMob(EnumKnowledge.FROZEN, "ice_golem", "$(l:jitl:frozen/structures#eskimo_camp)Eskimo Camp$(/l)", true, MobStats.ICE_GOLEM_HEALTH, MobStats.ICE_GOLEM_DAMAGE, "");
//            addMob(EnumKnowledge.FROZEN, "permafraust", "Frozen", false, MobStats.PERMAFRAUST_HEALTH, MobStats.PERMAFRAUST_DAMAGE, "Crystal Flake");
//            addMob(EnumKnowledge.FROZEN, "shatterer", "Frozen", true, MobStats.SHATTERER_HEALTH, 0, "");
//            addMob(EnumKnowledge.FROZEN, "shivering_bushwalker", "Frozen", true, MobStats.SHIVERING_BUSHWALKER_HEALTH, MobStats.SHIVERING_BUSHWALKER_DAMAGE, "Crystal Flakes");
//            addMob(EnumKnowledge.FROZEN, "shivering_shrieker", "Frozen", true, MobStats.SHIVERING_SHRIEKER_HEALTH, MobStats.SHIVERING_SHRIEKER_DAMAGE, "Crystal Flakes");
//            addMob(EnumKnowledge.FROZEN, "shiverwolf", "Dying Forest and Bitterwood Forest", "Frozen Ice Ball which is obtained from an $(l:jitl:frozen/traders#eskimo)Eskimo$(/l))", MobStats.TAMED_SHIVERWOLF_HEALTH, MobStats.SHIVERWOLF_HEALTH, MobStats.SHIVERWOLF_DAMAGE, "");
//
//            addMob(EnumKnowledge.EUCA, "dynaster", "Euca", true, MobStats.DYNASTER_HEALTH, MobStats.DYNASTER_DAMAGE, "Euca Meat, Royal Disk and Shimmerer Dust");
//            addMob(EnumKnowledge.EUCA, "euca_charger", "Euca", "Very quick", false , MobStats.EUCA_CHARGER_HEALTH, MobStats.EUCA_CHARGER_DAMAGE, "Euca Meat, Gate Keys and Shimmerer Dust");
//            addMob(EnumKnowledge.EUCA, "goldbot", "$(l:jitl:euca/structures#euca_sphere)Euca Sphere$(/l)", false, MobStats.GOLDBOT_HEALTH, MobStats.GOLDBOT_DAMAGE, "Gate Keys and Metal Disk");
//            addMob(EnumKnowledge.EUCA, "golder", "Euca", false, MobStats.GOLDER_HEALTH, MobStats.GOLDER_DAMAGE, "Euca Meat and Golder Dust");
//            addMob(EnumKnowledge.EUCA, "shimmerer", "Euca", "Fly's around", MobStats.SHIMMERER_HEALTH, "Gate Keys, Royal Disc and Shimmerer Dust");
//            addMob(EnumKnowledge.EUCA, "euca_hopper", "Goldite Grains", "Euca Meat", MobStats.TAMED_EUCA_HOPPER_HEALTH, MobStats.EUCA_HOPPER_HEALTH, (float)MobStats.EUCA_HOPPER_DAMAGE, "");
//
//            addMob(EnumKnowledge.DEPTHS, "darkener", "Depths", "Fly's around", MobStats.DARKENER_HEALTH, 0, "Dark Crystal");
//            addMob(EnumKnowledge.DEPTHS, "darkness_crawler", "Depths", "Beastly Stomach", MobStats.TAME_DARKNESS_CRAWLER_HEALTH, MobStats.DARKNESS_CRAWLER_HEALTH, (float)MobStats.DARKNESS_CRAWLER_DAMAGE, "Scale");
//            addMob(EnumKnowledge.DEPTHS, "dark_sorcerer", "$(l:jitl:depths/structures#dark_sorcerers_dungeon)Dark Sorcerers Dungeon$(/l)", false, MobStats.DARK_SORCERER_HEALTH, MobStats.DARK_SORCERER_DAMAGE, "Dark Orb");
//            addMob(EnumKnowledge.DEPTHS, "depths_beast", "Depths", false, MobStats.DEPTHS_BEAST_HEALTH, MobStats.DEPTHS_BEAST_DAMAGE, "Beastly Stomach");
//            addMob(EnumKnowledge.DEPTHS, "depths_hunter", "Depths", false, MobStats.DEPTHS_HUNTER_HEALTH, MobStats.DEPTHS_HUNTER_DAMAGE, "Depths Meat and Dark Crystal");
//            addMob(EnumKnowledge.DEPTHS, "roc", "Depths", "Depths Meat", MobStats.TAMED_ROC_HEALTH, MobStats.ROC_HEALTH, MobStats.ROC_DAMAGE, "Roc Feather");
//            addMob(EnumKnowledge.DEPTHS, "spiked_beast", "Depths", false, MobStats.SPIKED_BEAST_HEALTH, MobStats.SPIKED_BEAST_DAMAGE, "Beastly Stomach");
//
//            addMob(EnumKnowledge.CORBA, "corbanian_mollusk", "Corba", "A Slow slug that leaves a trail of Slime behind", MobStats.CORBANIAN_MOLLUSK_HEALTH, "Slimy Flesh and Slug Slime");
//            addMob(EnumKnowledge.CORBA, "leaf_blower", "Corba", false, MobStats.LEAF_BLOWER_HEALTH, MobStats.LEAF_BLOWER_DAMAGE, "Stick, Enchanted Leaf and Nature Tablet");
//            addMob(EnumKnowledge.CORBA, "nature_mage", "Tainted Swamp", FIREBALLS, false, MobStats.NATURE_MAGE_HEALTH, "Enchanted Leaf");
//            addMob(EnumKnowledge.CORBA, "overseer", "$(l:jitl:corba/structures#seer_tree)Overseer Tree$(/l)", FLYING_FIREBALLS, false, MobStats.OVERSEER_HEALTH, 0, "Collector Rock, Sentry Stone and Over Seeing Eye");
//            addMob(EnumKnowledge.CORBA, "overseer_elder", "$(l:jitl:corba/structures#seer_tree)Overseer Tree$(/l) up the very top", FLYING_FIREBALLS, false, MobStats.OVERSEER_ELDER_HEALTH, 0, "Collector Rock, Over Seeing Tablet, Sentry Stone and Over Seeing Eye");
//            addMob(EnumKnowledge.CORBA, "smelly", "Corba", false, MobStats.SMELLY_HEALTH, MobStats.SMELLY_DAMAGE, "");
//            addMob(EnumKnowledge.CORBA, "stinky", "Tainted Swamp", false, MobStats.STINKY_HEALTH, MobStats.STINKY_DAMAGE, "");
//            addMob(EnumKnowledge.CORBA, "surface_seer", "Tainted Swamp", FLYING_FIREBALLS, false, MobStats.SURFACE_SEER_HEALTH, 0, "Nature Tablet, Sentry Stone and Over Seeing Eye");
//            addMob(EnumKnowledge.CORBA, "tree_golem", "Tainted Forest", "When it's angry it has a hard hit", true, MobStats.TREE_GOLEM_HEALTH, MobStats.TREE_GOLEM_ATTACK, "Sticks, Enchanted Leaf, Overgrown Nature Ball and Nature Tablet");
//            addMob(EnumKnowledge.CORBA, "wood_creature", "Corba", true, MobStats.WOOD_CREATURE_HEALTH, MobStats.WOOD_CREATURE_DAMAGE, "Sticks and Enchanted Leaf");
//            addMob(EnumKnowledge.CORBA, "swamp_fly", "Tainted Swamp", "Can be caught in a Glass Bottle", MobStats.SWAMP_FLY_HEALTH, "");
//
//            addMob(EnumKnowledge.TERRANIA, "arana_king", "Terrania", false, MobStats.ARANA_KING_HEALTH, MobStats.ARANA_KING_DAMAGE, "Terrashroom");
//            addMob(EnumKnowledge.TERRANIA, "flungas", "Mushroom Forest", "Turns a Glass Bottle into a Bile Vile", MobStats.FLUNGUS_HEALTH, "Breathing Fungus");
//            addMob(EnumKnowledge.TERRANIA, "purplian", "Terrania", FIREBALLS, false, MobStats.PURPLIAN_HEALTH, 0, "Purple Powder and Terrastar");
//            addMob(EnumKnowledge.TERRANIA, "terragrow", "Terrania", true, MobStats.TERRAGROW_HEALTH, MobStats.TERRAGROW_DAMAGE, "Light Terranian Soil and Earthen Crystal");
//            addMob(EnumKnowledge.TERRANIA, "terrascatterer", "Terrania", false, MobStats.TERRA_SCATTERRER_HEALTH, MobStats.TERRA_SCATTERRER_DAMAGE, "Dark Terranian Soil and Earthen Crystal");
//            addMob(EnumKnowledge.TERRANIA, "terrashroom", "Mushroom Forest", false, MobStats.TERRASHROOM_HEALTH, MobStats.TERRASHROOM_DAMAGE, "Terrashroom");
//            addMob(EnumKnowledge.TERRANIA, "terraslug", "Terrania", false, MobStats.TERRASLUG_HEALTH, MobStats.TERRASLUG_DAMAGE, "Slug Slime");
//
//            addMob(EnumKnowledge.CLOUDIA, "aero_lotus", "Cloudia", MobStats.AERO_LOTUS_HEALTH, "Fluffy Feather");
//            addMob(EnumKnowledge.CLOUDIA, "cloud_ghost", "Cloudia", false, MobStats.CLOUD_GHOST_HEALTH, MobStats.CLOUD_GHOST_DAMAGE, "Fluffy Feather");
//            addMob(EnumKnowledge.CLOUDIA, "sky_eel", "Cloudia", "Fly's around", false, MobStats.SKY_EEL_HEALTH, 0, "Fluffy Feather");
//            addMob(EnumKnowledge.CLOUDIA, "starlight_golem", "Cloudia", true, MobStats.STARLIGHT_GOLEM_HEALTH, MobStats.STARLIGHT_GOLEM_DAMAGE, "Golem Chunk");
//            addMob(EnumKnowledge.CLOUDIA, "starlight_transporter", "Cloudia", false, MobStats.STARLIGHT_TRANSPORTER_HEALTH, MobStats.STARLIGHT_TRANSPORTER_DAMAGE, "Golem Chunk");
//            addMob(EnumKnowledge.CLOUDIA, "starlight_walker", "Cloudia", false, MobStats.STARLIGHT_WALKER_HEALTH, MobStats.STARLIGHT_WALKER_DAMAGE, "Golem Chunk");
//
//            addMob(EnumKnowledge.SENTERIAN, "sentry_lord", "Senterian labyrinth", false, MobStats.SENTRY_LORD_HEALTH, MobStats.SENTRY_LORD_DAMAGE, "Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)");
//            addMob(EnumKnowledge.SENTERIAN, "sentry_stalker", "Senterian labyrinth", false, MobStats.SENTRY_STALKER_HEALTH, MobStats.SENTRY_STALKER_DAMAGE, "Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)");
//            addMob(EnumKnowledge.SENTERIAN, "sentry_walker", "Senterian labyrinth", false, MobStats.SENTRY_WALKER_HEALTH, MobStats.SENTRY_WALKER_DAMAGE, "Sentry Observer which is used in $(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)");
//
//            addMob(EnumKnowledge.SENTERIAN, "mini_sentry_walker", "$(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)", false, MobStats.MINI_SENTRY_WALKER_HEALTH, MobStats.MINI_SENTRY_WALKER_DAMAGE, "Sentacoins");
//            addMob(EnumKnowledge.SENTERIAN, "mini_sentry_stalker", "$(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)", false, MobStats.MINI_SENTRY_STALKER_HEALTH, MobStats.MINI_SENTRY_STALKER_DAMAGE, "Sentacoins");
//            addMob(EnumKnowledge.SENTERIAN, "mini_sentry_lord", "$(l:jitl:senterian/structures#senterian_altar)Senterian Altar$(/l)", false, MobStats.MINI_SENTRY_LORD_HEALTH, MobStats.MINI_SENTRY_LORD_DAMAGE, "Sentacoins");

        }

        IPlantRegistry plant = jer.getPlantRegistry();
    }
}
