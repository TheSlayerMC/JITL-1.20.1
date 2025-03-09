package net.jitl.common.entity.projectile;

import net.jitl.core.init.internal.JEntities;
import net.jitl.core.init.internal.JParticleManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DoomsbringerEntity extends JThrowableProjectile {

    public DoomsbringerEntity(EntityType<DoomsbringerEntity> type, Level world) {
        super(type, world);
    }

    public DoomsbringerEntity(int damage, Level world, LivingEntity thrower) {
        super(JEntities.DOOMSBRINGER_TYPE.get(), damage, world, thrower);
        setPotionEffect(MobEffects.CONFUSION, 100);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(JParticleManager.DOOMSBRINGER.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected float getGravity() {
        return 0.003F;
    }
}