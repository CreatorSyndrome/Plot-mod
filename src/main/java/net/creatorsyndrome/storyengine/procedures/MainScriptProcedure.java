package net.creatorsyndrome.storyengine.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;

import net.creatorsyndrome.storyengine.network.StoryengineModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class MainScriptProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.world);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		if (StoryengineModVariables.MapVariables.get(world).current_story_id == 0) {
			StoryengineModVariables.MapVariables.get(world).current_story_id = 1;
			StoryengineModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
