package hu.bme.aut.android.myreceipts.ui.navigation

import androidx.navigation3.runtime.NavKey
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import kotlinx.serialization.Serializable

sealed interface Screen: NavKey {
    @Serializable
    data object WelcomeScreen : Screen

    @Serializable
    data object RecipeListScreen : Screen

    @Serializable
    data object RecipeCreateScreen : Screen

    @Serializable
    data class RecipeEditScreen(val recipeId: Int): Screen

    @Serializable
    data class RecipeDetailsScreen(val recipeId: Int) : Screen

    @Serializable
    data class RecipeRandomScreen(val recipe: RecipeUI? = null) : Screen
}