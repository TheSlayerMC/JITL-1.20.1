package net.jitl.core.init.compat;

import net.minecraftforge.fml.ModList;

public class ModCompat {

    public static void init() {
        if(ModList.get().isLoaded("jeresources")) {
            JerCompat.init();
        }
    }
}
