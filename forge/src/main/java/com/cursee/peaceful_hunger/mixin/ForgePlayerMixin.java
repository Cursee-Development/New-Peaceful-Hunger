package com.cursee.peaceful_hunger.mixin;

import com.cursee.peaceful_hunger.PeacefulHunger;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class ForgePlayerMixin {

//    @Redirect(method = "aiStep", at = @At(value ="FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/Difficulty;PEACEFUL:Lnet/minecraft/world/Difficulty;"))
//    private Difficulty ForgePlayerMixin$getDifficulty() {
//        return PeacefulHunger.enabled ? Difficulty.HARD : Difficulty.PEACEFUL;
//    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/Level;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty injected1() {

        Player player = (Player) (Object) this;

        if (PeacefulHunger.enabled && player.level().getDifficulty() == Difficulty.PEACEFUL) return Difficulty.HARD;

        return player.level().getDifficulty();
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;needsFood()Z"))
    private boolean injected3(FoodData instance) {

        if (PeacefulHunger.enabled && !instance.needsFood()) return true;

        return instance.needsFood();
    }
}
