package hu.bme.aut.android.myreceipts.ui.screens.random_screen

import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.RecipeDetailState

sealed class RecipeRandomState {
    object Loading : RecipeRandomState()
    data class Error(val error: Throwable) : RecipeRandomState()
    data class Success(val recipe: RecipeUI) : RecipeRandomState()
}