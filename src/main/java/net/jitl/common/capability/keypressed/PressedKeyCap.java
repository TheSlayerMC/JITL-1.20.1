package net.jitl.common.capability.keypressed;

import net.minecraft.nbt.CompoundTag;

public class PressedKeyCap {
    private boolean armor;
    private boolean amulet;

    public void copyFrom(PressedKeyCap pressedKeyCap) {
        this.armor = pressedKeyCap.armor;
        this.amulet = pressedKeyCap.amulet;
    }

    public void setArmorPressed(boolean bool) {
        armor = bool;
    }

    public boolean isArmorPressed() {
        return armor;
    }

    public void setAmuletPressed(boolean bool) {
        amulet = bool;
    }

    public boolean isAmuletPressed() {
        return amulet;
    }

    public void saveNBT(CompoundTag nbt) {
        nbt.putBoolean("armor", armor);
        nbt.putBoolean("amulet", amulet);
    }

    public void readNBT(CompoundTag nbt) {
        this.armor = nbt.getBoolean("armor");
        this.amulet = nbt.getBoolean("amulet");
    }
}
