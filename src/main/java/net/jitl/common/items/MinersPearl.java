package net.jitl.common.items;

import net.jitl.client.ChatUtils;
import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

public class MinersPearl extends JItem implements IEssenceItem {

    public MinersPearl(Properties pProperties) {
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
            boolean canUse = player.getY() <= world.getSeaLevel() - 2 &&
                    !world.canSeeSky(new BlockPos((int) player.getX(), (int) (player.getY() + (double) player.getEyeHeight()), (int) player.getZ())) &&
                    !player.isInWater();
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                if(canUse) {
                    if(!world.isClientSide) {
                        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 2));
                        if(essence.consumeEssence(player, 10)) {
                            player.teleportTo(player.getBlockX(), world.getHeight(Heightmap.Types.WORLD_SURFACE, (int) player.getX(), (int) player.getZ()), player.getBlockZ());
                            stack.hurtAndBreak(1, player, item -> { });
                        }
                    } else {
                        world.playSound(player, player.getOnPos(), JSounds.STAFF_0.get(), SoundSource.BLOCKS);
                    }
                } else {
                    if(world.isClientSide) {
                        world.playSound(player, player.getOnPos(), JSounds.STAFF_0.get(), SoundSource.BLOCKS);
                        ChatUtils.sendColouredTranslatedMessage(player, ChatFormatting.DARK_PURPLE, "jitl.message.item.miners_pearl");
                    }
                }
            });
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
