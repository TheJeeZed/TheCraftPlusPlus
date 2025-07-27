package net.thejeezed.pandorium.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.item.crafting.RecipeManager;
import net.thejeezed.pandorium.Pandorium;
import net.thejeezed.pandorium.kiln.KilnScreen;
import net.thejeezed.pandorium.recipe.KilnRecipe;

import java.util.List;

@JeiPlugin
public class JEIPandoriumPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Pandorium.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new KilnCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<KilnRecipe> polishingRecipes = recipeManager.getAllRecipesFor(KilnRecipe.Type.INSTANCE);
        registration.addRecipes(KilnCategory.KILN_TYPE, polishingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(KilnScreen.class, 80, 30, 20, 20,
                KilnCategory.KILN_TYPE);
    }
}
