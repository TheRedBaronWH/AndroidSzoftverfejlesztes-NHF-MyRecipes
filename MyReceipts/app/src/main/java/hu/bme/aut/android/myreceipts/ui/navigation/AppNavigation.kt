package hu.bme.aut.android.myreceipts.ui.navigation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import hu.bme.aut.android.myreceipts.ui.model.RecipeUI
import hu.bme.aut.android.myreceipts.ui.screens.WelcomeScreen
import hu.bme.aut.android.myreceipts.ui.screens.create_screen.RecipeCreateScreen
import hu.bme.aut.android.myreceipts.ui.screens.details_screen.RecipeDetailScreen
import hu.bme.aut.android.myreceipts.ui.screens.edit_screens.edit_screen.RecipeEditScreen
import hu.bme.aut.android.myreceipts.ui.screens.list_screen.RecipeListScreen
import hu.bme.aut.android.myreceipts.ui.screens.random_screen.RecipeRandomScreen

@Composable
fun AppNavigation(
    loadRecipe: RecipeUI? = null,
    modifier: Modifier = Modifier
) {
    val backStack = if (loadRecipe == null) {
        rememberNavBackStack(Screen.WelcomeScreen)
    } else {
        rememberNavBackStack(Screen.WelcomeScreen, Screen.RecipeRandomScreen(loadRecipe))
    }
    NavDisplay(
        modifier = modifier.statusBarsPadding(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.WelcomeScreen> {
                WelcomeScreen(
                    onReceiptsClicked = {
                        backStack.add(Screen.RecipeListScreen)
                    },
                    onRandomClicked = {
                        backStack.add(Screen.RecipeRandomScreen(null))
                    }
                )
            }

            entry<Screen.RecipeListScreen> {
                RecipeListScreen(
                    onBackClicked = { backStack.removeLastOrNull() },
                    onNewRecipeClicked = { backStack.add(Screen.RecipeCreateScreen) },
                    onRecipeClicked = { backStack.add(Screen.RecipeDetailsScreen(recipeId = it)) }
                )
            }

            entry<Screen.RecipeDetailsScreen> { key ->
                RecipeDetailScreen(
                    recipeId = key.recipeId,
                    onEditClicked = {
                        backStack.add(
                            Screen.RecipeEditScreen(recipeId = key.recipeId)
                        )
                    },
                    onBackClicked = { backStack.removeLastOrNull() }
                )
            }

            entry<Screen.RecipeCreateScreen> { key ->
                RecipeCreateScreen(
                    onBackClicked = { backStack.removeLastOrNull() }
                )
            }

            entry<Screen.RecipeEditScreen> { key ->
                RecipeEditScreen(
                    recipeId = key.recipeId,
                    onBackClicked = { backStack.removeLastOrNull() }
                )
            }

            entry<Screen.RecipeRandomScreen> { key ->
                RecipeRandomScreen(
                    onBackClicked = { backStack.removeLastOrNull() },
                    recipe = key.recipe
                )
            }
        }
    )
}