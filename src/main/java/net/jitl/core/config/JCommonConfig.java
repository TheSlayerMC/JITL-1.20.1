package net.jitl.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class JCommonConfig {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.BooleanValue NEED_SUMMONING_STRUCTURE;
	public static final ForgeConfigSpec.BooleanValue ENABLE_LOOT_POUCH_DROP;
	public static final ForgeConfigSpec.ConfigValue<Integer> COMMON_LOOT_CHANCE;
	public static final ForgeConfigSpec.ConfigValue<Integer> GOLD_LOOT_CHANCE;
	public static final ForgeConfigSpec.ConfigValue<Integer> DIAMOND_LOOT_CHANCE;
	public static final ForgeConfigSpec.BooleanValue ENABLE_BOOM_SPAWN;

	static {
		BUILDER.push("Common configs for JITL");

		NEED_SUMMONING_STRUCTURE = BUILDER.comment("Need structure to use Summoning Table").define("Summoning structure: ", true);

		ENABLE_LOOT_POUCH_DROP = BUILDER.comment("Can Mobs drop Loot Pouches").define("Loot Pouches: ", true);
		COMMON_LOOT_CHANCE = BUILDER.comment("Common Loot Chance").define("Common Loot Chance 1 out of: ", 100);
		GOLD_LOOT_CHANCE = BUILDER.comment("Gold Loot Chance").define("Gold Loot Chance 1 out of: ", 150);
		DIAMOND_LOOT_CHANCE = BUILDER.comment("Diamond Loot Chance").define("Diamond Loot Chance 1 out of: ", 200);

		ENABLE_BOOM_SPAWN = BUILDER.comment("Can BoomBoom spawn naturally").define("Value: ", true);

		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}