package net.jitl.core.helper.compat;

import net.minecraftforge.fml.ModList;

public class ModCompat {

    public static void init() {
        if(ModList.get().isLoaded("jeresources")) {
            JerCompat.init();
        }
    }
}
