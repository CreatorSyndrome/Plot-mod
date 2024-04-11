package net.creatorsyndrome.storyengine.entity;

import net.creatorsyndrome.storyengine.init.StoryengineModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.ClientTickEvent;

public class CameraEntity extends Animal {
    private Entity targetEntity;
    public boolean active = false;

    public double tox = 0.0;
    public double toy = 0.0;
    public double toz = 0.0;

    public double speed = 1.0;

    public CameraEntity(EntityType<CameraEntity> type, Level world) {
        super(type, world);
        setNoAi(true);
    }

    protected void registerGoals() {

    }

    @Override
    public void tick() {
        super.tick();
        if (active) {
            this.level.players().get(0).setPos(this.position());
            this.level.players().get(0).setXRot(this.getXRot());
            this.level.players().get(0).setYRot(this.getYRot());
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
        NPCEntity retval = StoryengineModEntities.NPC.get().create(serverWorld);
        retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
        return retval;
    }
}