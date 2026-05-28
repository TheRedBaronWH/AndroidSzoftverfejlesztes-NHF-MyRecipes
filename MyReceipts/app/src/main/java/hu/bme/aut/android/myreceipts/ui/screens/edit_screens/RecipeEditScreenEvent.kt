package hu.bme.aut.android.myreceipts.ui.screens.edit_screens

import hu.bme.aut.android.myreceipts.model.RecipeTypes

sealed class RecipeEditScreenEvent {
    data class TakeAndChangeThumbnail(val newThumb: String) : RecipeEditScreenEvent()
    data class ChangeName(val newName: String) : RecipeEditScreenEvent()
    data class ChangeType(val newType: RecipeTypes) : RecipeEditScreenEvent()
    data class ChangeIngredients(val newIngredients: String) : RecipeEditScreenEvent()
    data class ChangeInstructions(val newInstructions: String) : RecipeEditScreenEvent()
    data class ChangeTimeToMake(val newTimeToMake: String) : RecipeEditScreenEvent()
    data class ChangeYoutubeLink(val newYoutubeLink: String) : RecipeEditScreenEvent()

    object SaveRecipe: RecipeEditScreenEvent()
}