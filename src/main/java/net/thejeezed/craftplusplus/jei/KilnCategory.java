package net.thejeezed.craftplusplus.jei;


import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.thejeezed.craftplusplus.CraftPlusPlus;
import net.thejeezed.craftplusplus.init.ModBlocks;
import net.thejeezed.craftplusplus.recipe.KilnRecipe;

public class KilnCategory implements IRecipeCategory<KilnRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(CraftPlusPlus.MOD_ID, "kilm");
    public static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/blast_furnace.png");

    public static final RecipeType<KilnRecipe> KILN_TYPE =
            new RecipeType<>(UID, KilnRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public KilnCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 170, 80);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.KILN.get()));
    }

    @Override
    public RecipeType<KilnRecipe> getRecipeType() {
        return KILN_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.craftplusplus.kiln");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KilnRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 53).addIngredients(recipe.getFuelItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem(null));
    }
}
