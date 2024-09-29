package com.cursee.peaceful_hunger.mixin;

import com.cursee.peaceful_hunger.PeacefulHunger;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class ForgePlayerMixin {

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty FabricPlayerMixin$getDifficulty(Level instance) {

        if (PeacefulHunger.enabled && instance.getDifficulty() == Difficulty.PEACEFUL) return Difficulty.HARD;

        return instance.getDifficulty();
    }

//    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"))
//    private boolean injected3(FoodData instance) {
//
//        if (PeacefulHunger.enabled && !instance.needsFood()) return true;
//
//        return instance.needsFood();
//    }
}
