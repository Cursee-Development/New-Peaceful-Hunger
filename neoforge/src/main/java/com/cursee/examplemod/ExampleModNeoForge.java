package com.cursee.examplemod;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ExampleModNeoForge {

    public ExampleModNeoForge(IEventBus eventBus) {

        ExampleMod.init();
    }
}
