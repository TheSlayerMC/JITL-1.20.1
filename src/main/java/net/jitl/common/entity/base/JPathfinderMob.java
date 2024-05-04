package net.jitl.common.entity.base;

import net.jitl.client.knowledge.EnumKnowledge;
import net.jitl.common.capability.stats.PlayerStatsProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class JPathfinderMob extends PathfinderMob implements GeoEntity {

    protected EnumKnowledge knowledge;
    protected float knowledgeAmount = 0.0F;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected JPathfinderMob(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected abstract void controller(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controller(controllers);
    }

    public void setKnowledge(EnumKnowledge knowledge, float amount) {
        this.knowledge = knowledge;
        this.knowledgeAmount = amount;
    }

    @Override
    public void die(@NotNull DamageSource cause) {
        super.die(cause);
        if(cause.getEntity() instanceof Player player && this.knowledge != null) {
            player.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(knowledge -> {
                knowledge.addXP(this.knowledge, this.knowledgeAmount, player);
            });
        }
    }
}