package com.cursee.examplemod;

import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ExampleModForge {
    
    public ExampleModForge() {
    
        ExampleMod.init();
    }
}