package com.cursee.peaceful_hunger;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Mod(Constants.MOD_ID)
public class PeacefulHungerForge {

    @Nullable public static Difficulty difficulty;

    public PeacefulHungerForge() {
    
        PeacefulHunger.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(PeacefulHungerForge::onServerTickEvent);
    }

    private static void onServerTickEvent(TickEvent.ServerTickEvent event) {

        try (ServerLevel level = event.getServer().overworld()) {
            difficulty = level.getDifficulty();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}