package net.jitl.common.event;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStats;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class DeathEvent {

    @SubscribeEvent
    public static void onEntityDrop(LivingDropsEvent event) {
        RandomSource random = RandomSource.create();
        LivingEntity entity = event.getEntity();
        if(event.getSource().getEntity() instanceof Player player) {

            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
                if (isOverworldAnimal(entity)) {
                    stats.addXP(EnumKnowledge.OVERWORLD, 2.5F, player);
                }

                if (isOverworldMob(entity)) {
                    stats.addXP(EnumKnowledge.OVERWORLD, 5F, player);
                }

                if (isNetherMob(entity)) {
                    stats.addXP(EnumKnowledge.NETHER, 5F, player);
                }

                if (isEndMob(entity)) {
                    stats.addXP(EnumKnowledge.END, 5F, player);
                }

                if(higherEndXP(entity)) {
                    stats.addLevel(EnumKnowledge.END, 100);
                }

                if(higherOverworldXP(entity)) {
                    stats.addLevel(EnumKnowledge.OVERWORLD, 10);
                }
            });

            if(entity instanceof Ghast) {
                if(random.nextInt(3) == 0)
                    entity.spawnAtLocation(JItems.GHAST_TENTACLE.get(), 1);
            }

            if(JCommonConfig.ENABLE_LOOT_POUCH_DROP.get()) {
                if(random.nextInt(JCommonConfig.COMMON_LOOT_CHANCE.get()) == 0) {
                    entity.spawnAtLocation(JItems.LOOT_POUCH.get(), 1);
                }
                if(random.nextInt(JCommonConfig.GOLD_LOOT_CHANCE.get()) == 0) {
                    entity.spawnAtLocation(JItems.GOLD_LOOT_POUCH.get(), 1);
                }
                if(random.nextInt(JCommonConfig.DIAMOND_LOOT_CHANCE.get()) == 0) {
                    entity.spawnAtLocation(JItems.DIAMOND_LOOT_POUCH.get(), 1);
                }
            }
        }
    }

    public static boolean isOverworldAnimal(LivingEntity entity) {
        return entity instanceof Sheep || entity instanceof Pig || entity instanceof Cow || entity instanceof Horse || entity instanceof Llama
                || entity instanceof Squid || entity instanceof Axolotl || entity instanceof Bat || entity instanceof Bee || entity instanceof Camel
                || entity instanceof Cat || entity instanceof Chicken || entity instanceof Cod || entity instanceof Frog || entity instanceof Donkey
                || entity instanceof Dolphin || entity instanceof Fox || entity instanceof Goat || entity instanceof Mule || entity instanceof Ocelot
                || entity instanceof Panda || entity instanceof Parrot || entity instanceof PolarBear || entity instanceof Sniffer || entity instanceof SnowGolem
                || entity instanceof Tadpole || entity instanceof TropicalFish || entity instanceof Turtle || entity instanceof Wolf;
    }

    public static boolean isOverworldMob(LivingEntity entity) {
        return entity instanceof Skeleton || entity instanceof Creeper || entity instanceof Ravager || entity instanceof Silverfish || entity instanceof Slime
                || entity instanceof SpellcasterIllager || entity instanceof Spider || entity instanceof Stray || entity instanceof Strider || entity instanceof Vex
                || entity instanceof Vindicator || entity instanceof Witch || entity instanceof Zombie || entity instanceof Allay || entity instanceof IronGolem
                || entity instanceof Phantom;
    }

    public static boolean isNetherMob(LivingEntity entity) {
        return entity instanceof WitherSkeleton || entity instanceof ZombifiedPiglin || entity instanceof MagmaCube || entity instanceof Blaze || entity instanceof Zoglin
                || entity instanceof Piglin;
    }

    public static boolean isEndMob(LivingEntity entity) {
        return entity instanceof EnderMan || entity instanceof Endermite || entity instanceof Shulker;
    }

    public static boolean higherEndXP(LivingEntity entity) {
        return entity instanceof EnderDragon;
    }

    public static boolean higherOverworldXP(LivingEntity entity) {
        return entity instanceof ElderGuardian || entity instanceof Warden;
    }
}