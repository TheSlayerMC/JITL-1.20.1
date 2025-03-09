package net.jitl.common.items;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.entity.projectile.JThrowableProjectile;
import net.jitl.common.items.base.JItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.helper.TriFunction;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class GunItem extends JItem implements IEssenceItem {

    protected TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory;
    private final int essenceUsage, damage;

    public GunItem(int essence, int damage, int maxUses, TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory) {
        super(JItems.itemProps().stacksTo(1).durability(maxUses));
        this.projectileFactory = projectileFactory;
        this.essenceUsage = essence;
        this.damage = damage;
    }

    @Override
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level worldIn, @NotNull LivingEntity entityLiving, int timeLeft) {
        if(entityLiving instanceof Player player && worldIn instanceof ServerLevel level) {
            if(getPowerForTime(this.getUseDuration(stack) - timeLeft) > 0.25) {
               player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                   if(essence.consumeEssence(player, this.essenceUsage)) {
                       JThrowableProjectile projectile = projectileFactory.apply(this.damage, level, player);
                       projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                       level.addFreshEntity(projectile);
                       player.getItemInHand(player.getUsedItemHand()).hurtAndBreak(1, player, item -> {});
                       level.playSound(null, player.getX(), player.getY(), player.getZ(), JSounds.CANNON.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                   }
               });
            }
        }
    }

    public static float getPowerForTime(int charge) {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack item) {
        return UseAnim.BOW;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        player.startUsingItem(usedHand);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("jitl.tooltip.cannon", (damage / 2)));
        addItemDesc(JItems.NETHER_PLASMA.get().asItem(), tooltip, "jitl.tooltip.nether_plasma");
        addItemDesc(JItems.OCEAN_PLASMA.get().asItem(), tooltip, "jitl.tooltip.ocean_plasma");
        addItemDesc(JItems.FOREST_PLASMA.get().asItem(), tooltip, "jitl.tooltip.forest_plasma");
        addItemDesc(JItems.ROCK_LAUNCHER.get().asItem(), tooltip, "jitl.tooltip.rock_launcher");
        addItemDesc(JItems.CHAOS_CANNON.get().asItem(), tooltip, "jitl.tooltip.chaos_cannon");
        addItemDesc(JItems.EYE_BLASTER.get().asItem(), tooltip, "jitl.tooltip.eye_blaster");
        tooltip.add(Component.translatable("jitl.tooltip.essence_usage", essenceUsage));
        tooltip.add(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }
}