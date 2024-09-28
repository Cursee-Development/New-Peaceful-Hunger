package com.cursee.peaceful_hunger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class PeacefulHungerFabric implements ModInitializer {

    @Nullable public static Difficulty difficulty;
    
    @Override
    public void onInitialize() {

        PeacefulHunger.init();

        ServerTickEvents.START_SERVER_TICK.register(new ServerTickHandler());
    }

    public static class ServerTickHandler implements ServerTickEvents.StartTick {

        @Override
        public void onStartTick(MinecraftServer server) {

            try (ServerLevel level = server.overworld()) {
                difficulty = level.getDifficulty();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
