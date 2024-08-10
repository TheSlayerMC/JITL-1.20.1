package net.jitl.common.capability.portal;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerPortalProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerPortal> PORTAL = CapabilityManager.get(new CapabilityToken<>() { });

    private PlayerPortal portal = null;
    private final LazyOptional<PlayerPortal> optional = LazyOptional.of(this::createPlayerPortal);

    private @NotNull PlayerPortal createPlayerPortal() {
        if(this.portal == null) {
            this.portal = new PlayerPortal();
        }
        return this.portal;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PORTAL) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerPortal().saveNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerPortal().readNBT(nbt);
    }
}