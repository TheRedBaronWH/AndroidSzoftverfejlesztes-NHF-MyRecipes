package hu.bme.aut.android.myreceipts.ui.screens.list_screen

sealed class RecipeListScreenEvent {
    object LoadRecipes : RecipeListScreenEvent()
}