package net.jitl.common.items;

import net.jitl.common.capability.essence.PlayerEssenceProvider;
import net.jitl.common.entity.projectile.JThrowableProjectile;
import net.jitl.common.items.base.JSwordItem;
import net.jitl.core.helper.IEssenceItem;
import net.jitl.core.helper.JToolTiers;
import net.jitl.core.helper.TriFunction;
import net.jitl.core.init.internal.JItems;
import net.jitl.core.init.internal.JSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class HammerItem extends JSwordItem implements IEssenceItem {

    protected TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory;
    private final int essenceUsage, damage;

    public HammerItem(JToolTiers tier, int essence, int damage, TriFunction<Integer, Level, LivingEntity, JThrowableProjectile> projectileFactory) {
        super(tier, JItems.BASIC, JItems.itemProps().durability(tier.getTier().getUses()));
        this.projectileFactory = projectileFactory;
        this.essenceUsage = essence;
        this.damage = damage;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(!level.isClientSide()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                if(essence.consumeEssence(player, this.essenceUsage)) {
                    JThrowableProjectile projectile = projectileFactory.apply(this.damage, level, player);
                    projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                    level.addFreshEntity(projectile);
                    player.getItemInHand(player.getUsedItemHand()).hurtAndBreak(1, player, item -> {});
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), JSounds.HAMMER.get(), SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                }
            });
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("jitl.tooltip.hammer", (damage / 2)));
        addItemDesc(JItems.SPELLBINDING_HAMMER.get().asItem(), tooltip, "jitl.tooltip.spellbinding_hammer");
        addItemDesc(JItems.EARTHEN_HAMMER.get().asItem(), tooltip, "jitl.tooltip.earthen_hammer");
        addItemDesc(JItems.FLAMING_HAMMER.get().asItem(), tooltip, "jitl.tooltip.flaming_hammer");
        addItemDesc(JItems.NETHIC_HAMMER.get().asItem(), tooltip, "jitl.tooltip.nethic_hammer");
        addItemDesc(JItems.OVERGROWN_HAMMER.get().asItem(), tooltip, "jitl.tooltip.overgrown_hammer");
        addItemDesc(JItems.ROCKY_HAMMER.get().asItem(), tooltip, "jitl.tooltip.rocky_hammer");
        addItemDesc(JItems.ROYAL_HAMMER.get().asItem(), tooltip, "jitl.tooltip.royal_hammer");
        addItemDesc(JItems.CRYSTALLIZED_HAMMER.get().asItem(), tooltip, "jitl.tooltip.crystallized_hammer");
        addItemDesc(JItems.WITHIC_HAMMER.get().asItem(), tooltip, "jitl.tooltip.withic_hammer");
        tooltip.add(Component.translatable("jitl.tooltip.essence_usage", essenceUsage));
        tooltip.add(Component.translatable("jitl.uses_remaining", (stack.getMaxDamage() - stack.getDamageValue())));
    }

    public void addItemDesc(Item item, List<Component> tooltip, String descLoc) {
        if(this == item) {
            tooltip.add(Component.translatable(descLoc));
        }
    }
}