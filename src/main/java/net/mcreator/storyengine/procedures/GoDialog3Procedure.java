package net.mcreator.storyengine.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.storyengine.network.StoryengineModVariables;

public class GoDialog3Procedure {
	public static void execute(LevelAccessor world) {
		StoryengineModVariables.MapVariables.get(world).current_story_id = StoryengineModVariables.MapVariables.get(world).variant_3_to;
		StoryengineModVariables.MapVariables.get(world).syncData(world);
	}
}
