package hu.bme.aut.android.myreceipts.ui.screens.details_screen

import hu.bme.aut.android.myreceipts.ui.model.RecipeUI

sealed class RecipeDetailState {
    object Loading : RecipeDetailState()
    data class Error(val error: Throwable) : RecipeDetailState()
    data class Success(val recipe: RecipeUI) : RecipeDetailState()
}