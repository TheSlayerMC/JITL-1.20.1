package net.jitl.common.items;

import net.jitl.client.ChatUtils;
import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.items.base.JItem;
import net.jitl.core.config.JCommonConfig;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EternalNight extends JItem implements IEssenceItem {

    public EternalNight(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level world, @NotNull LivingEntity entity, int timeLeft) {
        if(entity instanceof Player player) {
            if(!world.isClientSide) {
                if (JCommonConfig.ENABLE_ETERNAL_LIGHT.get()) {
                    ServerLevel serverLevel = (ServerLevel) world;
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 2));
                    player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                        if (essence.consumeEssence(player, 10)) {
                            serverLevel.setDayTime(18000);
                            stack.hurtAndBreak(1, player, item -> {
                            });
                            world.playSound(player, player.getOnPos(), JSounds.STAFF_0.get(), SoundSource.BLOCKS);
                            ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.DARK_PURPLE, "jitl.message.item.eternal_night", player.getDisplayName());
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
}
