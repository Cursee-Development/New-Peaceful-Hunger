package com.cursee.peaceful_hunger.mixin;

import com.cursee.peaceful_hunger.PeacefulHunger;
import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FoodData.class)
public class FabricFoodDataMixin {

//    @ModifyVariable(method = "tick", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;", shift = At.Shift.AFTER), ordinal = 0)
//    public Difficulty ForgeFoodDataMixin$changeDifficulty(Difficulty difficulty) {
//
//        if (PeacefulHunger.enabled && difficulty == Difficulty.PEACEFUL) return Difficulty.HARD;
//
//        return difficulty;
//    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    public Difficulty ForgeFoodDataMixin$getDifficulty(Level instance) {

        Difficulty difficulty = instance.getDifficulty();

        if (PeacefulHunger.enabled && difficulty == Difficulty.PEACEFUL) {
            return Difficulty.HARD;
        }

        return difficulty;
    }
}
