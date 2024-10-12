package net.jitl.common.event;

import net.jitl.core.init.JITL;
import net.jitl.core.init.internal.JItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = JITL.MODID)
public class CurioEventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide()) {
            if (entity instanceof Player player) {
                Optional<SlotResult> SKULL = getEquippedCurio(player, JItems.SKULL_OF_DECAY.get());
                Optional<SlotResult> DEATH_CAP = getEquippedCurio(player, JItems.DEATH_CAP.get());

                if(event.getSource().getEntity() instanceof LivingEntity attacker) {
                    if(SKULL.isPresent()) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        SKULL.get().stack().hurtAndBreak(1, player, item -> {});
                    }

                    if(DEATH_CAP.isPresent()) {
                        attacker.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
                        DEATH_CAP.get().stack().hurtAndBreak(1, player, item -> {});
                    }
                }
            }
        }
    }

    private static Optional<SlotResult> getEquippedCurio(LivingEntity entity, @NotNull Item curio) {
        return CuriosApi.getCuriosHelper().findFirstCurio(entity, curio);
    }

    public static void onKeyPressed(Player player) {

    }
}
