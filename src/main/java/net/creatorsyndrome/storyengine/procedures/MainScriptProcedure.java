package net.creatorsyndrome.storyengine.procedures;

import net.creatorsyndrome.storyengine.entity.NPCEntity;
import net.creatorsyndrome.storyengine.init.StoryengineModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;

import net.creatorsyndrome.storyengine.network.StoryengineModVariables;
import net.creatorsyndrome.storyengine.procedures.SetDialogVariantsProcedure;
import net.creatorsyndrome.storyengine.procedures.SendMessageProcedure;

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
		SendMessageProcedure.execute(world, String.valueOf(world.getServer().getTickCount()), "server");
		if (StoryengineModVariables.MapVariables.get(world).current_story_id == 0) {
			//spawn of npc
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = new NPCEntity(StoryengineModEntities.NPC.get(), _level);
				entityToSpawn.moveTo(70, 70, 70, world.getRandom().nextFloat() * 360F, 0);
				entityToSpawn.getPersistentData().putString("name", "randomguy");
				if (entityToSpawn instanceof Mob _mobToSpawn)
					_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
				world.addFreshEntity(entityToSpawn);
			}
			SendMessageProcedure.execute(world, "Эй ты !", "Рандомный чел");
			SetDialogVariantsProcedure.execute(world, 1, 2, 3, "Да", "Нет", "Пока", "Хочешь я подвигаюсь ?");
		}
		if (StoryengineModVariables.MapVariables.get(world).current_story_id == 2) {
			SendMessageProcedure.execute(world, "Ну и не надо", "Рандомный чел");
			StoryengineModVariables.MapVariables.get(world).current_story_id = 3;
			StoryengineModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
