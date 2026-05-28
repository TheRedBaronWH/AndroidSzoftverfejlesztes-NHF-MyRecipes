package hu.bme.aut.android.myreceipts.ui.screens.details_screen

sealed class RecipeDetailScreenEvent {
    data class LoadRecipe(val recipeId: Int) : RecipeDetailScreenEvent()

    data class DeleteRecipe(val recipeId: Int) : RecipeDetailScreenEvent()
}