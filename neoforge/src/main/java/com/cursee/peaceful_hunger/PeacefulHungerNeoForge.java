package com.cursee.peaceful_hunger;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class PeacefulHungerNeoForge {

    public PeacefulHungerNeoForge(IEventBus eventBus) {

        PeacefulHunger.init();
    }
}
