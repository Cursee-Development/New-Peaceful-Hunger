package com.cursee.peaceful_hunger.mixin;

import com.cursee.peaceful_hunger.PeacefulHunger;
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

        if (PeacefulHunger.enabled && instance.getDifficulty() == Difficulty.PEACEFUL) return Difficulty.HARD;

        return instance.getDifficulty();
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean injected2(GameRules instance, GameRules.Key<GameRules.BooleanValue> $$0) {

        if (PeacefulHunger.enabled && PeacefulHunger.disable_natural_regeneration && instance.getBoolean(GameRules.RULE_NATURAL_REGENERATION)) return false;

        return instance.getBoolean(GameRules.RULE_NATURAL_REGENERATION);
    }
}
