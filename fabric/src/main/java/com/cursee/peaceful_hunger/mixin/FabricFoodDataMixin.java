package com.cursee.peaceful_hunger.mixin;

import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// if (instance.getDifficulty() == Difficulty.PEACEFUL) return Difficulty.HARD;
// return instance.getDifficulty();

@Mixin(FoodData.class)
public class FabricFoodDataMixin {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty injected1(Level instance) {
        return Difficulty.HARD;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean injected2(GameRules instance, GameRules.Key<GameRules.BooleanValue> $$0) {
        return false;
    }
}
