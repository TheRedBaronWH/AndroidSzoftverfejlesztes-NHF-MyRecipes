package hu.bme.aut.android.myreceipts.ui.screens.list_screen

import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

sealed class RecipeListState {
    object Loading: RecipeListState()

    data class Error(val error: Throwable) : RecipeListState()

    data class Success(val recipeList: List<RecipeUI>) : RecipeListState()
}