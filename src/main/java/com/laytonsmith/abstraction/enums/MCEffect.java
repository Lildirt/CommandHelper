package com.laytonsmith.abstraction.enums;

import com.laytonsmith.annotations.MEnum;

@MEnum("com.commandhelper.Effect")
public enum MCEffect {
	/**
	 * VISUAL
	 */
	BONE_MEAL_USE, // integer for number of particles
	COMPOSTER_FILL_ATTEMPT, // boolean for success
	COPPER_WAX_OFF,
	COPPER_WAX_ON,
	DRAGON_BREATH,
	DRIPPING_DRIPSTONE,
	ELECTRIC_SPARK, // Axis at which particles are shown
	ENDER_DRAGON_DESTROY_BLOCK,
	END_GATEWAY_SPAWN,
	END_PORTAL_FRAME_FILL,
	ENDER_SIGNAL,
	INSTANT_POTION_BREAK, // Color of particles
	LAVA_INTERACT,
	MOBSPAWNER_FLAMES,
	OXIDISED_COPPER_SCRAPE,
	POTION_BREAK, // Potion data
	REDSTONE_TORCH_BURNOUT,
	SMOKE, // BlockFace for the direction of the smoke particles
	SPONGE_DRY,
	VILLAGER_PLANT_GROW, // integer for number of particles
	/**
	 * SOUND
	 */
	ANVIL_BREAK,
	ANVIL_LAND,
	ANVIL_USE,
	BAT_TAKEOFF,
	BLAZE_SHOOT,
	BOOK_PAGE_TURN,
	BOW_FIRE,
	BREWING_STAND_BREW,
	CHORUS_FLOWER_DEATH,
	CHORUS_FLOWER_GROW,
	CLICK1,
	CLICK2,
	DOOR_CLOSE, // deprecated in 1.19.3
	DOOR_TOGGLE, // deprecated in 1.19.3
	ENDERDRAGON_GROWL,
	ENDERDRAGON_SHOOT,
	ENDEREYE_LAUNCH,
	EXTINGUISH,
	FENCE_GATE_CLOSE, // deprecated in 1.19.3
	FENCE_GATE_TOGGLE, // deprecated in 1.19.3
	FIREWORK_SHOOT,
	GHAST_SHOOT,
	GHAST_SHRIEK,
	GRINDSTONE_USE,
	HUSK_CONVERTED_TO_ZOMBIE,
	IRON_DOOR_CLOSE, // deprecated in 1.19.3
	IRON_DOOR_TOGGLE, // deprecated in 1.19.3
	IRON_TRAPDOOR_CLOSE, // deprecated in 1.19.3
	IRON_TRAPDOOR_TOGGLE, // deprecated in 1.19.3
	PHANTOM_BITE,
	POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON,
	POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON,
	POINTED_DRIPSTONE_LAND,
	PORTAL_TRAVEL,
	RECORD_PLAY, // Material for record item
	SKELETON_CONVERTED_TO_STRAY,
	SMITHING_TABLE_USE,
	STEP_SOUND, // Material for block type stepped on
	TRAPDOOR_CLOSE, // deprecated in 1.19.3
	TRAPDOOR_TOGGLE, // deprecated in 1.19.3
	ZOMBIE_CHEW_WOODEN_DOOR,
	ZOMBIE_CHEW_IRON_DOOR,
	ZOMBIE_DESTROY_DOOR,
	WITHER_BREAK_BLOCK,
	WITHER_SHOOT,
	ZOMBIE_CONVERTED_TO_DROWNED,
	ZOMBIE_CONVERTED_VILLAGER,
	ZOMBIE_INFECT
}
