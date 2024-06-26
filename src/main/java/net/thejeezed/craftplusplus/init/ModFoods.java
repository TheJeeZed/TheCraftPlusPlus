package net.thejeezed.craftplusplus.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties RAW_TENTACLE = new FoodProperties.Builder().nutrition(2).meat()
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 50), 0.1f).build();
    public static final FoodProperties COOKED_TENTACLE = new FoodProperties.Builder().nutrition(6).meat()
            .saturationMod(0.6f).build();
    public static final FoodProperties SEA_SOUP = new FoodProperties.Builder().nutrition(12)
            .saturationMod(0.7f).effect(() -> new MobEffectInstance(MobEffects.CONDUIT_POWER, 1200), 1).build();
}
