package hu.bme.aut.android.myreceipts.ui.screens.create_screen

sealed class RecipeEditScreenUIEvent {
    object Success: RecipeEditScreenUIEvent()

    data class Failure(val error: String): RecipeEditScreenUIEvent()
}